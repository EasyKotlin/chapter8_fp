// I wonder if there's any use in attempting to build this from the ground up. Let's see. Here's a basic, recursive factorial function:

function factorial(n) {
    return n == 0 ? 1 : n * factorial(n - 1);
}

// =============================================
// Let's refactor and create a new function called call that returns an anonymous factorial-computing function instead of performing the calculation itself:

function fact() {
    return function (n) {
        return n == 0 ? 1 : n * fact()(n - 1);
    };
}

var factorial = fact();

// =============================================

// That's a little weird, but there's nothing wrong with it. We're just generating a new factorial function at each step.
// The recursion at this stage is still fairly explicit. The call function needs to be aware of its own name. Let's parameterize the recursive call:

function fact(recurse) {
    return function (n) {
        return n == 0 ? 1 : n * recurse(n - 1);
    };
}

function recurser(x) {
    return fact(recurser)(x);
}

var factorial = fact(recurser);
// That's great, but recurser still needs to know its own name. Let's parameterize that, too:

// =============================================
function recurser(f) {
    return fact(function (x) {
        return f(f)(x);
    });
}

var factorial = recurser(recurser);
// Now, instead of calling recurser(recurser) directly, let's create a wrapper function that returns its result:
// =============================================================================================================

function Y() {
    return (function (f) {
        return f(f);
    })(recurser);
}

var factorial = Y();
// We can now get rid of the recurser name altogether; it's just an argument to Y's inner function, which can be replaced with the function itself:

function Y() {
    return (function (f) {
        return f(f);
    })(function (f) {
        return fact(function (x) {
            return f(f)(x);
        });
    });
}

var factorial = Y();
// The only external name still referenced is call, but it should be clear by now that that's easily parameterized, too, creating the complete, generic, solution:

// ========================================================
// Y = lambda f. ( lambda x. f(x x) )  (lambda x. f(x x) )
// ========================================================

function Y(f) { // Y f = f   ===    f: ((T)->T) -> ((T)->T)       Y: ((T)->T) -> ((T)->T)
    console.log("f=" + f);
    // f=function (rec) {
    //     return function (n) {
    //         return n == 0 ? 1 : n * rec(n - 1);
    //     };
    // }
    return (function (g) {  //  ( g ) -> ((T)-> T)
        console.log("gA=" + g)  // g
        // gA=function (g) {
        //     console.log("gB=" + g);
        //     return f(function (x) {
        //         console.log("x=" + x)
        //         console.log("gB(gB)(x)=" + g(g)(x) + " x=" + x);
        //         return g(g)(x);
        //     });
        // }
        console.log("gA(gA) = " + g(g))
        // gA(gA) = function (n) {
        //     return n == 0 ? 1 : n * rec(n - 1);
        // }
        return g(g);
    })(function (g) {
        console.log("gB=" + g);

        // gB=function (g) {
        //     console.log("gB=" + g);
        //     return f(function (x) {
        //         console.log("x=" + x)
        //         console.log("gB(gB)(x)=" + g(g)(x) + " x=" + x);
        //         return g(g)(x);
        //     });
        // }

        return f(function (x) {
            console.log("gB(gB)(x)=" + g(g)(x) + " x=" + x);
            // gB(gB)(x)=6 x=3
            return g(g)(x);
        });
    });
}


Y(function (rec) {
    return function (n) {
        return n == 0 ? 1 : n * rec(n - 1);
    };
})(3);


var fact = Y(function (rec) {
    return function (n) {
        return n == 0 ? 1 : n * rec(n - 1);
    };
});


var factorial = Y(function (recurse) {
    return function (n) {
        return n == 0 ? 1 : n * recurse(n - 1);
    };
});


var y_combinator = function (fn) {
    return (function (u) {
        return u(u);
    })(function (x) {
        return fn(function () {
            return x(x).apply(null, arguments);
        });
    });
};

y_combinator(function (fab) {
    return function (n) {
        return n <= 1 ? 1 : n * fab(n - 1);
    };
})(10);



