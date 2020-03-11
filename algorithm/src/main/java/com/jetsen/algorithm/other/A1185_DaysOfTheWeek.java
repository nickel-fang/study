package com.jetsen.algorithm.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A1185_DaysOfTheWeek {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month - 1, day);
        return weeks[cal.get(java.util.Calendar.DAY_OF_WEEK) - 1];
    }

    //基姆拉尔森计算公式 W= (d+2m+3(m+1)/5+y+y/4-y/100+y/400+1)%7
    public String dayOfTheWeek2(int day, int month, int year) {
        if (month <= 2) {
            month += 12;
            year--;
        }
        int iWeek = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400 + 1) % 7;
        String[] weeks = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return weeks[iWeek];
    }

    @Test
    public void testDayOfTheWeek() {
        assertEquals(dayOfTheWeek2(31, 8, 2019), "Saturday");
    }
}
