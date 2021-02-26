package com.jetsen.stream;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: Nickel Fang
 * @date: 2020/11/30 15:35
 */
public class StreamTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2011, 700),
                new Transaction(alan, 2012, 950)
        );

        /*//1. 找出2011年发生的所有交易，并按交易额排序（从低到高）
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);

        //2. 交易员都在哪些不同的城市工作过
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);

        //3. 查找所有来自于剑桥的交易员，交按姓名排序
        transactions.stream().map(t -> t.getTrader()).filter(t -> t.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);

        //4. 返回所有交易员的姓名字符串，按字母顺序排序
        //transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().forEach(System.out::println);
        String str = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (s1, s2) -> s1 + s2);
        System.out.println(str);
        str = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(str);

        //5. 有没有交换易员是在米兰工作的
        boolean inMilan = transactions.stream().map(t -> t.getTrader().getCity()).anyMatch(c -> c.equals("Milan"));
        System.out.println(inMilan);

        //6. 打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(Integer::sum).ifPresent(System.out::println);

        //7. 所有交易中，最高的交易额是多少
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);

        //8. 找到交易额最小的交易
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().ifPresent(System.out::println);
        transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2).ifPresent(System.out::println);
        transactions.stream().min(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);

        //勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));*/

        //交易中金额最大的交易
        transactions.stream().collect(Collectors.maxBy(Comparator.comparing(Transaction::getValue))).ifPresent(System.out::println);
        transactions.stream().collect(Collectors.summingInt(Transaction::getValue));

//        transactions.stream().collect(Collectors.reducing((t1, t2) -> t1.getValue() + t2.getValue())).ifPresent();
    }


}
