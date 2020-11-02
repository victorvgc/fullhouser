package com.victorvgc.fullhouser.flow_one.data_source

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure

interface FlowOneRemoteDeckDataSource {
    suspend fun saveDeck(deck: Deck?): Either<Failure, Deck>

    suspend fun savePile(deck: Deck?): Either<Failure, Deck>

    suspend fun saveRotCard(deck: Deck?): Either<Failure, Deck>
}

