package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 */
fun add(x: Int, y: Int): Int {
    return x + y
}

fun curryAdd(x: Int): (Int) -> Int {
    return { y -> x + y }
}

fun testCurry() {
    val a = add(1, 2)
    val c = curryAdd(1)(2)
    println(a)
    println(c)
}

fun main(args: Array<String>) {
    testCurry()

    val lambdaCurryAdd = {
        x: Int ->
        {
            y: Int ->
            x + y
        }
    }

    println(lambdaCurryAdd(1)(2))
}
