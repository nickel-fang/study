package com.jetsen.jdk8;

import java.util.Optional;

/**
 * @author: Nickel Fang
 * @date: 2020/12/7 17:06
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
