package com.victorvgc.fullhouser.flowOne.data_source

import android.content.SharedPreferences
import com.victorvgc.fullhouser.core.constants.SharedPreferencesConstants
import com.victorvgc.fullhouser.core.model.Deck

class LocalDeckDataSourceImpl(private val sharedPreferences: SharedPreferences) : LocalDeckDataSource {
    override suspend fun saveDeck(deck: Deck) {
        val editor = sharedPreferences.edit()

        editor.putString(SharedPreferencesConstants.deckId, deck.id)
        editor.putString(SharedPreferencesConstants.rotCard, deck.rotCard.toString())

        editor.apply()
    }
}