package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RemoteCard(
    val image: String,
    val value: String,
    val suit: String,
    val code: String
) : Parcelable {
    fun toInternalModel(): Card {
        return Card.fromString(code)
    }
}