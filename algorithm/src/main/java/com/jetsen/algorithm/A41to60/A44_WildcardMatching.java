package com.jetsen.algorithm.A41to60;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A44_WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (p == null || p.isEmpty()) return s == null || s.isEmpty();
        int i = 0, j = 0, istart = -1, jstart = -1, slen = s.length(), plen = p.length();

        while (i < slen) {
            if (j < plen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < plen && p.charAt(j) == '*') {
                istart = i;
                jstart = j++;
            } else if (istart > -1) {
                i = ++istart;
                j = jstart + 1;
            } else {
                return false;
            }
        }
        while (j < plen && p.charAt(j) == '*') j++;
        return j == plen;
    }

    @Test
    public void testIsMatch() {
        assertEquals(isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"), true);
    }
}
