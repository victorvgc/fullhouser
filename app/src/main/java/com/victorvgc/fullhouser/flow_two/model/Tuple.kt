package com.victorvgc.fullhouser.flow_two.model

import android.os.Parcelable
import com.victorvgc.fullhouser.core.model.Card
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class Tuple(val c1: Card, val c2: Card, val c3: Card) : Parcelable {
    @IgnoredOnParcel
    private val cards = listOf(c1, c2, c3)

    override fun equals(other: Any?): Boolean {
        if (other !is Tuple)
            return false

        return other.cards.contains(c1) && other.cards.contains(c2) && other.cards.contains(c3)
    }

    override fun hashCode(): Int {
        var result = c1.hashCode()
        result = 31 * result + c2.hashCode()
        result = 31 * result + c3.hashCode()
        result = 31 * result + cards.hashCode()
        return result
    }

    override fun toString(): String {
        return "$c1, $c2, $c3"
    }
}