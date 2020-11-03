package com.victorvgc.fullhouser.flow_one.di

import com.victorvgc.fullhouser.flow_one.service.FlowOneDeckService
import org.koin.dsl.module
import retrofit2.Retrofit

val flowOneNetworkModule = module {
    single<FlowOneDeckService> { get<Retrofit>().create(FlowOneDeckService::class.java) }
}