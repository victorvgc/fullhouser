package com.victorvgc.fullhouser.flowOne.repository

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flowOne.model.Success

interface DeckRepository {
    suspend fun saveDeck(deck: Deck?): Either<Failure, Success>
}