package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 */


class FPApi {

    fun apply() {
        1.apply { 1 }
    }

    fun closureDemo() {
        Thread({
            for (i in 1..10) {
                println("I = $i")
                Thread.sleep(1000)
            }

        }).start()

        Thread({
            for (j in 10..20) {
                println("J = $j")
                Thread.sleep(2000)
            }
            Thread.sleep(1000)
        }).start()
    }


    fun test() {
        apply().apply { 1 }

        closureDemo()
    }

}

fun main(args: Array<String>) {
    FPApi().test()
}
