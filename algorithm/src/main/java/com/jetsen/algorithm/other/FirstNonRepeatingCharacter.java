package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstNonRepeatingCharacter {
    public static char firstNonRepeatingCharacter2(String str) {
        int[] repeat = new int[26];
        for (char c : str.toCharArray()) repeat[c - 'a']++;

        for (char c : str.toCharArray()) {
            if (repeat[c - 'a'] == 1) return c;
        }
        return '-';
    }

    public static char firstNonRepeatingCharacter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.indexOf(str.charAt(i)) == str.lastIndexOf(str.charAt(i)))
                return str.charAt(i);
        }
        return '-';
    }

    @Test
    public void testFirstNonRepeatingCharacter() {
        assertEquals(firstNonRepeatingCharacter("aaabcccdeeef"), 'b');
        assertEquals(firstNonRepeatingCharacter("abcbad"), 'c');
        assertEquals(firstNonRepeatingCharacter("abcabcabc"), '-');
    }
}
