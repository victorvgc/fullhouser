package com.victorvgc.fullhouser.flowOne.repository

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSource
import com.victorvgc.fullhouser.flowOne.failure.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flowOne.model.Success

class DeckRepositoryImpl(
    private val remoteDeckDataSource: RemoteDeckDataSource,
    private val localDeckDataSource: LocalDeckDataSource
): DeckRepository {
    override suspend fun saveDeck(deck: Deck?): Either<Failure, Success> {
        val response = remoteDeckDataSource.saveDeck(deck!!)

        return response.fold(
            { Left(SomethingWentWrongFailure()) },
            { remoteDeck ->
                localDeckDataSource.saveDeck(remoteDeck)
                Right(Success())
            }
        )
    }
}