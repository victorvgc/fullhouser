package com.victorvgc.fullhouser.flow_two.data_source

import android.content.SharedPreferences
import com.victorvgc.fullhouser.core.constants.SharedPreferencesConstants

class FlowTwoLocalDeckDataSourceImpl(private val preferences: SharedPreferences) :
    FlowTwoLocalDeckDataSource {
    override suspend fun getDeckId(): String {
        val deckId = preferences.getString(SharedPreferencesConstants.deckId, "")

        return deckId ?: ""
    }
}