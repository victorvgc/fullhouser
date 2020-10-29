package com.victorvgc.fullhouser.flowOne.service

import com.victorvgc.fullhouser.core.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DeckService {

    @GET("new/shuffle")
    suspend fun saveDeck(@Query("cards") cards: String): Response
}