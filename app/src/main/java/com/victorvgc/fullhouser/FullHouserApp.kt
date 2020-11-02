package com.victorvgc.fullhouser

import android.app.Application
import com.victorvgc.fullhouser.core.di.coreDataSourceModule
import com.victorvgc.fullhouser.core.di.coreNetworkModule
import com.victorvgc.fullhouser.flow_one.di.flowOneDataSourceModule
import com.victorvgc.fullhouser.flow_one.di.flowOneNetworkModule
import com.victorvgc.fullhouser.flow_one.di.flowOneRepositoryModule
import com.victorvgc.fullhouser.flow_one.di.flowOneViewModelModule
import com.victorvgc.fullhouser.flow_two.di.flowTwoDataSourceModule
import com.victorvgc.fullhouser.flow_two.di.flowTwoNetworkModule
import com.victorvgc.fullhouser.flow_two.di.flowTwoRepositoryModule
import com.victorvgc.fullhouser.flow_two.di.flowTwoViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FullHouserApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FullHouserApp)
            // core
            modules(coreNetworkModule)
            modules(coreDataSourceModule)

            // flow one
            modules(flowOneNetworkModule)
            modules(flowOneDataSourceModule)
            modules(flowOneRepositoryModule)
            modules(flowOneViewModelModule)

            // flow two
            modules(flowTwoNetworkModule)
            modules(flowTwoDataSourceModule)
            modules(flowTwoRepositoryModule)
            modules(flowTwoViewModelModule)
        }
    }
}