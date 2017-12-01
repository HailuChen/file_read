package com.example.demo.fileRead;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by who on 2017/11/30.
 */
public class ReadFileByNIO {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("G:\\abc.txt");
        FileOutputStream fos = new FileOutputStream("G:\\zxc.txt");

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        while (true) {
            buffer.clear();
            int i = inChannel.read(buffer);

            if (i == -1) {
                break;
            }
            buffer.flip();

            outChannel.write(buffer);
        }
        fis.close();
        fos.close();
    }
}
