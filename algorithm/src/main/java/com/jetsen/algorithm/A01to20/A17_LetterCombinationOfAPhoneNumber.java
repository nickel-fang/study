package com.jetsen.algorithm.A01to20;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class A17_LetterCombinationOfAPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String[]> digitToLetters = new HashMap<Character, String[]>() { //initialize the value
            {
                put('2', new String[]{"a", "b", "c"});
                put('3', new String[]{"d", "e", "f"});
                put('4', new String[]{"g", "h", "i"});
                put('5', new String[]{"j", "k", "l"});
                put('6', new String[]{"m", "n", "o"});
                put('7', new String[]{"p", "q", "r", "s"});
                put('8', new String[]{"t", "u", "v"});
                put('9', new String[]{"w", "x", "y", "z"});

            }
        };

        return getRecursive(digitToLetters, digits);
    }

    public static List<String> getRecursive(Map<Character, String[]> digitToLetters, String digits) {
        List<String> result = new ArrayList<>();

        if (!digits.isEmpty() && digits.length() == 1) {
            return Arrays.asList(digitToLetters.get(digits.charAt(0)));
        } else if (!digits.isEmpty() && digits.length() != 1) {
            char digit = digits.charAt(0);
            digits = digits.substring(1);
            for (String ch : digitToLetters.get(digit)) {
                for (String str : getRecursive(digitToLetters, digits))
                    result.add(ch + str);
            }

        }
        return result;
    }

    @Test
    public void testLetterCombinations() {
//        assertIterableEquals(letterCombinations("2"), Arrays.asList("a", "b", "c"));
        assertIterableEquals(letterCombinations("23"), Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
    }
}
