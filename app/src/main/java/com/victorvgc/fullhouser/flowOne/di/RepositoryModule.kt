package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.repository.DeckRepository
import com.victorvgc.fullhouser.flowOne.repository.DeckRepositoryImpl
import org.koin.dsl.module

val flowOneRepositoryModule = module {
    single<DeckRepository> { DeckRepositoryImpl(get(), get()) }
}