package com.victorvgc.fullhouser.flowOne.data_source

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.core.utils.toParamString
import com.victorvgc.fullhouser.flowOne.failure.APIFailure
import com.victorvgc.fullhouser.flowOne.service.FlowOneDeckService

class FlowOneRemoteDeckDataSourceImpl(private val flowOneDeckService: FlowOneDeckService) :
    FlowOneRemoteDeckDataSource {
    override suspend fun saveDeck(deck: Deck?): Either<Failure, Deck> {
        val response = flowOneDeckService.saveDeck(deck!!.toString())

        return if (response.success)
            Right(Deck(response.deckId, deck.cards, deck.rotCard))
        else
            Left(APIFailure())
    }

    override suspend fun savePile(deck: Deck?): Either<Failure, Deck> {
        val response = flowOneDeckService.createDeckPile(deck!!.id, deck.cards.toParamString())

        return if (response.success)
            Right(deck)
        else
            Left(APIFailure())
    }

    override suspend fun saveRotCard(deck: Deck?): Either<Failure, Deck> {
        val response = flowOneDeckService.createRotPile(deck!!.id, deck.rotCard.toString())

        return if (response.success)
            Right(deck)
        else
            Left(APIFailure())
    }
}