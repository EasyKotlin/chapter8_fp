package com.easy.kotlin

/**
 * Created by jack on 2017/7/9.
 *
 * lambda f. (lambda x. (f(x x)) lambda x. (f(x x)))
 *
 * FP YCombinator
 */

// 为了方便易懂，使用 X 用做函数 (X)->Int 的别名
typealias X = (Int) -> Int
// G 递归引用 G 自己
interface G : Function1<G, X>
// create a fun G from lazy blocking
fun G(block: (G) -> X) = object : G {
    // 调用函数自身 `block(g)` like as `g(g)`
    override fun invoke(g: G) = block(g)
}

typealias F = Function1<X, X>

fun Y(f: F) = (fun(g: G) = g(g))(G { g -> f({ x -> g(g)(x) }) })

val fact = Y {
    rec ->
    {
        n ->
        if (n == 0) 1 else n * rec(n - 1)
    }
}
val fib = Y { f ->
    {
        n ->
        if (n == 1 || n == 2) 1 else f(n - 1) + f(n - 2)

    }
}


fun main(args: Array<String>) {
    val square: X = { x -> x * x }
    println(square(9))

    println(fact(10))
    println(fib(10))

    for (i in 1..10) {
        println("$i!= ${fact(i)}")
    }

    for (i in 1..10) {
        println("fib($i) = ${fib(i)}")
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





























