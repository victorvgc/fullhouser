package com.victorvgc.fullhouser.flow_two.data_source

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.constants.NetworkConstants
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Pile
import com.victorvgc.fullhouser.core.utils.APIFailure
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flow_two.service.FlowTwoDeckService

class FlowTwoRemoteDeckDataSourceImpl(private val deckService: FlowTwoDeckService) :
    FlowTwoRemoteDeckDataSource {
    override suspend fun getPlayerHand(deckId: String): Either<Failure, List<Card>> {
        val response = deckService.retrievePlayerHand(deckId)

        if (response.success) {
            val cardList = mutableListOf<Card>()

            val pile = response.piles[NetworkConstants.PLAYER_HAND] ?: Pile(0, emptyList())
            if (!pile.cards.isNullOrEmpty()) {
                for (remoteCards in pile.cards) {
                    cardList.add(Card.fromString(remoteCards.code))
                }
            }

            return if (cardList.isNotEmpty())
                Right(cardList)
            else
                Left(APIFailure())
        }

        return Left(APIFailure())
    }

    override suspend fun getRotCard(deckId: String): Either<Failure, Card> {
        val response = deckService.retrieveRotCard(deckId)

        if (response.success) {
            val cardList = mutableListOf<Card>()

            val pile = response.piles[NetworkConstants.ROT_CARD] ?: Pile(0, emptyList())

            if (!pile.cards.isNullOrEmpty()) {
                for (remoteCards in pile.cards) {
                    cardList.add(Card.fromString(remoteCards.code))
                }
            }

            return if (cardList.isNotEmpty())
                Right(cardList[0])
            else
                Left(APIFailure())
        }

        return Left(APIFailure())
    }
}