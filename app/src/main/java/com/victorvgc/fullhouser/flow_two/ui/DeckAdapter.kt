package com.victorvgc.fullhouser.flow_two.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.victorvgc.fullhouser.R
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.databinding.CardItemBinding

class DeckAdapter : RecyclerView.Adapter<DeckViewHolder>() {
    var deck: Deck = Deck("", emptyList(), Card("", ""))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder =
        DataBindingUtil.inflate<CardItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.card_item,
            parent,
            false
        ).let { DeckViewHolder(it) }


    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) =
        holder.bind(deck.cards[position])

    override fun getItemCount(): Int = deck.cards.size

}