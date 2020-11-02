package com.victorvgc.fullhouser.flow_one

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.model.State
import com.victorvgc.fullhouser.flow_one.model.Error
import com.victorvgc.fullhouser.flow_one.model.Form
import com.victorvgc.fullhouser.flow_one.model.Loading
import com.victorvgc.fullhouser.flow_one.model.Success
import com.victorvgc.fullhouser.flow_one.repository.FlowOneDeckRepository
import kotlinx.coroutines.launch

class FlowOneViewModel(private val repository: FlowOneDeckRepository) : ViewModel() {
    val form = Form()

    val onFocusChangedListener = View.OnFocusChangeListener { _, focus ->
        if (!focus)
            form.isValid()
    }

    private val state = MutableLiveData<State>()

    fun onSubmit() {
        viewModelScope.launch {
            state.postValue(Loading())
            val cards = mutableListOf<Card>()

            for (cardInput in form.cards) {
                cards.add(Card.fromString(cardInput))
            }

            val rotCard = Card.fromString(form.rotCard)

            val deck = Deck("", cards, rotCard)

            val result = repository.saveDeck(deck)

            result.fold({
                state.postValue(Error())
            },
                {
                    state.postValue(Success())
                })
        }
    }

    fun getState(): LiveData<State> {
        return state
    }
}