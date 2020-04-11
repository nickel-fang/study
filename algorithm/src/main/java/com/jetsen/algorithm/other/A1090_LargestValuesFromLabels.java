package com.jetsen.algorithm.other;

import javax.security.auth.callback.LanguageCallback;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class A1090_LargestValuesFromLabels {
    public int largestValuesFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int len = values.length;
        int[][] items = new int[len][2];
        for (int i = 0; i < len; i++) {
            items[i][0] = values[i];
            items[i][1] = labels[i];
        }
        //Arrays.sort(items, Comparator.comparingInt(i -> -i[0]));
        Arrays.sort(items, Comparator.comparingInt(new ToIntFunction<int[]>() {
            @Override
            public int applyAsInt(int[] value) {
                return -value[0];
            }
        }));

        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int[] item : items) {
            int labelCount = map.getOrDefault(item[1], 0);
            if (labelCount < use_limit) {
                result += item[0];
                if (--num_wanted == 0) break;
                map.put(item[1], labelCount + 1);
            }
        }

        return result;
    }
}
