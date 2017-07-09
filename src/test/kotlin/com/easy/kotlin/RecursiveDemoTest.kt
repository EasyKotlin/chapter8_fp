package com.easy.kotlin

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by jack on 2017/7/7.
 */
@RunWith(JUnit4::class)
class RecursiveDemoTest {

    @Test
    fun testFactorial() {
        Assert.assertTrue(factorial(0) == 1)
        Assert.assertTrue(factorial(1) == 1)
        Assert.assertTrue(factorial(3) == 6)
        Assert.assertTrue(factorial(10) == 3628800)
    }

    @Test
    fun testFibonacci() {
        Assert.assertTrue(fibonacci(1) == 1)
        Assert.assertTrue(fibonacci(2) == 1)
        Assert.assertTrue(fibonacci(3) == 2)
        Assert.assertTrue(fibonacci(4) == 3)
        Assert.assertTrue(fibonacci(5) == 5)
        Assert.assertTrue(fibonacci(6) == 8)
    }

}
