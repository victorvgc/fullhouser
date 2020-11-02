package com.victorvgc.fullhouser.flow_two.data_source

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.core.constants.SharedPreferencesConstants
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
class FlowTwoLocalDataSourceImplTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    // SUT
    private lateinit var sut: FlowTwoLocalDeckDataSourceImpl

    // MOCKS
    private val mockSharedPreferences = mock(SharedPreferences::class.java)

    // USEFUL VARIABLES
    private val testDeckId = "deckID00001"

    @Before
    fun setup() {
        sut = FlowTwoLocalDeckDataSourceImpl(mockSharedPreferences)
    }

    @Test
    fun `when getDeckId fetch deckId from sharedPreferences`() {
        testCoroutineRule.runBlockingTest {
            // arrange
            `when`(mockSharedPreferences.getString(anyString(), anyString())).thenReturn(testDeckId)

            // act
            val result = sut.getDeckId()

            // assert
            verify(mockSharedPreferences, times(1)).getString(SharedPreferencesConstants.deckId, "")

            assertEquals(testDeckId, result)
        }
    }
}