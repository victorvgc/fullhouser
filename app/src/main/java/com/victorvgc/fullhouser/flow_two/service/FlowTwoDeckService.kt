package com.victorvgc.fullhouser.flow_two.service

import com.victorvgc.fullhouser.core.constants.NetworkConstants
import com.victorvgc.fullhouser.core.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FlowTwoDeckService {
    @GET(NetworkConstants.PLAYER_HAND_URL + "list")
    suspend fun retrievePlayerHand(@Path(NetworkConstants.PARAM_DECK_ID) deckId: String): Response

    @GET(NetworkConstants.ROT_CARD_URL + "list")
    suspend fun retrieveRotCard(@Path(NetworkConstants.PARAM_DECK_ID) deckId: String): Response
}