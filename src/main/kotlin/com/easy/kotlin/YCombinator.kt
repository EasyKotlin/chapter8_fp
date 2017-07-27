package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 *
 * lambda f. (lambda x. (f(x x)) lambda x. (f(x x)))
 *
 *
 */

/**
 * OOP YCombinator
 */
object YCombinatorKt {

    fun yCombinator(f: Lambda<Lambda<*>>): Lambda<Lambda<*>> {

        return object : Lambda<Lambda<*>> {

            override fun call(n: Any): Lambda<*> {
                val u = n as Lambda<Lambda<*>>
                return u.call(u)
            }
        }.call(object : Lambda<Lambda<*>> {

            override fun call(n: Any): Lambda<*> {
                val x = n as Lambda<Lambda<*>>

                return f.call(object : Lambda<Any> {
                    override fun call(n: Any): Any {
                        return x.call(x).call(n)!!
                    }
                })
            }

        }) as Lambda<Lambda<*>>
    }

    @JvmStatic fun main(args: Array<String>) {

        val y = yCombinator(object : Lambda<Lambda<*>> {

            override fun call(n: Any): Lambda<*> {
                val fab = n as Lambda<Int>

                return object : Lambda<Int> {

                    override fun call(n: Any): Int {
                        val n = Integer.parseInt(n.toString())
                        if (n < 2) {
                            return Integer.valueOf(1)
                        } else {
                            return n * fab.call(n - 1)
                        }
                    }
                }
            }
        })

        println(y.call(10)) //输出： 3628800
    }

    interface Lambda<E> {
        fun call(n: Any): E
    }
}

//用CoffeeScript实现一个 Y combinator就长这样：
//coffee> Y = (f) -> ((x) -> (x x)) ((x) -> (f ((y) -> ((x x) y))))

/*

function Y(f) {
    return (function (g) {
        return g(g);
    }) (function (g) {
        return f(function (x) {
            return g(g)(x);
        });
    });
}

*/


//fun <T> Y(f: (((T) -> T) -> ((T) -> T))): (((T) -> T) -> ((T) -> T)) {
//    return (fun(g: ((T) -> T) -> ((T) -> T)): ((T) -> T) -> ((T) -> T) {
//        return { x -> g(g((x))) }
//    })(fun(g: ((T) -> T) -> ((T) -> T)): (((T) -> T) -> ((T) -> T)) {
//        return f(fun <T> fn(x: ((T) -> T) -> ((T) -> T)): ((T) -> T) -> ((T) -> T) {
//            return { p -> g(g(p(x))) }
//        })
//    })
//}


//Y(f) = (g => f(g(g))) (g => f(g(g)))



/**
 * FP YCombinator
 */




























