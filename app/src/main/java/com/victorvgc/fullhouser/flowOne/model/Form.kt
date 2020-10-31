package com.victorvgc.fullhouser.flowOne.model

import androidx.annotation.VisibleForTesting
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victorvgc.fullhouser.BR
import com.victorvgc.fullhouser.R
import java.util.*

class Form : BaseObservable() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val cardFields = CardFields()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var error = 0

    private val buttonCLick = MutableLiveData<CardFields>()

    @Bindable
    fun isValid(): Boolean {

        val cardList = mutableListOf<String>()

        if (cardFields.card0.isNotEmpty()) cardList.add(cardFields.card0)
        if (cardFields.card1.isNotEmpty()) cardList.add(cardFields.card1)
        if (cardFields.card2.isNotEmpty()) cardList.add(cardFields.card2)
        if (cardFields.card3.isNotEmpty()) cardList.add(cardFields.card3)
        if (cardFields.card4.isNotEmpty()) cardList.add(cardFields.card4)
        if (cardFields.card5.isNotEmpty()) cardList.add(cardFields.card5)
        if (cardFields.card6.isNotEmpty()) cardList.add(cardFields.card6)
        if (cardFields.card7.isNotEmpty()) cardList.add(cardFields.card7)
        if (cardFields.card8.isNotEmpty()) cardList.add(cardFields.card8)
        if (cardFields.card8.isNotEmpty()) cardList.add(cardFields.card9)

        if (cardList.isEmpty()) {
            error = 0

            notifyPropertyChanged(BR.formMsg)
            notifyPropertyChanged(BR.valid)

            return false
        }

        if (cardFields.rotCard.isEmpty()) {
            error = R.string.error_rot_card_empty

            notifyPropertyChanged(BR.formMsg)
            notifyPropertyChanged(BR.valid)

            return false
        }

        cardList.add(cardFields.rotCard)

        var isCardsValid = true

        for (card in cardList) {
            if (!card.isCardValid()) {
                isCardsValid = false
                break
            }
        }

        if (!isCardsValid) {
            error = R.string.error_cards_not_valid

            notifyPropertyChanged(BR.formMsg)
            notifyPropertyChanged(BR.valid)

            return false
        }

        var isUniqueCardsSet = true

        for (i in 0 until cardList.size) {
            if (isUniqueCardsSet) {
                for (j in (i + 1) until cardList.size) {
                    val c1 = cardList[i]
                    val c2 = cardList[j]
                    if (c1.equals(c2, true)) {
                        isUniqueCardsSet = false
                        break
                    }
                }
            } else {
                break
            }
        }

        if (!isUniqueCardsSet) {
            error = R.string.error_unique_card_set

            notifyPropertyChanged(BR.formMsg)
            notifyPropertyChanged(BR.valid)

            return false
        }

        error = 0
        notifyPropertyChanged(BR.formMsg)
        notifyPropertyChanged(BR.valid)

        return true
    }

    fun onClick() {
        if (isValid()) {
            buttonCLick.value = cardFields
        }
    }

    fun getCardFields(): LiveData<CardFields> {
        return buttonCLick
    }

    @Bindable
    fun getFormMsg(): Int {
        return error
    }
}

fun String.isCardValid(): Boolean {
    // must be between 2..3 chars
    if (this.length !in 2..3) {
        return false
    }

    val cardRegex = "[AKQJ|[2-9]][HCDS]".toRegex()
    val card10Regex = "10[HCDS]".toRegex()

    return (toUpperCase(Locale.getDefault()).matches(cardRegex)
            || toUpperCase(Locale.getDefault()).matches(card10Regex))
}