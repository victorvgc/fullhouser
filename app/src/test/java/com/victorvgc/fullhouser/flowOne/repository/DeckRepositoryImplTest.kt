package com.victorvgc.fullhouser.flowOne.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.model.Card
import com.victorvgc.fullhouser.core.model.Deck
import com.victorvgc.fullhouser.core.utils.Failure
import com.victorvgc.fullhouser.flowOne.data_source.LocalDeckDataSource
import com.victorvgc.fullhouser.flowOne.data_source.RemoteDeckDataSource
import com.victorvgc.fullhouser.flowOne.failure.SomethingWentWrongFailure
import com.victorvgc.fullhouser.flowOne.model.Success
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
class DeckRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: DeckRepositoryImpl

    // MOCKS
    private val mockLocalDeckDataSource = mock(LocalDeckDataSource::class.java)
    private val mockRemoteDeckDataSource = mock(RemoteDeckDataSource::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"
    private val testRotCard = Card("H", "A")
    private val testCardsList = listOf(
        Card("S", "A"),
        Card("H", "A"),
        Card("C", "A"),
        Card("D", "A")
    )
    private val testDeck = Deck("testDeckId", testCardsList, testRotCard)

    @Before
    fun setup() {
        sut = DeckRepositoryImpl(mockRemoteDeckDataSource, mockLocalDeckDataSource)
    }

    @Test
    fun `when saveDeck called success return successful Result`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            `when`(mockRemoteDeckDataSource.saveDeck(testDeck)).thenAnswer {
                Right(Deck(testDeckId, testCardsList, testRotCard))
            }
            // act
            val result = sut.saveDeck(testDeck)

            // assert
            val expectedFromRemote = Deck(testDeckId, testCardsList, testRotCard)
            verify(mockRemoteDeckDataSource, times(1)).saveDeck(testDeck)
            verify(mockLocalDeckDataSource, times(1)).saveDeck(expectedFromRemote)

            val expected = Right(Success())

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when saveDeck called fails return SomethingWentWrongFailure`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            `when`(mockRemoteDeckDataSource.saveDeck(testDeck)).thenAnswer {
                Left(Failure("Any failure"))
            }

            // act
            val result = sut.saveDeck(testDeck)

            // arrange
            val expected = Left(SomethingWentWrongFailure())

            assertEquals(expected, result)
        }
    }
}