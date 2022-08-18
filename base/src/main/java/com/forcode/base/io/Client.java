package com.forcode.base.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws IOException {
//        BioClient();
        NioClient();
    }

    private static void BioClient() throws IOException {
        System.out.println(">>>>>>>...BIO客户端启动...>>>>>>>>");
        // 1.创建Socket并根据IP地址与端口连接服务端
        Socket socket = new Socket("127.0.0.1", 8888);
        // 2.从Socket对象中获取一个字节输出流并封装成输出对象
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        // 3.通过输出对象往服务端传递信息
        printStream.println("Hello！我是熊猫~");
        // 4.通过下述方法告诉服务端已经完成发送，接下来只接收消息
        socket.shutdownOutput();
        // 5.从套接字中获取字节输入流并封装成输入对象
        InputStream inputStream = socket.getInputStream();
        BufferedReader readBuffer =
                new BufferedReader(new InputStreamReader(inputStream));
        // 6.通过输入对象从Buffer读取信息
        String msg;
        while ((msg = readBuffer.readLine()) != null) {
            System.out.println("收到信息：" + msg);
        }
        // 7.发送后清空输出流中的信息
        printStream.flush();
        // 8.使用完成后关闭流对象与套接字
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    private static void NioClient() throws IOException {
        System.out.println(">>>>>>>...NIO客户端启动...>>>>>>>>");
        // 1.创建一个TCP类型的通道并指定地址建立连接
        SocketChannel channel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1",8888));
        // 2.将通道置为非阻塞模式
        channel.configureBlocking(false);
        // 3.创建字节缓冲区，并写入要传输的消息数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String msg = "我是熊猫！";
        buffer.put(msg.getBytes());
        // 4.将缓冲区切换为读取模式
        buffer.flip();
        // 5.将带有数据的缓冲区写入通道，利用通道传输数据
        channel.write(buffer);
        // 6.传输完成后情况缓冲区、关闭通道
        buffer.clear();
        channel.close();
    }
}
