package com.victorvgc.fullhouser.flow_one.model

import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FormTest {
    // SUT
    private lateinit var sut: Form

    @Before
    fun setup() {
        sut = Form()
    }

    @Test
    fun `form is valid`() {
        // arrange
        sut.cardFields.rotCard = "AS"
        sut.cardFields.card0 = "2C"
        sut.cardFields.card1 = "KD"

        // act
        val result = sut.isValid()

        // assert
        assertTrue("form is not valid", result)
        assertEquals("Error message should be empty", 0, sut.error)
    }

    @Test
    fun `card input is valid`() {
        // arrange
        val cardInput1 = "AC"
        val cardInput2 = "2d"
        val cardInput3 = "10H"
        val cardInput4 = "Ks"
        val cardInput5 = "Qc"
        val cardInput6 = "JD"

        // act
        val result1 = cardInput1.isCardValid()
        val result2 = cardInput2.isCardValid()
        val result3 = cardInput3.isCardValid()
        val result4 = cardInput4.isCardValid()
        val result5 = cardInput5.isCardValid()
        val result6 = cardInput6.isCardValid()

        // assert
        assertTrue("card input is not valid", result1)
        assertTrue("card input is not valid", result2)
        assertTrue("card input is not valid", result3)
        assertTrue("card input is not valid", result4)
        assertTrue("card input is not valid", result5)
        assertTrue("card input is not valid", result6)
    }

    @Test
    fun `card input is not valid`() {
        // arrange
        val cardInput1 = "CA"
        val cardInput2 = "d2"
        val cardInput3 = "H10"
        val cardInput4 = "sK"
        val cardInput5 = "cQ"
        val cardInput6 = "DJ"

        // act
        val result1 = cardInput1.isCardValid()
        val result2 = cardInput2.isCardValid()
        val result3 = cardInput3.isCardValid()
        val result4 = cardInput4.isCardValid()
        val result5 = cardInput5.isCardValid()
        val result6 = cardInput6.isCardValid()

        // assert
        assertFalse("card input is valid", result1)
        assertFalse("card input is valid", result2)
        assertFalse("card input is valid", result3)
        assertFalse("card input is valid", result4)
        assertFalse("card input is valid", result5)
        assertFalse("card input is valid", result6)
    }
}