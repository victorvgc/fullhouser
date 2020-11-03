package com.victorvgc.fullhouser.flow_two.data_source

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.utils.Failure

interface FlowTwoRemoteDeckDataSource {
    suspend fun getPlayerHand(deckId: String): Either<Failure, List<Card>>

    suspend fun getRotCard(deckId: String): Either<Failure, Card>
}