package com.jetsen.algorithm.A61to80;

import java.util.HashMap;
import java.util.Map;

public class A76_MinimumWindowsSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int l = 0, r = 0;
        int[] info = {-1, 0, 0};

        Map<Character, Integer> dic = new HashMap<>();

        for (char c : t.toCharArray()) {
            dic.put(c, dic.getOrDefault(c, 0) + 1);
        }

        int distinctCharNumber = 0;

        Map<Character, Integer> countChar = new HashMap<>();

        char[] ss = s.toCharArray();

        while (r < ss.length) {
            if (dic.containsKey(ss[r])) {
                countChar.put(ss[r], countChar.getOrDefault(ss[r], 0) + 1);

                if (countChar.get(ss[r]) == dic.get(ss[r]))
                    distinctCharNumber++;

                //找到了符合的字串
                if (distinctCharNumber == dic.size()) {
                    if (info[0] == -1 || info[0] > r - l + 1) {
                        info[0] = r - l + 1;
                        info[1] = l;
                        info[2] = r;
                    }

                    while (l <= r && distinctCharNumber == dic.size()) {
                        if (dic.containsKey(ss[l])) {
                            countChar.put(ss[l], countChar.get(ss[l]) - 1);
                            if (countChar.get(ss[l]) < dic.get(ss[l]))
                                distinctCharNumber--;
                        }

                        l++;
                        if (distinctCharNumber == dic.size()) {
                            if (info[0] > r - l + 1) {
                                info[0] = r - l + 1;
                                info[1] = l;
                                info[2] = r;
                            }
                        }
                    }
                }
            }
            r++;
        }

        return info[0] == -1 ? "" : s.substring(info[1], info[2] + 1);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
