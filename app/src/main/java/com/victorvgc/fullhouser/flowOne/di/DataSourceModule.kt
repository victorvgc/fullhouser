package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSourceImpl
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSourceImpl
import org.koin.dsl.module

val flowOneDataSourceModule = module {
    single<RemoteDeckDataSource> { RemoteDeckDataSourceImpl(get())}
    single<LocalDeckDataSource> { LocalDeckDataSourceImpl(get())}
}