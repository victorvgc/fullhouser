package com.victorvgc.fullhouser.flow_two.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FullHouse(val tuple: Tuple, val pair: Pair) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other !is FullHouse)
            return false

        return this.tuple == other.tuple && this.pair == other.pair
    }

    override fun hashCode(): Int {
        var result = tuple.hashCode()
        result = 31 * result + pair.hashCode()
        return result
    }

    override fun toString(): String {
        return "$tuple - $pair\n"
    }
}