package com.victorvgc.fullhouser.flow_two.ui

import android.os.Bundle
import com.victorvgc.fullhouser.R
import com.victorvgc.fullhouser.core.BaseActivity
import com.victorvgc.fullhouser.databinding.ActivityFlowTwoBinding
import com.victorvgc.fullhouser.flow_two.FlowTwoViewModel
import com.victorvgc.fullhouser.flow_two.model.Success
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FlowTwoActivity : BaseActivity() {

    private val binding: ActivityFlowTwoBinding by binding(R.layout.activity_flow_two)
    private val viewModel: FlowTwoViewModel by viewModel { parametersOf() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FlowTwoActivity
            viewModel = this@FlowTwoActivity.viewModel
        }

        viewModel.getDeck()

        viewModel.state.observe(this) {
            when (it) {
                is Success -> {

                }
            }
        }
    }
}