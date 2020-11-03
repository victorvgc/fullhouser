package com.victorvgc.fullhouser.flow_two.di

import com.victorvgc.fullhouser.flow_two.FlowTwoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val flowTwoViewModelModule = module {
    viewModel { FlowTwoViewModel(get()) }
}