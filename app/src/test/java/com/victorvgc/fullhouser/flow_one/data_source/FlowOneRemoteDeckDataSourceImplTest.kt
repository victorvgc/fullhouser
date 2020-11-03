package com.victorvgc.fullhouser.flow_one.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.model.Response
import com.victorvgc.fullhouser.core.utils.APIFailure
import com.victorvgc.fullhouser.core.utils.toParamString
import com.victorvgc.fullhouser.flow_one.service.FlowOneDeckService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FlowOneRemoteDeckDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowOneRemoteDeckDataSourceImpl

    // MOCKS
    private val mockDeckService = mock(FlowOneDeckService::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "2")
    private val testCardsList = listOf(
        Card("S", "A"),
        Card("H", "A"),
        Card("C", "A"),
        Card("D", "A")
    )
    private val testDeck = Deck("testDeckId", testCardsList, testRotCard)
    private val testResponse = Response(true, testDeckId, true, testCardsList.size, emptyMap())
    private val testFailureResponse =
        Response(false, testDeckId, true, testCardsList.size, emptyMap())

    // USEFUL METHODS
    private suspend fun setupSuccessfulRequest() {
        `when`(mockDeckService.saveDeck(anyString())).thenAnswer {
            testResponse
        }

        `when`(mockDeckService.createDeckPile(anyString(), anyString())).thenAnswer {
            testResponse
        }

        `when`(mockDeckService.createRotPile(anyString(), anyString())).thenAnswer {
            testResponse
        }

        `when`(mockDeckService.drawCards(anyString(), anyInt())).thenAnswer {
            testResponse
        }
    }

    private suspend fun setupFailureRequest() {
        `when`(mockDeckService.saveDeck(anyString())).thenAnswer {
            testFailureResponse
        }

        `when`(mockDeckService.createDeckPile(anyString(), anyString())).thenAnswer {
            testFailureResponse
        }

        `when`(mockDeckService.createRotPile(anyString(), anyString())).thenAnswer {
            testFailureResponse
        }

        `when`(mockDeckService.drawCards(anyString(), anyInt())).thenAnswer {
            testFailureResponse
        }
    }

    @Before
    fun setup() {
        sut = FlowOneRemoteDeckDataSourceImpl(mockDeckService)
    }

    @Test
    fun `when saveDeck called send deck as String`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            sut.saveDeck(testDeck)

            // assert
            verify(mockDeckService, times(1)).saveDeck(testDeck.toString())
        }
    }

    @Test
    fun `when saveDeck called success return a deck with deckId from Response Right Side`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            val result = sut.saveDeck(testDeck)

            // assert
            val expected = Right(Deck(testDeckId, testCardsList, testRotCard))
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveDeck called and receives an API failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRequest()

            // act
            val result = sut.saveDeck(testDeck)

            // assert
            val expected = Left(APIFailure())
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveDeckPile called send deck id, and cards in deck as a pile`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            sut.savePile(testDeck)

            // assert
            verify(mockDeckService, times(1)).createDeckPile(
                testDeck.id,
                testDeck.cards.toParamString()
            )
        }
    }

    @Test
    fun `when saveDeckPile called success return a deck with deckId from Response Right Side`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            val result = sut.savePile(testDeck)

            // assert
            val expected = Right(testDeck)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveDeckPile called and receives an API failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRequest()

            // act
            val result = sut.savePile(testDeck)

            // assert
            val expected = Left(APIFailure())
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveRotPile called send rotation card to a deck pile`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            sut.saveRotCard(testDeck)

            // assert
            verify(mockDeckService, times(1)).createRotPile(
                testDeck.id,
                testDeck.rotCard.toString()
            )
        }
    }

    @Test
    fun `when saveRotPile called success return a deck with deckId from Response Right Side`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            val result = sut.saveRotCard(testDeck)

            // assert
            val expected = Right(testDeck)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveRotPile called and receives an API failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRequest()

            // act
            val result = sut.saveRotCard(testDeck)

            // assert
            val expected = Left(APIFailure())
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when drawAllCards send deck cards plus rot card sum to be draw`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            sut.drawAllCards(testDeck)

            // assert
            verify(mockDeckService, times(1))
                .drawCards(testDeck.id, testDeck.cards.size + 1)
        }
    }

    @Test
    fun `when drawAllCards called success return a deck with deckId from Response Right Side`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            val result = sut.drawAllCards(testDeck)

            // assert
            val expected = Right(testDeck)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `when drawAllCards called and receives an API failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRequest()

            // act
            val result = sut.drawAllCards(testDeck)

            // assert
            val expected = Left(APIFailure())
            assertEquals(expected, result)
        }
    }
}