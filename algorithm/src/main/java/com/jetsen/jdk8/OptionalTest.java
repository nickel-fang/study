package com.jetsen.jdk8;

import java.util.Optional;

/**
 * @author: Nickel Fang
 * @date: 2020/12/7 17:08
 */
public class OptionalTest {
    public static void main(String[] args) {
//        Optional<Person> person = Optional.of(new Person());
        Optional<Person> person = Optional.empty();
//        Optional<String> name = person.map(Person::getName).map(Car::getInsurance).map(Insurance::getName);
        Optional<String> s1 = person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName);
        System.out.println(s1);
        String result = person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("unknown");
        System.out.println(result);

        person.filter(p -> p.getAge() > 30).flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("unknown");
    }
}
