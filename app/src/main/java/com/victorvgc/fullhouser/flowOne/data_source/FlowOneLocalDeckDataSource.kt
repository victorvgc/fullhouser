package com.victorvgc.fullhouser.flowOne.data_source

import com.victorvgc.fullhouser.core.model.Deck

interface FlowOneLocalDeckDataSource {
    suspend fun saveDeck(deck: Deck)
}

