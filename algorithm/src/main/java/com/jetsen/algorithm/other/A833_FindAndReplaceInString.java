package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A833_FindAndReplaceInString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int indexTmp;
        String sourceTmp, targetTmp;
        int len = indexes.length;

        //bubble sort
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                if (indexes[j] > indexes[j + 1]) {
                    indexTmp = indexes[j];
                    indexes[j] = indexes[j + 1];
                    indexes[j + 1] = indexTmp;

                    sourceTmp = sources[j];
                    sources[j] = sources[j + 1];
                    sources[j + 1] = sourceTmp;

                    targetTmp = targets[j];
                    targets[j] = targets[j + 1];
                    targets[j + 1] = targetTmp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int point = 0;
        for (int i = 0; i < len; i++) {
            if (S.substring(indexes[i], indexes[i] + sources[i].length()).equals(sources[i])) {
                sb.append(S.substring(point, indexes[i])).append(targets[i]);
                point = indexes[i] + sources[i].length();
            }
        }
        if (point < S.length())
            sb.append(S.substring(point));

        return sb.toString();
    }

    @Test
    public void testFindReplaceString() {
        assertEquals(findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}), "eeebffff");
        assertEquals(findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"}), "eeecd");

    }
}
