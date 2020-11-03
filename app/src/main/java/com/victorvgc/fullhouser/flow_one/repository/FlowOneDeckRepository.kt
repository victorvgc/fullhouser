package com.victorvgc.fullhouser.flow_one.repository

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flow_one.model.Success

interface FlowOneDeckRepository {
    suspend fun saveDeck(deck: Deck?): Either<Failure, Success>
}