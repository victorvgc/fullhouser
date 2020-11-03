package com.victorvgc.fullhouser.flow_two.di

import com.victorvgc.fullhouser.flow_two.service.FlowTwoDeckService
import org.koin.dsl.module
import retrofit2.Retrofit

val flowTwoNetworkModule = module {
    single<FlowTwoDeckService> { get<Retrofit>().create(FlowTwoDeckService::class.java) }
}