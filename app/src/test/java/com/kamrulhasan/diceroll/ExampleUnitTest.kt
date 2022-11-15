package com.kamrulhasan.diceroll

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    //@Test
    fun takingNumber(): Int{
        val dice = Dice()
        //assertNotEquals(5, dice.roll())
       return dice.roll()
    }
    @Test
    fun generates_number(){
        assertTrue("The value is 6",
            takingNumber() == 6)
    }

    @Test
    fun isNotNull(){
        val value : String? = null
        assertNotNull(value)
    }

    @Test
    fun isNull(){
        val value : String = "Hello BJIT Academy"
        assertNull(value)
    }

    @Test
    fun isTrue(){
        val a = 21753
        val x = a%10
        assertTrue("The number end with non zero digit",
                    x in 1..9)
    }

    @Test
    fun isEnmty(){
        val myAgeIs60 = false
        assertFalse("Conition is false.", myAgeIs60)
    }
}





