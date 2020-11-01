package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.service.FlowOneDeckService
import org.koin.dsl.module
import retrofit2.Retrofit

val flowOneNetworkModule = module {
    single<FlowOneDeckService> { get<Retrofit>().create(FlowOneDeckService::class.java) }
}