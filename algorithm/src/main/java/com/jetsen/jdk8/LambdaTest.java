package com.jetsen.jdk8;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaTest {

    public static String toUpperString(MyNumber<String> mn, String str) {
        return mn.getValue(str);
    }

    public static String toUpperString(Function<String, String> mn, String str) {
        return mn.apply(str);
    }

    public static void main(String[] args) {

        Runnable run = () -> System.out.println("runnable: lambda test!");
        new Thread(run).start();
        new Thread(() -> System.out.println("runnable: lambda test!")).start();

        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        TreeSet<String> treeSet1 = new TreeSet<>((o1, o2) -> Integer.compare(o1.length(), o2.length()));
        TreeSet<String> treeSet2 = new TreeSet<>(Comparator.comparingInt(String::length));

        /*jdk1.7之前，匿名内部类使用外部变量则外部变量必须变为final，即不可变化
         *在1.8之后，final可省略，但是实际上final还是存在的，在runable内部++num还是报错。
         */
        int num = 0;
        new Thread(() -> System.out.println(num)).start();

        /*
         * 函数式接口
         * Consumer<T> 参数类型 T; 返回类型 void
         * Supplier<T> 参数类型 无; 返回类型 T
         * Function<T,R> 参数类型 T; 返回类型 R
         * Predicate<T> 参数类型 T; 返回类型 boolean
         */

        System.out.println(toUpperString((MyNumber<String>) str -> str.toUpperCase(), "abc"));
        System.out.println(toUpperString((Function<String, String>) str -> str.toUpperCase(), "abc"));

        Consumer<Integer> c = number -> System.out.println(number);
        c.accept(123);

        ((Consumer<Integer>) number -> System.out.println(number)).accept(321);

        /*
         * 对象::实例方法
         * 类::静态方法
         * 类::实例方法
         * */

        PrintStream printStream = System.out;
        ((Consumer<String>) printStream::println).accept("hello wold");
        ((Consumer<String>) str1 -> printStream.println(str1)).accept("hello wold again");

        Comparator<Integer> com = Integer::compare;
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);

        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("a", "b"));

        /*
         * 构造器引用 ClassName::new
         * */
        Function<String, String> fun = str1 -> {
            return new String(str1);
        };

        Function<String, String> fun2 = String::new;
        fun2.apply("123");

        Function<Integer, Integer[]> fun3 = Integer[]::new;
        fun3.apply(10);
    }
}
