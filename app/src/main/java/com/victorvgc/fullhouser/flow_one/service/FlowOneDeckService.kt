package com.victorvgc.fullhouser.flow_one.service

import com.victorvgc.fullhouser.core.constants.NetworkConstants
import com.victorvgc.fullhouser.core.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FlowOneDeckService {

    @GET("new/shuffle/")
    suspend fun saveDeck(@Query(NetworkConstants.PARAM_CARDS) cards: String): Response

    @GET(NetworkConstants.DRAW_CARDS)
    suspend fun drawCards(
        @Path(NetworkConstants.PARAM_DECK_ID) deckId: String,
        @Query(NetworkConstants.CARD_COUNT) cardCount: Int
    ): Response

    @GET("${NetworkConstants.PLAYER_HAND_URL}add/")
    suspend fun createDeckPile(
        @Path(NetworkConstants.PARAM_DECK_ID) deckId: String,
        @Query(NetworkConstants.PARAM_CARDS, encoded = true) cards: String
    ): Response

    @GET("${NetworkConstants.ROT_CARD_URL}add/")
    suspend fun createRotPile(
        @Path(NetworkConstants.PARAM_DECK_ID) deckId: String,
        @Query(NetworkConstants.PARAM_CARDS, encoded = true) rotCard: String
    ): Response
}