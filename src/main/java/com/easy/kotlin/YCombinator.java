package com.easy.kotlin;

/**
 * Created by jack on 2017/7/9.
 */
public class YCombinator {
    public static Lambda<Lambda> yCombinator(final Lambda<Lambda> f) {
        return new Lambda<Lambda>() {
            @Override
            public Lambda call(Object input) {
                final Lambda<Lambda> u = (Lambda<Lambda>)input;
                return u.call(u);
            }
        }.call(new Lambda<Lambda>() {
            @Override
            public Lambda call(Object input) {
                final Lambda<Lambda> x = (Lambda<Lambda>)input;
                return f.call(new Lambda<Object>() {
                    @Override
                    public Object call(Object input) {
                        return x.call(x).call(input);
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        Lambda<Lambda> y = yCombinator(new Lambda<Lambda>() {
            @Override
            public Lambda call(Object input) {
                final Lambda<Integer> fab = (Lambda<Integer>)input;
                return new Lambda<Integer>() {
                    @Override
                    public Integer call(Object input) {
                        Integer n = Integer.parseInt(input.toString());
                        if (n < 2) {
                            return Integer.valueOf(1);
                        } else {
                            return n * fab.call(n - 1);
                        }
                    }
                };
            }
        });
        System.out.println(y.call(10));//输出： 3628800
    }

    interface Lambda<E> {
        E call(Object input);
    }
}
