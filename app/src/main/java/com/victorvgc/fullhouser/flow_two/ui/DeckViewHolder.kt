package com.victorvgc.fullhouser.flow_two.ui

import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.databinding.CardItemBinding

class DeckViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Card) {
        binding.apply {
            this.card = card

            executePendingBindings()
        }
    }
}