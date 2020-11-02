package com.victorvgc.fullhouser.flow_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flow_one.model.Error
import com.victorvgc.fullhouser.flow_one.model.State
import com.victorvgc.fullhouser.flow_one.model.Success
import com.victorvgc.fullhouser.flow_one.repository.FlowOneDeckRepository
import com.victorvgc.fullhouser.getOrAwaitValue
import io.mockk.MockKAnnotations
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FlowOneViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowOneViewModel

    // MOCKS
    private val mockDeckRepository = mock(FlowOneDeckRepository::class.java)

    @Mock
    private lateinit var mockObserver: Observer<State>

    // USEFUL VARIABLES
    private val testUserNewCardInput = "AS"
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "A")
    private val testCardsList = listOf(
        Card("S", "A"),
        Card("H", "A"),
        Card("C", "A"),
        Card("D", "A")
    )
    private val testDeck = Deck("", testCardsList, testRotCard)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        sut = FlowOneViewModel(mockDeckRepository)
    }

    private fun setupSuccessForm() {
        sut.form.cards.addAll(testDeck.toString().split(",").subList(0, testCardsList.size))
        sut.form.rotCard = testRotCard.toString()
    }

    @Test
    fun `should set Success state when onSubmit successful`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            sut.getState().observeForever(mockObserver)

            `when`(mockDeckRepository.saveDeck(any(Deck::class.java))).thenAnswer {
                Right(Success())
            }

            setupSuccessForm()

            // act
            sut.onSubmit()

            // assert
            assertEquals(Success(), sut.getState().getOrAwaitValue())
        }
    }

    @Test
    fun `should set Error state when onSubmit fails`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            sut.getState().observeForever(mockObserver)

            `when`(mockDeckRepository.saveDeck(any(Deck::class.java))).thenAnswer {
                Left(SomethingWentWrongFailure())
            }

            setupSuccessForm()

            // act
            sut.onSubmit()

            // assert
            assertEquals(Error(), sut.getState().getOrAwaitValue())
        }
    }

    @After
    fun tearDown() {
        sut.getState().removeObserver(mockObserver)
    }
}