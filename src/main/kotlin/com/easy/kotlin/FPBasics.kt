package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 */

object FPBasics {

    fun double(x: Int): Int {
        return 2 * x
    }

    fun String.swap(index1: Int, index2: Int): String {
        val charArray = this.toCharArray()
        val tmp = charArray[index1]
        charArray[index1] = charArray[index2]
        charArray[index2] = tmp

        return charArrayToString(charArray)
    }

    fun charArrayToString(charArray: CharArray): String {
        var result = ""
        charArray.forEach { it -> result = result + it }
        return result
    }


    fun powerOf(number: Int, exponent: Int): Int {
        return Math.pow(number.toDouble(), exponent.toDouble()).toInt()
    }


    fun add(x: Int = 0, y: Int = 0): Int {
        return x + y
    }


    fun reformat(str: String,
                 normalizeCase: Boolean = true,
                 upperCaseFirstLetter: Boolean = true,
                 divideByCamelHumps: Boolean = false,
                 wordSeparator: Char = ' ') {

    }

    fun printHello(name: String?): Unit {
        if (name != null)
            println("Hello ${name}")
        else
            println("Hi there!")
        // `return Unit` 或者 `return` 是可选的
    }


    fun <T> asList(vararg ts: T): List<T> {
        val result = ArrayList<T>()
        for (t in ts) // ts is an Array
            result.add(t)
        return result
    }


    fun sum(x: Int, y: Int, z: Int): Int {
        val delta = 0;
        fun add(a: Int, b: Int): Int {
            return a + b + delta
        }
        return add(x + add(y, z))
    }


    fun sumGTZero(c: Iterable<Int>): Int {
        var sum = 0
        c.filter { it > 0 }.forEach {
            sum += it
        }
        return sum
    }


    fun isOdd(x: Int): Boolean {
        return x % 2 == 1
    }

    tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


    fun test() {
        val doubleTwo = double(2)
        println("double(2) = $doubleTwo")

        val str = "abcd"
        val swapStr = str.swap(0, str.lastIndex)
        println("str.swap(0, str.lastIndex) = $swapStr")

        val eight = powerOf(2, 3)
        println("powerOf(2,3) = $eight")

        val zero = add()
        val one = add(1)
        val two = add(1, 1)
        println("add() = $zero")
        println("add(1) = $one")
        println("add(1, 1) = $two")

        printHello("Jack")

        println("sum(1,2,3) = ${sum(1, 2, 3)}")

        val list = listOf(1, 2, 3, 4, 5)
        list.filter(FPBasics::isOdd)

        list.filter((fun(x: Int): Boolean {
            return x % 2 == 1
        }))

        list.filter {
            it % 2 == 1
        }

        list.filter({
            it % 2 == 1
        })

        val sumgtZero = FPBasics.sumGTZero(list) //
        println("sumgtZero = $sumgtZero")

//        带接收者的函数字面值
        val sum = fun Int.(other: Int): Int = this + other
        println("1.sum(1)=${1.sum(1)}")

        findFixPoint()

    }

}

fun main(args: Array<String>) {
    FPBasics.test()

    html {
        body()
    }


}


open class DefaultParamBase {
    open fun add(x: Int = 0, y: Int = 0): Int {
        return x + y
    }
}

class DefaultParam : DefaultParamBase() {
    override fun add(x: Int, y: Int): Int { // 不能有默认值
        return super.add(x, y)
    }
}

class HTML {
    fun body() {
        println("HTML BODY")
    }
}

fun html(init: HTML.() -> Unit): HTML { // HTML.() -> Unit 函数类型
    val html = HTML()  // 创建接收者对象
    html.init()        // 将该接收者对象传给该 lambda
    return html
}


