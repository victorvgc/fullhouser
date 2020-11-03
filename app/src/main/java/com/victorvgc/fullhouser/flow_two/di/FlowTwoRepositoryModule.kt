package com.victorvgc.fullhouser.flow_two.di

import com.victorvgc.fullhouser.flow_two.repository.FlowTwoDeckRepository
import com.victorvgc.fullhouser.flow_two.repository.FlowTwoDeckRepositoryImpl
import org.koin.dsl.module

val flowTwoRepositoryModule = module {
    single<FlowTwoDeckRepository> { FlowTwoDeckRepositoryImpl(get(), get()) }
}