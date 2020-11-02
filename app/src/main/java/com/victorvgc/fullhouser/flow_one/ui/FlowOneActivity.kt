package com.victorvgc.fullhouser.flow_one.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.victorvgc.fullhouser.R
import com.victorvgc.fullhouser.core.BaseActivity
import com.victorvgc.fullhouser.databinding.ActivityFlowOneBinding
import com.victorvgc.fullhouser.flow_one.FlowOneViewModel
import com.victorvgc.fullhouser.flow_one.model.Loading
import com.victorvgc.fullhouser.flow_one.model.Success
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

        viewModel.getState().observe(this) {
            binding.pbSubmit.visibility = View.GONE
            when (it) {
                is Loading -> {
                    binding.pbSubmit.visibility = View.VISIBLE
                }
                is Success -> {
                    /*Intent().let {
                        startActivity(it)
                    }*/
                    Toast.makeText(
                        this,
                        "TODO: Call Flow 2",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Error -> {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.error_something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.error_something_went_really_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}