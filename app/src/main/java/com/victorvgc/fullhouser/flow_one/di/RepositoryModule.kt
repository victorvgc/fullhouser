package com.victorvgc.fullhouser.flow_one.di

import com.victorvgc.fullhouser.flow_one.repository.FlowOneDeckRepository
import com.victorvgc.fullhouser.flow_one.repository.FlowOneDeckRepositoryImpl
import org.koin.dsl.module

val flowOneRepositoryModule = module {
    single<FlowOneDeckRepository> { FlowOneDeckRepositoryImpl(get(), get()) }
}