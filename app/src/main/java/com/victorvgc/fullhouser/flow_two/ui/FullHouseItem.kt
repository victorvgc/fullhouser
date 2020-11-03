package com.victorvgc.fullhouser.flow_two.ui

import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.databinding.FullHouseItemBinding
import com.victorvgc.fullhouser.flow_two.model.FullHouse

class FullHouseItem(private val binding: FullHouseItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(fullHouse: FullHouse) {
        binding.apply {
            this.fullHouse = fullHouse

            executePendingBindings()
        }
    }
}