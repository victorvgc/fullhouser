package com.victorvgc.fullhouser.core.constants

class NetworkConstants {
    companion object {
        const val BASE_URL = "https://deckofcardsapi.com/api/deck/"
        const val BASE_CARD_URL = "https://deckofcardsapi.com/static/img/"

        const val PLAYER_HAND_URL = "{deckId}/pile/player_hand/"
        const val ROT_CARD_URL = "{deckId}/pile/rot_card/"

        const val PARAM_DECK_ID = "deckId"
        const val PARAM_CARDS = "cards"
    }
}