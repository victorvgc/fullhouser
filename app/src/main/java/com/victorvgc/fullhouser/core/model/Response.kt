package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    val success: Boolean,
    @SerializedName("deck_id")
    val deckId: String,
    val shuffled: Boolean,
    val remaining: Int,
    val piles: List<Pile>
): Parcelable