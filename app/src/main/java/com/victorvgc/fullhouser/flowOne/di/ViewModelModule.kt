package com.victorvgc.fullhouser.flowOne.di

import com.victorvgc.fullhouser.flowOne.FlowOneViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FlowOneViewModel(get()) }
}