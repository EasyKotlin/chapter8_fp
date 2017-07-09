package com.easy.kotlin

import kotlin.coroutines.experimental.buildSequence


/**
 * Created by jack on 2017/7/10.
 */

fun main(args: Array<String>) {
    val fibonacciSeq = buildSequence {
        var x = 0
        var y = 1
        println("START ")

        yield(1)

        while (true) {
            yield(x + y)
            println("STEP")
            val next = x + y
            x = y
            y = next
        }
        println("END")
    }

    println(fibonacciSeq.take(10).forEach {
        println("$it")
    })

}
