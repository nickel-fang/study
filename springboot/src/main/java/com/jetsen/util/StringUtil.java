package com.jetsen.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringUtil {
    public static String toPinYin(String str) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = str.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]).append("_");
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.toPinYin("我家那闺女2"));
        System.out.println(StringUtil.toPinYin("声临其境3"));
        System.out.println(StringUtil.toPinYin("婚前21天"));
        System.out.println(StringUtil.toPinYin("创造101"));
        System.out.println(StringUtil.toPinYin("歌手2020"));
        System.out.println(StringUtil.toPinYin("横冲直撞20岁2"));
    }
}
