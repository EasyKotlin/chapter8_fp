package com.easy.kotlin

import com.easy.kotlin.F3.testF3

/**
 * Created by jack on 2017/7/7.
 */

fun factorial(n: Int): Int {
    println("factorial() called!  n=$n")
    if (n == 0) return 1;
    return n * factorial(n - 1);
}


/**
 * 1、1、2、3、5、8、13、21、34、……
 * F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
 */

fun fibonacci(n: Int): Int {
    if (n == 1 || n == 2) return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
}


fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4)
    val doubled = list.map({ value -> value * 2 })
    println(doubled)

    val addOneAnonymouse = (fun(x: Int): Int {
        return x + 1
    })
    println(addOneAnonymouse(1))

    val addOneLambda = {
        x: Int ->
        x + 1
    }
    println(addOneLambda(1))

    val square = (fun(x: Int): Int {
        return x * x
    })

    println(square(12))


    /**
     * 让匿名函数递归
     * 由于不能给函数命名，我们需要把函数作为参数传入一个高阶函数。
     * 这样，在高阶函数中，就可以使用 参数名 来引用函数，相当于变相地给函数命了名。
     */

    // 「用 lambda 表达式写出递归」，不能在 lambda 定义完成之前直接引用自身。
//    val call = (fun(n: Int): Int {
//        if (n == 0) return 1
//        return n * call(n - 1) // unresolved reference : call
//    })

//    call(3)


//    var call = { n: Int -> if (n == 0) 1 else n * call(n - 1) }

    var factorial1 = F1.fact()
    println("factorial1(10) = ${factorial1(10)}")

    var factorial2 = F2.fact(F2.rec())
    println("factorial2(3) = ${factorial2(3)}")
    println("factorial2(10) = ${factorial2(10)}")

    val fact2 = F2.fact(F2.rec())
    println("fact2(10)=${fact2(10)}")

    testF3()
}

object F1 {
    fun fact(): (Int) -> Int {
        return fun(n: Int): Int {
            if (n == 0) return 1
            return n * fact()(n - 1) // The recursion at this stage is still fairly explicit.
            // The call function needs to be aware of its own name.
            // Let's parameterize the recursive call: see F2
        }
    }
}

object F2 {

    fun fact(rec: (Int) -> Int): (Int) -> Int {
        return { n: Int ->
            if (n == 0) 1
            else n * rec(n - 1)
        }
    }

    fun rec(): (Int) -> Int {
        return { x -> fact(rec()).invoke(x) }
    }
}

object F3 {

    fun fact(rec: (Int) -> Int): (Int) -> Int {
        return { n: Int ->
            if (n == 0) 1
            else n * rec(n - 1)
        }
    }

    fun recurser(f: (Int) -> Int): (Int) -> Int {
        return fact { x -> f(f(x)) }
    }

    fun testF3() {

    }

}

