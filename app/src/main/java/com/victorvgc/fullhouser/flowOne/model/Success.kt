package com.victorvgc.fullhouser.flowOne.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Success: Parcelable {
    override fun equals(other: Any?): Boolean {
        return other is Success
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}