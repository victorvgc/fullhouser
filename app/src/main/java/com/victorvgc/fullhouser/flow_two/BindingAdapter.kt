package com.victorvgc.fullhouser.flow_two

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.flow_two.model.FullHouse
import com.victorvgc.fullhouser.flow_two.ui.DeckAdapter
import com.victorvgc.fullhouser.flow_two.ui.FullHouseAdapter

@BindingAdapter("deck")
fun bindDeck(view: RecyclerView, deck: Deck?) {
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