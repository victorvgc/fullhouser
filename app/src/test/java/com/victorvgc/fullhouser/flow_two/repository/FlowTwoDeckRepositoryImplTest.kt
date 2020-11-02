package com.victorvgc.fullhouser.flow_two.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.model.RemoteCard
import com.victorvgc.fullhouser.core.utils.APIFailure
import com.victorvgc.fullhouser.core.utils.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoLocalDeckDataSource
import com.victorvgc.fullhouser.flow_two.data_source.FlowTwoRemoteDeckDataSource
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
class FlowTwoDeckRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowTwoDeckRepositoryImpl

    // MOCKS
    private val mockLocalDataSource = mock(FlowTwoLocalDeckDataSource::class.java)
    private val mockRemoteDataSource = mock(FlowTwoRemoteDeckDataSource::class.java)

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
    private val testDeck = Deck(testDeckId, testPlayerHandList, testRotCard)

    // USEFUL METHODS
    private suspend fun setupSuccessfulFlow() {
        `when`(mockLocalDataSource.getDeckId()).thenAnswer {
            testDeckId
        }
        `when`(mockRemoteDataSource.getPlayerHand(anyString())).thenAnswer {
            Right(testPlayerHandList)
        }
        `when`(mockRemoteDataSource.getRotCard(anyString())).thenAnswer {
            Right(testRotCard)
        }
    }

    private suspend fun setupLocalFailureFlow() {
        `when`(mockLocalDataSource.getDeckId()).thenAnswer {
            ""
        }
    }

    private suspend fun setupRemotePlayerHandFailureFlow() {
        `when`(mockLocalDataSource.getDeckId()).thenAnswer {
            testDeckId
        }
        `when`(mockRemoteDataSource.getPlayerHand(anyString())).thenAnswer {
            Left(APIFailure())
        }
    }

    private suspend fun setupRemoteRotCardFailureFlow() {
        `when`(mockLocalDataSource.getDeckId()).thenAnswer {
            testDeckId
        }
        `when`(mockRemoteDataSource.getPlayerHand(anyString())).thenAnswer {
            Right(testPlayerHandList)
        }
        `when`(mockRemoteDataSource.getRotCard(anyString())).thenAnswer {
            Left(APIFailure())
        }
    }

    @Before
    fun setup() {
        sut = FlowTwoDeckRepositoryImpl(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `when getDeck called fetch deckId from local and get deck from remote after`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulFlow()

            // act
            sut.getDeck()

            // assert
            val order = inOrder(mockLocalDataSource, mockRemoteDataSource)
            order.verify(mockLocalDataSource, times(1)).getDeckId()
            order.verify(mockRemoteDataSource, times(1)).getPlayerHand(testDeckId)
            order.verify(mockRemoteDataSource, times(1)).getRotCard(testDeckId)
        }
    }

    @Test
    fun `when getDeck successful return Deck`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupSuccessfulFlow()

            // act
            val result = sut.getDeck()

            // assert
            val expected = Right(testDeck)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getDeck local fails returns SomethingWentWrong failure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupLocalFailureFlow()

            // act
            val result = sut.getDeck()

            // assert
            val expected = Left(SomethingWentWrongFailure())

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getDeck remote playerHand fails returns APIFailure failure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupRemotePlayerHandFailureFlow()

            // act
            val result = sut.getDeck()

            // assert
            val expected = Left(APIFailure())

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when getDeck remote rotCard fails returns APIFailure failure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            setupRemoteRotCardFailureFlow()

            // act
            val result = sut.getDeck()

            // assert
            val expected = Left(APIFailure())

            assertEquals(expected, result)
        }
    }
}