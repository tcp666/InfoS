package com.keshe.tools;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @ClassName DeflaterCompress
 * @Description TODO
 * @Author Haining
 * @Date 2020/6/1 11:36
 * @Version 1.0
 */
public class DeflaterCompress {

    //压缩算法
    public byte[] compress(byte[] data){
        // 构建压缩器
        Deflater compressor = new Deflater();
        compressor.setInput(data);
        compressor.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        try {
            while (!compressor.finished()) {
                int compressLen = compressor.deflate(buffer);
                bos.write(buffer, 0, compressLen);
            }
        } finally {
            // 释放资源
            compressor.end();
        }

        return bos.toByteArray();
    }


    //解压文件
    public byte[] uncompress(byte[] data){
        // 构建解压器
        Inflater decompressor = new Inflater();
        decompressor.setInput(data);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        try {
            while (!decompressor.finished()) {
                int decompressLen = decompressor.inflate(buffer);
                bos.write(buffer, 0, decompressLen);
            }
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            decompressor.end();
        }

        return bos.toByteArray();
    }
}
