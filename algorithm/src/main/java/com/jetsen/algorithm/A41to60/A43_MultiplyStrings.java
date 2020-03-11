package com.jetsen.algorithm.A41to60;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A43_MultiplyStrings {

    //Runtime: 2 ms, faster than 99.50%
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        char[] values = new char[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                values[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        int carry = 0;
        for (int i = values.length - 1; i >= 0; i--) {
            values[i] += carry;
            carry = values[i] / 10;
            values[i] %= 10;
        }

        int beginIndex = -1;
        while (beginIndex < values.length - 1 && values[++beginIndex] == 0) ;
        for (int i = beginIndex; i < values.length; i++) values[i] += '0';
        return new String(values, beginIndex, values.length - beginIndex);
    }

    //Runtime: 68 ms, faster than 5.01%
    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.length() > num2.length()) { //swap the two numbers
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        String result = "0";
        int i = num1.length() - 1;
        while (i >= 0) {
            char c = num1.charAt(i--);
            for (int j = 0; j < c - '0'; j++) {
                result = add(result.toString(), num2);
            }
            num2 = num2 + "0";
        }
        return result.toString();
    }

    private String add(String num1, String num2) {
        if (num1.equals("0")) return num2;
        if (num2.equals("0")) return num1;

        if (num1.length() > num2.length()) { //swap the two numbers
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1, tmp;
        while (i >= 0) {
            tmp = num1.charAt(i--) - '0' + num2.charAt(j--) - '0';
            result.insert(0, (tmp + carry) % 10);
            carry = (tmp + carry) / 10;
        }

        while (carry == 1 && j >= 0) {
            tmp = num2.charAt(j--) - '0';
            result.insert(0, (tmp + carry) % 10);
            carry = (tmp + carry) / 10;
        }
        if (j >= 0)
            result.insert(0, num2.substring(0, j + 1));
        else if (carry == 1)
            result.insert(0, 1);
        return result.toString();
    }

    @Test
    public void testAdd() {
        assertEquals(multiply("123", "456"), "56088");
        assertEquals(multiply("56088", "456"), "25576128");
    }
}
