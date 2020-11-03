package com.victorvgc.fullhouser.flowOne.data_source

import android.content.SharedPreferences
import com.victorvgc.fullhouser.core.constants.SharedPreferencesConstants
import com.victorvgc.fullhouser.core.model.Deck

class FlowOneLocalDeckDataSourceImpl(private val sharedPreferences: SharedPreferences) :
    FlowOneLocalDeckDataSource {
    override suspend fun saveDeck(deck: Deck) {
        val editor = sharedPreferences.edit()

        editor.putString(SharedPreferencesConstants.deckId, deck.id)

        editor.apply()
    }
}