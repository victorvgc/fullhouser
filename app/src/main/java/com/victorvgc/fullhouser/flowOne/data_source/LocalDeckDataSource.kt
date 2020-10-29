package com.victorvgc.fullhouser.flowOne.data_source

import com.victorvgc.fullhouser.core.model.Deck

interface LocalDeckDataSource {
    suspend fun saveDeck(deck: Deck)
}

