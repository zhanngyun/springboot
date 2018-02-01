package com.baidu.ueditor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuqw on 2017/7/14.
 */
public class StreamUtil {
    private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);

    public static byte[] toBytes(InputStream input, int size) throws IOException {
        size = size < 1 ? 1 : size;
        int length = 1024 * size;
        byte[] buffer = new byte[length];

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        StreamUtil.close(input);
        return output.toByteArray();
    }

    public static final InputStream toInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    public static void close(InputStream input){
        try {
            if(input != null){
                input.close();
            }
        } catch (Exception e) {
            log.info("关闭输入流出现异常！");
            e.printStackTrace();
        }
    }
}
