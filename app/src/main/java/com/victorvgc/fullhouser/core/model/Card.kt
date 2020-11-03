package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Card(
    val suit: String,
    val value: String,
) : Parcelable {

    companion object {
        fun fromString(s: String): Card {
            val suit = if (s.length == 3) s[2].toString() else s[1].toString()
            val value = if (s.length == 3) s.substring(0, 2) else s[0].toString()

            return Card(suit, value)
        }
    }

    @IgnoredOnParcel
    val img: String =
        "${value.toUpperCase(Locale.getDefault())}${suit.toUpperCase(Locale.getDefault())}.png"

    override fun toString(): String {
        return "$value$suit"
    }
}