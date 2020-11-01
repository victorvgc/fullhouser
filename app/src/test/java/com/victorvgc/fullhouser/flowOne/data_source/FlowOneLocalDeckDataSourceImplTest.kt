package com.victorvgc.fullhouser.flowOne.data_source

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.constants.SharedPreferencesConstants
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FlowOneLocalDeckDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowOneLocalDeckDataSourceImpl

    // MOCKS
    private val mockSharedPreferences = mock(SharedPreferences::class.java)
    private val mockSharedPreferencesEditor = mock(SharedPreferences.Editor::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "A")
    private val testDeck = Deck(testDeckId, emptyList(), testRotCard)

    // USEFUL METHODS
    private fun setupPreferences() {
        `when`(mockSharedPreferences.edit()).thenReturn(mockSharedPreferencesEditor)
        `when`(
            mockSharedPreferencesEditor.putString(
                anyString(),
                anyString()
            )
        ).thenReturn(
            mockSharedPreferencesEditor
        )
    }

    @Before
    fun setup() {
        sut = FlowOneLocalDeckDataSourceImpl(mockSharedPreferences)
    }

    @Test
    fun `when saveDeck called put deckId on SharedPreferences`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupPreferences()

            // act
            sut.saveDeck(testDeck)

            // assert
            verify(mockSharedPreferences, times(1)).edit()
            verify(mockSharedPreferencesEditor, times(1))
                .putString(
            SharedPreferencesConstants.deckId,
            testDeckId
                )

            verify(mockSharedPreferencesEditor, times(1)).apply()
        }
    }
}