package com.victorvgc.fullhouser.flowOne.repository

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flowOne.data_source.FlowOneLocalDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.FlowOneRemoteDeckDataSource
import com.victorvgc.fullhouser.flowOne.failure.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flowOne.model.Success

class FlowOneDeckRepositoryImpl(
    private val remoteDeckDataSource: FlowOneRemoteDeckDataSource,
    private val localDeckDataSource: FlowOneLocalDeckDataSource
) : FlowOneDeckRepository {
    override suspend fun saveDeck(deck: Deck?): Either<Failure, Success> {
        val resDeck = remoteDeckDataSource.saveDeck(deck!!)

        return resDeck.fold(
            { Left(SomethingWentWrongFailure()) },
            { remoteDeck ->
                val resDeckPile = remoteDeckDataSource.savePile(remoteDeck)

                resDeckPile.fold(
                    { Left(SomethingWentWrongFailure()) },
                    { remotePile ->
                        val resRotPile = remoteDeckDataSource.saveRotCard(remotePile)

                        resRotPile.fold(
                            { Left(SomethingWentWrongFailure()) },
                            { rotPile ->
                                localDeckDataSource.saveDeck(rotPile)
                                Right(Success())
                            }
                        )
                    }
                )
            }
        )
    }
}