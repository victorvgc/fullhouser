package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.repository.FlowOneDeckRepository
import com.victorvgc.fullhouser.flowOne.repository.FlowOneDeckRepositoryImpl
import org.koin.dsl.module

val flowOneRepositoryModule = module {
    single<FlowOneDeckRepository> { FlowOneDeckRepositoryImpl(get(), get()) }
}