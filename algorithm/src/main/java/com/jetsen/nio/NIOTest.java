package com.jetsen.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {
    public static void copyFileUseNIO(String src,String dst) throws IOException {
        FileInputStream fi = new FileInputStream(new File(src));
        FileOutputStream fo = new FileOutputStream(new File(dst));

        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            int eof = inChannel.read(buffer);
            if(eof==-1){
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
    }

    public static void main(String[] args) {
        String inStr = "resources/in/《疯狂Spring Cloud》电子书（一）.pdf";
        String outStr = "resources/out/《疯狂Spring Cloud》电子书（一）.pdf";

        try {
            copyFileUseNIO(inStr,outStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
