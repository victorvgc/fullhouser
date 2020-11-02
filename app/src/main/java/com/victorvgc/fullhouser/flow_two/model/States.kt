package com.victorvgc.fullhouser.flow_two.model

import com.victorvgc.fullhouser.core.model.State
import kotlinx.android.parcel.Parcelize

@Parcelize
class DeckNotFound : State() {
    override fun equals(other: Any?): Boolean {
        return other is DeckNotFound
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

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