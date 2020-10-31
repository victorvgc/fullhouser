package com.victorvgc.fullhouser.flowOne.ui

import android.os.Bundle
import com.victorvgc.fullhouser.R
import com.victorvgc.fullhouser.core.BaseActivity
import com.victorvgc.fullhouser.databinding.ActivityFlowOneBinding
import com.victorvgc.fullhouser.flowOne.FlowOneViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FlowOneActivity : BaseActivity() {

    private val binding: ActivityFlowOneBinding by binding(R.layout.activity_flow_one)
    private val viewModel: FlowOneViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FlowOneActivity

            viewModel = this@FlowOneActivity.viewModel
        }
    }
}