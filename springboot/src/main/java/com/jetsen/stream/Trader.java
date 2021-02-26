package com.jetsen.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Nickel Fang
 * @date: 2020/11/30 15:33
 */
@Data
@AllArgsConstructor
public class Trader {
    private String name;
    private String city;

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
