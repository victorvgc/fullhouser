package com.victorvgc.fullhouser.flow_two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flow_two.model.*
import com.victorvgc.fullhouser.flow_two.repository.FlowTwoDeckRepository
import com.victorvgc.fullhouser.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FlowTwoViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowTwoViewModel

    // MOCKS
    private val mockRepository = mock(FlowTwoDeckRepository::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "2")
    private val testPlayerHandList = listOf(
        Card("S", "A"),
        Card("D", "A"),
        Card("C", "A"),
        Card("H", "K"),
        Card("S", "K")
    )

    private val testPlayerHandSortedList = listOf(
        Card("H", "K"),
        Card("D", "A"),
        Card("C", "A"),
        Card("S", "A"),
        Card("S", "K")
    )

    private val testFullHouseList = listOf(
        FullHouse(
            Tuple(
                Card("D", "A"),
                Card("C", "A"),
                Card("S", "A")
            ), Pair(
                Card("H", "K"),
                Card("S", "K")
            )
        )
    )

    private val testDeck = Deck(testDeckId, testPlayerHandList, testRotCard)
    private val testHighestCard = testPlayerHandSortedList[0]

    // USEFUL METHODS
    private suspend fun setupSuccessfulRepository() {
        `when`(mockRepository.getDeck()).thenAnswer {
            Right(testDeck)
        }
    }

    private suspend fun setupFailureRepository() {
        `when`(mockRepository.getDeck()).thenAnswer {
            Left(Failure("any failure"))
        }
    }

    @Before
    fun setup() {
        sut = FlowTwoViewModel(mockRepository)
    }

    @Test
    fun `when sortDeck order deck cards accordingly rotation card`() {
        testCoroutineRule.runBlockingTest {
            // arrange

            // act
            val result = sut.sortDeck(testDeck)

            // assert
            val expected = Deck(testDeckId, testPlayerHandSortedList, testRotCard)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getFullHouseCombination return all fullHouse combinations from sorted deck`() {
        testCoroutineRule.runBlockingTest {
            // arrange

            // act
            val sorted = sut.sortDeck(testDeck)
            val result = sut.getFullHouseComb(sorted.cards)

            // assert
            assertEquals(testFullHouseList, result)
        }
    }

    @Test
    fun `when getDeck populate deck LiveData with ordered cards`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRepository()

            // act
            sut.getDeck()

            // assert
            val expected = Deck(testDeckId, testPlayerHandSortedList, testRotCard)

            assertEquals(expected, sut.deck.getOrAwaitValue())
        }
    }

    @Test
    fun `when getDeck successful set state to Success`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRepository()

            // act
            sut.getDeck()

            // assert
            assertEquals(Success(), sut.state.getOrAwaitValue())
        }
    }

    @Test
    fun `when getDeck failure set state to DeckNotFound`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRepository()

            // act
            sut.getDeck()

            // assert
            assertEquals(DeckNotFound(), sut.state.getOrAwaitValue())
        }
    }

    @Test
    fun `when getDeck success set highestCard`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRepository()

            // act
            sut.getDeck()

            // assert
            assertEquals(testHighestCard, sut.highestCard.getOrAwaitValue())
        }
    }

    @Test
    fun `when getDeck success set fullHouseList combinations`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRepository()

            // act
            sut.getDeck()

            // assert
            assertEquals(testFullHouseList, sut.fullHouseList.getOrAwaitValue())
        }
    }
}