package com.jetsen.other;


import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author: Nickel Fang
 * @date: 2020/7/13 10:19
 */
public class FileSizeTest {
    public static void main(String[] args) {
/*        String tt = "123";
        long size = FileUtils.sizeOf(new File("E:\\work/文档/协同处理平台"));
        long size2 = new File("E:\\work\\文档\\协同处理平台").length();
        System.out.println(size / 1024 / 1024);
        System.out.println(FileUtils.byteCountToDisplaySize(size));

        System.out.println(size2);
        System.out.println(FileUtils.byteCountToDisplaySize(size2));

        System.out.println(System.getProperty("os.name"));


        File file = new File("W:\\paonan");
        long totalSpace = file.getTotalSpace();
        int space = (int) (totalSpace / 1024 / 1024 / 1024);
        System.out.println(totalSpace);
        System.out.println(space);*/

        System.out.println("100000000/100000000/100000000/100000000/100000000/100000000/100000000/100000000/100000000/100000000".length());
    }
}
