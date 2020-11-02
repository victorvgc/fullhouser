package com.victorvgc.fullhouser.flow_one.di

import com.victorvgc.fullhouser.flow_one.FlowOneViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FlowOneViewModel(get()) }
}