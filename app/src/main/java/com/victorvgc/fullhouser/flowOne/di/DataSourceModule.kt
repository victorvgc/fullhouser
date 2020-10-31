package com.victorvgc.fullhouser.flowOne.di

import android.content.Context
import android.content.SharedPreferences
import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSourceImpl
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val flowOneDataSourceModule = module {
    single<SharedPreferences> { androidContext().getSharedPreferences("default", Context.MODE_PRIVATE) }

    single<RemoteDeckDataSource> { RemoteDeckDataSourceImpl(get())}
    single<LocalDeckDataSource> { LocalDeckDataSourceImpl(get())}
}