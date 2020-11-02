package com.victorvgc.fullhouser.flow_one

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victorvgc.fullhouser.core.constants.NetworkConstants

@BindingAdapter("loadCard")
fun bindCardImage(view: ImageView, url: String?) {
    url.let {
        Glide.with(view)
            .load(NetworkConstants.BASE_CARD_URL + it)
            .into(view)
    }
}

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

