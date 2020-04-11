package com.jetsen.jdk8;

import java.util.*;
import java.util.stream.*;

/*
 * Stream 自己不会存储元素。
 * Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * Java8 中的 Collection 接口被扩展，提供了 两个获取流的方法：
 * default Stream<E> stream() : 返回一个顺序流
 * default Stream<E> parallelStream() : 返回一个并行流
 *
 * Java8 中的 Arrays 的静态方法 stream() 可以获取数组流：
 * static <T> Stream<T> stream(T[] array): 返回一个流
 *
 * 由值创建流 Stream.of()
 * public static <T> Stream<T> of(T...values)
 *
 * 由函数创建流Stream.iterate(), Stream.generate()
 * */
public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        int[] ints = new int[10];
        IntStream stream1 = Arrays.stream(ints);

        System.out.println(LongStream.rangeClosed(0, 1000000000L).parallel().reduce(0, Long::sum));


        //最终操作: collect
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("zhangsan1", 20, 95));
        persons.add(new Person("zhangsan2", 30, 85));
        persons.add(new Person("zhangsan3", 40, 75));
        persons.add(new Person().setName("zhangsan4").setAge(50).setScore(65));

        Stream<Person> personStream = persons.stream();
        Map<String, Person> personMap = personStream.collect(Collectors.toMap(per -> per.getName(), per -> per));
        System.out.println(personMap);

        //最终操作: reduce
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5, 6, 7);
        Optional<Integer> reduce = s1.reduce((n1, n2) -> n1 + n2);

        Stream<Person> personStream2 = persons.stream();
        Person temp = new Person();
        Optional<Person> reduce1 = personStream2.reduce((n1, n2) -> temp.setScore(n1.getScore() + n2.getScore()));
        System.out.println(reduce1.get().getScore());

        //最终操作: max min
        Stream<Person> personStream3 = persons.stream();
        Optional<Person> max = personStream3.max((p1, p2) -> p1.getScore() - p2.getScore());
        System.out.println(max.get().getScore());

        //最终操作: matching : anyMatch,allMatch,noneMatch
        Stream<Person> personStream4 = persons.stream();
        boolean b = personStream4.allMatch(p -> p.getScore() > 70);
        System.out.println(b);

        //最终操作: count
        Stream<Person> personStream5 = persons.stream();
        long count = personStream5.count();
        System.out.println(count);

        //最终操作: forEach
        Stream<Person> personStream6 = persons.stream();
//        personStream6.forEach(p -> System.out.println(p));
        personStream6.forEach(System.out::print);

        //中间操作: filter
        persons.stream().filter(p -> p.getScore() > 70).forEach(System.out::println);

        //中间操作: distinct
        //去重， 根据对象的hash值，或对象的equals
        System.out.println("distinct:");
        persons.stream().distinct().forEach(System.out::println);

        //中间操作: sort
        // sorted(),无参时，流中对象需实现Comparable接口,
        // sorted(Comparator)
        persons.stream().sorted((p1, p2) -> p2.getAge() - p1.getAge()).forEach(System.out::println);

        //中间操作: skip & limit
        System.out.println("----skip & limit----");
        persons.stream().limit(2).forEach(System.out::println);
        persons.stream().skip(1).limit(2).forEach(System.out::println);

        //中间操作: map
        System.out.println("----map----");
        persons.stream().map(p -> p.getName()).forEach(System.out::println);

        System.out.println("----平均成绩----");
        Optional<Integer> reduce2 = persons.stream()
                .filter(p -> p.getScore() >= 60)
                .map(p -> p.getScore())
                .reduce((p1, p2) -> p1 + p2);
        int total = (int) persons.stream().count();
        System.out.println(reduce2.get() / total);

        System.out.println("-----findFirst, findAny------");
        System.out.println(persons.stream().parallel().findFirst());
        System.out.println(persons.stream().findAny());
        System.out.println(persons.stream().parallel().findAny());

        System.out.println("----flapMap----");
        String[] array = {"hello", "world"};
        System.out.println(Arrays.stream(array)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList()));

        //Collectors工具类
        // toList(), toSet(), toMap()
        // maxBy(), minBy()
        // joining()
        // summingInt(), averagingInt(), summarizingInt()
        System.out.println(persons.stream().collect(Collectors.maxBy((p1, p2) -> p1.getScore() - p2.getScore())));
        System.out.println(persons.stream().map(Person::getName).collect(Collectors.joining()));
        System.out.println(persons.stream().map(Person::getName).collect(Collectors.joining("-")));
        System.out.println(persons.stream().collect(Collectors.summingInt(Person::getScore)));
        System.out.println(persons.stream().collect(Collectors.averagingInt(Person::getScore)).intValue());
        System.out.println(persons.stream().collect(Collectors.summarizingInt(Person::getScore)));
    }
}
