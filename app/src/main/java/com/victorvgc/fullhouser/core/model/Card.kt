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
    @IgnoredOnParcel
    val img: String =
        "${value.toUpperCase(Locale.getDefault())}${suit.toUpperCase(Locale.getDefault())}.png"

    override fun toString(): String {
        return "$value$suit"
    }
}