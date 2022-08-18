package com.forcode.base.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    public static void main(String[] args) throws IOException {
//        BioServer();
        NioServer();
    }

    static class Temp{

        List<String> data = Lists.newArrayList("a", "b");

        public List<String> getData() {
            return data;
        }
    }

    private static void BioServer() throws IOException {
        System.out.println(">>>>>>>...BIO服务端启动...>>>>>>>>");
        // 1.定义一个ServerSocket服务端对象，并为其绑定端口号
        ServerSocket server = new ServerSocket(8888);
        while (true) {

            // 2.监听客户端Socket连接
            // 未监听到连接会阻塞住
            Socket socket = server.accept();
            // 3.从套接字中得到字节输入流并封装成输入流对象
            InputStream inputStream = socket.getInputStream();
            BufferedReader readBuffer =
                    new BufferedReader(new InputStreamReader(inputStream));
            // 4.从Buffer中读取信息，如果读到信息则输出
            String msg;
            while ((msg = readBuffer.readLine()) != null) {
                System.out.println("收到信息：" + msg);
            }
            // 5.从套接字中获取字节输出流并封装成输出对象
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            // 6.通过输出对象往服务端传递信息
            printStream.println("Hi！我是竹子~");
            // 7.发送后清空输出流中的信息
            printStream.flush();
            // 8.使用完成后关闭流对象与套接字
            outputStream.close();
            inputStream.close();
            socket.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        }
//        server.close();
    }

    private static void NioServer() throws IOException {
        System.out.println(">>>>>>>...NIO服务端启动...>>>>>>>>");
        // 1.创建服务端通道、选择器与字节缓冲区
        ServerSocketChannel ssc = ServerSocketChannel.open();
        Selector selector = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 2.为服务端绑定IP地址+端口
        ssc.bind(new InetSocketAddress("127.0.0.1", 8888));
        // 3.将服务端设置为非阻塞模式，同时绑定接收事件注册到选择器
        ssc.configureBlocking(false);
        // OP_READ - 1, OP_WRITE - 4, OP_CONNECT - 8, OP_ACCEPT - 16,
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // 4.通过选择器轮询所有已就绪的通道, 若没有已就绪通道, select()方法会阻塞住
        while (selector.select() > 0) {
            // 5.获取当前选择器上注册的通道中所有已经就绪的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 6.遍历得到的所有事件，并根据事件类型进行处理
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                // 7.如果是接收事件就绪，那则获取对应的客户端连接
                if (next.isAcceptable()) {
                    SocketChannel channel = ssc.accept();
                    // 8.将获取到的客户端连接置为非阻塞模式，绑定事件并注册到选择器上
                    channel.configureBlocking(false);
                    // event = 5
                    int event = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    channel.register(selector, event);
                    System.out.println("客户端连接：" + channel.getRemoteAddress());

                } else if (next.isReadable()) {
                    // 9.如果是读取事件就绪，则先获取对应的通道连接
                    SocketChannel channel = (SocketChannel) next.channel();
                    // 10.然后从对应的通道中，将数据读取到缓冲区并输出
                    int len = -1;
                    while ((len = channel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println("收到信息：" +
                                new String(buffer.array(), 0, buffer.remaining()));
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(atomicInteger.incrementAndGet());
                    buffer.clear();
                }
                // 11.将已经处理后的事件从选择器上移除（选择器不会自动移除）
                // 调用select()方法时会恢复'publicSelectedKeys'属性, 所以selectedKeys()方法又能获取到通道的事件
                iterator.remove();
            }
        }
    }
}
