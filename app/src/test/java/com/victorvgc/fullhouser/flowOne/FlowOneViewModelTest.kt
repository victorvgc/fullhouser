package com.victorvgc.fullhouser.flowOne

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victorvgc.fullhouser.CoroutineRule
import com.victorvgc.fullhouser.flowOne.repository.DeckRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
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
    private val mockDeckRepository = mock(DeckRepository::class.java)

    // USEFUL VARIABLES
    private val testUserNewCardInput = "AS"

    @Before
    fun setup() {
        //sut = FlowOneViewModel(mockDeckRepository)
    }


}