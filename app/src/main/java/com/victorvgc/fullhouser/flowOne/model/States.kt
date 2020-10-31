package com.victorvgc.fullhouser.flowOne.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class State : Parcelable


@Parcelize
class Success : State() {
    override fun equals(other: Any?): Boolean {
        return other is Success
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@Parcelize
class Loading : State() {
    override fun equals(other: Any?): Boolean {
        return other is Loading
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

@Parcelize
class Error : State() {
    override fun equals(other: Any?): Boolean {
        return other is Error
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

