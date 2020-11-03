package com.victorvgc.fullhouser.core.utils

import com.victorvgc.fullhouser.core.model.Card

fun List<Card>.toParamString(): String {
    var string = ""

    for (card in this) {
        string += "$card,"
    }

    return string
}