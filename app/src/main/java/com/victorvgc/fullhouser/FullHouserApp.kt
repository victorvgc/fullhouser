package com.victorvgc.fullhouser

import android.app.Application
import com.victorvgc.fullhouser.core.di.coreNetworkModule
import com.victorvgc.fullhouser.flowOne.di.flowOneDataSourceModule
import com.victorvgc.fullhouser.flowOne.di.flowOneNetworkModule
import com.victorvgc.fullhouser.flowOne.di.flowOneRepositoryModule
import com.victorvgc.fullhouser.flowOne.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FullHouserApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FullHouserApp)
            // core
            modules(coreNetworkModule)

            // flow one
            modules(flowOneNetworkModule)
            modules(flowOneDataSourceModule)
            modules(flowOneRepositoryModule)
            modules(viewModelModule)
        }
    }
}