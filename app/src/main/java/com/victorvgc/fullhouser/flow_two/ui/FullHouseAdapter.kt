package com.victorvgc.fullhouser.flow_two.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.R
import com.victorvgc.fullhouser.databinding.FullHouseItemBinding
import com.victorvgc.fullhouser.flow_two.model.FullHouse

class FullHouseAdapter : RecyclerView.Adapter<FullHouseItem>() {

    var fullHouseList: List<FullHouse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullHouseItem =
        DataBindingUtil.inflate<FullHouseItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.full_house_item,
            parent,
            false
        ).let { FullHouseItem(it) }

    override fun onBindViewHolder(holder: FullHouseItem, position: Int) =
        holder.bind(fullHouseList[position])

    override fun getItemCount(): Int = fullHouseList.size
}