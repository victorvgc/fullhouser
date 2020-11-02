package com.victorvgc.fullhouser.flow_two

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.model.State
import com.victorvgc.fullhouser.flow_two.model.*
import com.victorvgc.fullhouser.flow_two.repository.FlowTwoDeckRepository
import com.victorvgc.fullhouser.flow_two.utils.DeckUtils
import kotlinx.coroutines.launch

class FlowTwoViewModel(private val repository: FlowTwoDeckRepository) : ViewModel() {
    val highestCard = MutableLiveData<Card>()
    val deck = MutableLiveData<Deck>()
    val fullHouseList = MutableLiveData<List<FullHouse>>()

    val state = MutableLiveData<State>()

    fun getDeck() {
        viewModelScope.launch {
            state.postValue(Loading())
            val remoteDeckOrFailure = repository.getDeck()

            remoteDeckOrFailure.fold({
                state.postValue(DeckNotFound())
            },
                { remoteDeck ->
                    val sortedDeck = sortDeck(remoteDeck)
                    deck.postValue(sortedDeck)
                    highestCard.postValue(sortedDeck.cards[0])
                    fullHouseList.postValue(getFullHouseComb(sortedDeck.cards))
                    state.postValue(Success())
                })
        }
    }

    fun sortDeck(deck: Deck): Deck {
        val sorted = mutableListOf<Card>()

        var suitStrengthIndex = DeckUtils.suitStrength.indexOf(deck.rotCard.suit)
        val suitStrengthLast = DeckUtils.suitStrength.lastIndexOf(deck.rotCard.suit)

        while (suitStrengthIndex < suitStrengthLast) {
            val suitStrength = DeckUtils.suitStrength[suitStrengthIndex]

            var valueStrengthIndex = DeckUtils.valueStrength.indexOf(deck.rotCard.value)
            val valueStrengthLast = DeckUtils.valueStrength.lastIndexOf(deck.rotCard.value)

            while (valueStrengthIndex < valueStrengthLast) {
                val valueStrength = DeckUtils.valueStrength[valueStrengthIndex]

                for (card in deck.cards) {
                    if (
                        card.suit.equals(suitStrength, true) &&
                        card.value.equals(valueStrength, true)
                    ) {
                        sorted.add(card)
                    }
                }

                valueStrengthIndex++
            }

            suitStrengthIndex++
        }

        return Deck(deck.id, sorted, deck.rotCard)
    }

    fun getFullHouseComb(cards: List<Card>): List<FullHouse> {
        val combinations = mutableListOf<FullHouse>()

        if (cards.size < 5)
            return emptyList()

        val allTuples = getAllTuples(cards)
        val allPairs = getAllPairs(cards)

        for (tuple in allTuples) {
            for (pair in allPairs) {
                if (tuple.c1.value != pair.c1.value) {
                    val fullHouse = FullHouse(tuple, pair)
                    if (!combinations.contains(fullHouse))
                        combinations.add(fullHouse)
                }
            }
        }

        return combinations
    }

    private fun getAllTuples(cards: List<Card>): List<Tuple> {
        val allTuples = mutableListOf<Tuple>()

        for (c1 in cards) {
            for (c2 in cards) {
                for (c3 in cards) {
                    if (c1 != c2 && c2 != c3 && c1 != c3) {
                        if (c1.value == c2.value && c2.value == c3.value) {
                            val tuple = Tuple(c1, c2, c3)
                            if (!allTuples.contains(tuple))
                                allTuples.add(tuple)
                        }
                    }
                }
            }
        }

        return allTuples
    }

    private fun getAllPairs(cards: List<Card>): List<Pair> {
        val allPairs = mutableListOf<Pair>()

        for (c1 in cards) {
            for (c2 in cards) {
                if (c1 != c2) {
                    if (c1.value == c2.value) {
                        val pair = Pair(c1, c2)
                        if (!allPairs.contains(pair))
                            allPairs.add(pair)
                    }
                }
            }
        }

        return allPairs
    }
}