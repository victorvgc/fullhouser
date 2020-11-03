package com.victorvgc.fullhouser.flow_two.di

import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoLocalDeckDataSource
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoLocalDeckDataSourceImpl
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoRemoteDeckDataSource
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoRemoteDeckDataSourceImpl
import org.koin.dsl.module

val flowTwoDataSourceModule = module {
    single<FlowTwoLocalDeckDataSource> { FlowTwoLocalDeckDataSourceImpl(get()) }
    single<FlowTwoRemoteDeckDataSource> { FlowTwoRemoteDeckDataSourceImpl(get()) }
}