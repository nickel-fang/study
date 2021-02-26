package com.jetsen.jdk8;

import java.util.Optional;
import java.util.Random;

public class Person {
    String name;
    int age;
    int score;

    public Optional<Car> getCar() {
        return car;
    }

    private Optional<Car> car;

    public Person() {
    }

    public Person(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Person setScore(int score) {
        this.score = score;
        return this;
    }

    @Override
    public int hashCode() {
        return new Random().nextInt(10);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        Person o = (Person) object;
        return age == o.getAge() && name == o.getName() && score == o.getScore();
    }

    @Override
    public String toString() {
        return "name:" + name + "age:" + age + "score:" + score;
    }
}
