package com.victorvgc.fullhouser.flowOne.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.model.Response
import com.victorvgc.fullhouser.core.utils.toParamString
import com.victorvgc.fullhouser.flowOne.failure.APIFailure
import com.victorvgc.fullhouser.flowOne.service.DeckService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDeckDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: RemoteDeckDataSourceImpl

    // MOCKS
    private val mockDeckService = mock(DeckService::class.java)

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
    private val testResponse = Response(true, testDeckId, true, testCardsList.size)
    private val testFailureResponse = Response(false, testDeckId, true, testCardsList.size)

    // USEFUL VARIABLES
    private suspend fun setupSuccessfulRequest() {
        `when`(mockDeckService.saveDeck(anyString())).thenAnswer {
            testResponse
        }
    }

    private suspend fun setupFailureRequest() {
        `when`(mockDeckService.saveDeck(anyString())).thenAnswer {
            testFailureResponse
        }
    }

    @Before
    fun setup() {
        sut = RemoteDeckDataSourceImpl(mockDeckService)
    }

    @Test
    fun `when saveDeck called put cards list as string param`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRequest()

            // act
            sut.saveDeck(testDeck)

            // assert
            verify(mockDeckService, times(1)).saveDeck(testCardsList.toParamString())
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
}