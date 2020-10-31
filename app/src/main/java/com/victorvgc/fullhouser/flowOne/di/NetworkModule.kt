package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.service.DeckService
import org.koin.dsl.module
import retrofit2.Retrofit

val flowOneNetworkModule = module {
    single<DeckService> { get<Retrofit>().create(DeckService::class.java) }
}