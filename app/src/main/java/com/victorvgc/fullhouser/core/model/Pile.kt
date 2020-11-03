package com.victorvgc.fullhouser.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pile(
    val remaining: Int,
    val cards: List<RemoteCard>
) : Parcelable