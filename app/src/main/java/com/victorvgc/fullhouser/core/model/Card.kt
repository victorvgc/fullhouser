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
            val suit: String
            val value: String

            if (s.length == 2) {
                suit = s[1].toString()
                value = if (s[0].toString() == "0") "10" else s[0].toString()
            } else {
                suit = s[2].toString()
                value = s.substring(0, 2)
            }

            return Card(suit, value)
        }
    }

    @IgnoredOnParcel
    val img: String = if (value != "10")
        "${value.toUpperCase(Locale.getDefault())}${suit.toUpperCase(Locale.getDefault())}.png"
    else
        "0${suit.toUpperCase(Locale.getDefault())}.png"

    override fun toString(): String {
        return if (value != "10")
            "$value$suit"
        else
            "0$suit"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Card)
            return false

        return this.suit.equals(other.suit, true) && this.value.equals(other.value, true)
    }

    override fun hashCode(): Int {
        var result = suit.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + img.hashCode()
        return result
    }
}