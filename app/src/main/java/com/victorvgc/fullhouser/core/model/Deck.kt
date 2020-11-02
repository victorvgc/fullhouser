package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import com.victorvgc.fullhouser.core.utils.toParamString
import kotlinx.android.parcel.Parcelize

@Parcelize
class Deck(
    val id: String,
    val cards: List<Card>,
    val rotCard: Card
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other is Deck) {
            return this.id == other.id &&
                    this.cards == other.cards &&
                    this.rotCard == other.rotCard
        }

        return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + cards.hashCode()
        result = 31 * result + rotCard.hashCode()
        return result
    }

    override fun toString(): String {
        var str = cards.toParamString()
        str += ",$rotCard"

        return str
    }
}