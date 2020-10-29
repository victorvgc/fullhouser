package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Card(
    val suit: String,
    val value: String,
) : Parcelable {
    init {
        val img: String = "$value$suit.png"
    }

    override fun toString(): String {
        return "$value$suit"
    }
}