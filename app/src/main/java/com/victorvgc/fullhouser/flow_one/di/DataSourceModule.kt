package com.victorvgc.fullhouser.flow_one.di

import com.victorvgc.fullhouser.flow_one.data_source.FlowOneLocalDeckDataSource
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneLocalDeckDataSourceImpl
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneRemoteDeckDataSource
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneRemoteDeckDataSourceImpl
import org.koin.dsl.module

val flowOneDataSourceModule = module {
    single<FlowOneRemoteDeckDataSource> { FlowOneRemoteDeckDataSourceImpl(get()) }
    single<FlowOneLocalDeckDataSource> { FlowOneLocalDeckDataSourceImpl(get()) }
}