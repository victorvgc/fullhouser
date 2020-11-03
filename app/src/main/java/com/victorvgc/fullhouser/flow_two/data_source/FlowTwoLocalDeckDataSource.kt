package com.victorvgc.fullhouser.flow_two.data_source

interface FlowTwoLocalDeckDataSource {
    suspend fun getDeckId(): String
}