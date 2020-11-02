package com.victorvgc.fullhouser.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.victorvgc.fullhouser.core.constants.NetworkConstants
import com.victorvgc.fullhouser.core.model.Card

@BindingAdapter("loadCard")
fun bindCardImage(view: ImageView, card: Card?) {
    card.let {
        Glide.with(view)
            .load(NetworkConstants.BASE_CARD_URL + it?.img)
            .into(view)
    }
}