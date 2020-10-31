package com.victorvgc.fullhouser.flowOne.data_source

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure

interface RemoteDeckDataSource {
    suspend fun saveDeck(deck: Deck?): Either<Failure, Deck>

    suspend fun savePile(deck: Deck?): Either<Failure, Deck>
}

