package com.victorvgc.fullhouser.flow_two.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.*
import com.victorvgc.fullhouser.core.utils.APIFailure
import com.victorvgc.fullhouser.flow_two.service.FlowTwoDeckService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FlowTwoRemoteDeckDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowTwoRemoteDeckDataSourceImpl

    // MOCKS
    private val mockDeckService = Mockito.mock(FlowTwoDeckService::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "2")
    private val testPlayerHandList = listOf(
        Card("S", "A"),
        Card("H", "A"),
        Card("C", "A"),
        Card("D", "A")
    )
    private val testRemoteCardList = listOf(
        RemoteCard("img", "value", "suit", "AS"),
        RemoteCard("img", "value", "suit", "AH"),
        RemoteCard("img", "value", "suit", "AC"),
        RemoteCard("img", "value", "suit", "AD"),
    )

    private val testRemoteRotCard = listOf(
        RemoteCard("img", "value", "suit", "2H")
    )
    private val testPlayerHandPile = listOf(
        Pile(
            testRemoteCardList.size,
            testRemoteCardList
        )
    )

    private val testRotCardPile = listOf(
        Pile(
            testRemoteRotCard.size,
            testRemoteRotCard
        )
    )

    private val testDeck = Deck(testDeckId, testPlayerHandList, testRotCard)
    private val testPlayerHandResponse =
        Response(true, testDeckId, true, testPlayerHandList.size, testPlayerHandPile)
    private val testRotCardResponse =
        Response(true, testDeckId, true, testPlayerHandList.size, testRotCardPile)
    private val testFailureResponse =
        Response(false, testDeckId, true, testPlayerHandList.size, emptyList())

    // USEFUL METHODS
    private suspend fun setupSuccessfulPlayerHandResponse() {
        `when`(mockDeckService.retrievePlayerHand(anyString())).thenAnswer {
            testPlayerHandResponse
        }
    }

    private suspend fun setupFailurePlayerHandResponse() {
        `when`(mockDeckService.retrievePlayerHand(anyString())).thenAnswer {
            testFailureResponse
        }
    }

    private suspend fun setupSuccessfulRotCardResponse() {
        `when`(mockDeckService.retrieveRotCard(anyString())).thenAnswer {
            testRotCardResponse
        }
    }

    private suspend fun setupFailureRotCardResponse() {
        `when`(mockDeckService.retrieveRotCard(anyString())).thenAnswer {
            testFailureResponse
        }
    }

    @Before
    fun setup() {
        sut = FlowTwoRemoteDeckDataSourceImpl(mockDeckService)
    }

    @Test
    fun `when getPlayerHand called send deck id`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulPlayerHandResponse()

            // act
            sut.getPlayerHand(testDeckId)

            // assert
            verify(mockDeckService, times(1)).retrievePlayerHand(testDeckId)
        }
    }

    @Test
    fun `when getPlayerHand called returns successful list of cards`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulPlayerHandResponse()

            // act
            val result = sut.getPlayerHand(testDeckId)

            // assert
            val expected = Right(testPlayerHandList)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getPlayerHand called returns failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailurePlayerHandResponse()

            // act
            val result = sut.getPlayerHand(testDeckId)

            // assert
            val expected = Left(APIFailure())

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getRotCard called send deck id`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRotCardResponse()

            // act
            sut.getRotCard(testDeckId)

            // assert
            verify(mockDeckService, times(1)).retrieveRotCard(testDeckId)
        }
    }

    @Test
    fun `when getRotCard called returns successful list of cards`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulRotCardResponse()

            // act
            val result = sut.getRotCard(testDeckId)

            // assert
            val expected = Right(testRotCard)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getRotCard called returns failure return APIFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupFailureRotCardResponse()

            // act
            val result = sut.getRotCard(testDeckId)

            // assert
            val expected = Left(APIFailure())

            assertEquals(expected, result)
        }
    }
}