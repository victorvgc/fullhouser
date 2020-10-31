package com.victorvgc.fullhouser.flowOne

import android.view.View
import androidx.lifecycle.ViewModel
import com.victorvgc.fullhouser.flowOne.model.Form
import com.victorvgc.fullhouser.flowOne.repository.DeckRepository

class FlowOneViewModel(private val repository: DeckRepository): ViewModel() {
    val form = Form()

    val onFocusChangedListener = View.OnFocusChangeListener { view, focus ->
        if (!focus)
            form.isValid()
    }

    fun onSubmit(){
        form.isValid()
    }
}