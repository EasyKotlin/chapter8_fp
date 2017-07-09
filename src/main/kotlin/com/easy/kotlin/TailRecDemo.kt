package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 */

class TailRecDemo {
    fun fact(n: Int): Long {
        if (n == 0) return 1
        return n * fact(n - 1)
    }


    tailrec fun findFixPoint(x: Double = 1.0): Double
            = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


    fun test() {
        fact(100)
        findFixPoint()
    }
}

fun main(args: Array<String>) {
    TailRecDemo().test()
}
