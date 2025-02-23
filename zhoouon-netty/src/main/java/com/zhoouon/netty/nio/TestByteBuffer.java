package com.zhoouon.netty.nio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024/10/26 13:47
 * @Version: 1.0.0
 **/
@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {

        try {
            // 通过使用FileInputStream 读取文件，并转换成FileChannel
            FileChannel channel = new FileInputStream(ResourceUtils.getFile("classpath:data.txt")).getChannel();
            //创建buffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // 循环读取所有的内容
            while (true) {
                // 当使用 Channel的read方法读取完所有内容时，返回-1，并结束循环
                int len = channel.read(buffer);
                if (len == -1) {
                    break;
                }
                // 将buffer切换成读模式
                buffer.flip();
                // 判断buffer中是否还有剩余的字节
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                // 切换成写模式，是为了将读模式写入到ByteBuffer中的数据全部打印出来
                buffer.clear();
            }
        } catch (IOException e) {
            log.error("读取数据错误: {}", e.getMessage());
        }
    }
}
