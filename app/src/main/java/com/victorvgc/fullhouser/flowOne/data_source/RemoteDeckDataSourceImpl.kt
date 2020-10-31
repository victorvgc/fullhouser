package com.victorvgc.fullhouser.flowOne.data_source

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.core.utils.toParamString
import com.victorvgc.fullhouser.flowOne.failure.APIFailure
import com.victorvgc.fullhouser.flowOne.service.DeckService

class RemoteDeckDataSourceImpl(private val deckService: DeckService) : RemoteDeckDataSource {
    override suspend fun saveDeck(deck: Deck?): Either<Failure, Deck> {
        val response = deckService.saveDeck(deck!!.toString())

        return if (response.success)
            Right(Deck(response.deckId, deck.cards, deck.rotCard))
        else
            Left(APIFailure())
    }

    override suspend fun savePile(deck: Deck?): Either<Failure, Deck> {
        val response = deckService.createDeckPile(deck.toString(), deck!!.cards.toParamString())

        return if (response.success)
            Right(deck)
        else
            Left(APIFailure())
    }
}