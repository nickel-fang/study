package com.jetsen.algorithm.A21to40;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class A30_SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s.length() == 0 || words.length == 0) return new LinkedList();
        int n = words[0].length();
        int length = words.length * n;
        Map<String, Integer> frequencies = new HashMap();
        for (String word : words) frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);

        List<Integer> indices = new ArrayList();
        for (int i = 0; i < n; i++) {
            Map<String, Integer> currentFrequencies = new HashMap();
            int left = i;
            int count = 0;
            for (int right = i + n - 1; right < s.length(); right += n) {
                String rightEndString = s.substring(right - n + 1, right + 1);
                if (frequencies.containsKey(rightEndString)) {
                    currentFrequencies.put(rightEndString, currentFrequencies.getOrDefault(rightEndString, 0) + 1);
                    if (frequencies.get(rightEndString) - currentFrequencies.get(rightEndString) >= 0) {
                        count++;
                    }
                }
                if (right - left + 1 == length) {
                    if (count == words.length) {
                        indices.add(left);
                    }
                    String leftEndString = s.substring(left, left + n);
                    if (frequencies.containsKey(leftEndString)) {
                        currentFrequencies.put(leftEndString, currentFrequencies.getOrDefault(leftEndString, 0) - 1);
                        if (frequencies.get(leftEndString) - currentFrequencies.get(leftEndString) >= 1) {
                            count--;
                        }
                    }
                    left += n;
                }
            }
        }
        return indices;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.isEmpty() || words == null || words.length == 0) return result;
        int len = words[0].length(); //the length of a word

        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);

        for (int i = 0; i <= s.length() - words.length * len; i++) {
            String word = s.substring(i, i + len);

            if (wordsMap.containsKey(word)) {
                Map<String, Integer> tmpWordsMap = new HashMap<>(wordsMap);
                int point = i + len;
                tmpWordsMap.put(word, tmpWordsMap.get(word) - 1);
                int k = word.length() - 1;
                for (int j = 0; j < words.length - 1; j++) {
                    word = s.substring(point, point + len);
                    if (!wordsMap.containsKey(word) || tmpWordsMap.get(word) < 1) break;
                    point += len;
                    tmpWordsMap.put(word, tmpWordsMap.get(word) - 1);
                    k--;
                }

                if (k == 0) result.add(i);
            }
        }
        return result;
    }

    @Test
    public void testFindSubstring() {
        assertIterableEquals(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}), Arrays.asList(new int[]{0, 9}));
        assertIterableEquals(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}), Arrays.asList(new int[]{}));
    }
}
