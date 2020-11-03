package com.victorvgc.fullhouser.core.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataSourceModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            "default",
            Context.MODE_PRIVATE
        )
    }
}