package com.jetsen.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Nickel Fang
 * @date: 2020/11/30 15:35
 */
@Data
@AllArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
