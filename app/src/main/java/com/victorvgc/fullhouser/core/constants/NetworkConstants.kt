package com.victorvgc.fullhouser.core.constants

class NetworkConstants {
    companion object {
        const val BASE_URL = "https://deckofcardsapi.com/api/deck/"
        const val BASE_CARD_URL = "https://deckofcardsapi.com/static/img/"

        const val PARAM_DECK_ID = "deckId"
        const val PARAM_CARDS = "cards"

        const val PLAYER_HAND = "player_hand"
        const val ROT_CARD = "rot_card"
        const val CARD_COUNT = "count"

        const val PLAYER_HAND_URL = "{$PARAM_DECK_ID}/pile/$PLAYER_HAND/"
        const val ROT_CARD_URL = "{$PARAM_DECK_ID}/pile/$ROT_CARD/"
        const val DRAW_CARDS = "{$PARAM_DECK_ID}/draw/"
    }
}