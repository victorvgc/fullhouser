package com.victorvgc.fullhouser.flow_two

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.flow_two.model.FullHouse
import com.victorvgc.fullhouser.flow_two.ui.DeckAdapter
import com.victorvgc.fullhouser.flow_two.ui.FullHouseAdapter

@BindingAdapter("deck")
fun bindDeck(view: RecyclerView, deck: Deck?) {
    val layoutManager = GridLayoutManager(view.context, 5)
    view.layoutManager = layoutManager
    view.adapter = DeckAdapter().apply {
        this.deck = deck ?: Deck("", emptyList(), Card("", ""))
        notifyDataSetChanged()
    }
}

@BindingAdapter("fullHouse")
fun bindFullHouse(view: RecyclerView, fullHouses: List<FullHouse>?) {
    view.adapter = FullHouseAdapter().apply {
        this.fullHouseList = fullHouses ?: emptyList()
        notifyDataSetChanged()
    }
}

@BindingAdapter("fullHouseCount")
fun bindFullHouseCount(view: TextView, fullHouses: List<FullHouse>?) {
    if (fullHouses == null || fullHouses.isEmpty())
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}