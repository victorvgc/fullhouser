package com.victorvgc.fullhouser.core.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Failure(val msg: String): Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other is Failure) {
            return this.msg == other.msg
        }

        return false
    }

    override fun hashCode(): Int {
        return msg.hashCode()
    }
}