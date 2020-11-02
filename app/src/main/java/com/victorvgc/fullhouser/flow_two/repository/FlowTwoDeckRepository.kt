package com.victorvgc.fullhouser.flow_two.repository

import arrow.core.Either
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure

interface FlowTwoDeckRepository {
    suspend fun getDeck(): Either<Failure, Deck>
}