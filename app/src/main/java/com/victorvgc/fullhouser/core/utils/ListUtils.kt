package com.victorvgc.fullhouser.core.utils

import com.victorvgc.fullhouser.core.model.Card

fun List<Card>.toParamString(): String {
    var string = ""

    for (cardIndex in this.indices) {
        val card = this[cardIndex]
        string += "$card"

        if (cardIndex < this.size - 1)
            string += ","
    }

    return string
}