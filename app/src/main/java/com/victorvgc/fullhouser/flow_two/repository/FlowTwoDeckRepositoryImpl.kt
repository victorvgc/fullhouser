package com.victorvgc.fullhouser.flow_two.repository

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.APIFailure
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.core.utils.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoLocalDeckDataSource
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoRemoteDeckDataSource

class FlowTwoDeckRepositoryImpl(
    private val localDeckDataSource: FlowTwoLocalDeckDataSource,
    private val remoteDeckDataSource: FlowTwoRemoteDeckDataSource
) : FlowTwoDeckRepository {
    override suspend fun getDeck(): Either<Failure, Deck> {
        val deckId = localDeckDataSource.getDeckId()

        if (deckId.isNotEmpty()) {
            val playerHand = remoteDeckDataSource.getPlayerHand(deckId)

            return playerHand.fold({ Left(APIFailure()) },
                { hand ->
                    val rotCard = remoteDeckDataSource.getRotCard(deckId)
                    rotCard.fold({ Left(APIFailure()) },
                        { rc ->
                            Right(Deck(deckId, hand, rc))
                        }
                    )
                })
        }

        return Left(SomethingWentWrongFailure())
    }
}