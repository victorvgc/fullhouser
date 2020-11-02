package com.victorvgc.fullhouser.flow_one.di

import android.content.Context
import android.content.SharedPreferences
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneLocalDeckDataSource
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneLocalDeckDataSourceImpl
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneRemoteDeckDataSource
import com.victorvgc.fullhouser.flow_one.data_source.FlowOneRemoteDeckDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val flowOneDataSourceModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            "default",
            Context.MODE_PRIVATE
        )
    }

    single<FlowOneRemoteDeckDataSource> { FlowOneRemoteDeckDataSourceImpl(get()) }
    single<FlowOneLocalDeckDataSource> { FlowOneLocalDeckDataSourceImpl(get()) }
}