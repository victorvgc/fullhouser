package com.victorvgc.fullhouser.flow_one

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("error")
fun setError(view: TextView, msgId: Int) {
    if (msgId == 0)
        view.text = ""
    else {
        view.text = view.context.resources.getString(msgId)
    }
}

@BindingAdapter("onFocus")
fun setOnFocusChangedListener(view: EditText, listener: View.OnFocusChangeListener) {
    if (view.onFocusChangeListener == null)
        view.onFocusChangeListener = listener
}

