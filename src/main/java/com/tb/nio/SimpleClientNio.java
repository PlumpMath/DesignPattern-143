package com.tb.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by yangzhuo02 on 2016/1/12.
 */
public class SimpleClientNio {
    public static void main(String[] args) {
        SimpleClientNio simpleClientNio = new SimpleClientNio();
        simpleClientNio.testWrite();
    }

    private void testWrite() {

        try {
//            SocketChannel socketChannel = SocketChannel.open();
//            Selector selector = Selector.open();
//            socketChannel.configureBlocking(false);
//            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
//
//
//
//            boolean isConnect = socketChannel.connect(new InetSocketAddress(5508));
//
//            System.out.println(isConnect);


            InetSocketAddress addr = new InetSocketAddress(5508);
            SocketChannel socketChannel = SocketChannel.open();

            Selector selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            // Á¬½Óµ½server
            boolean isConnect = socketChannel.connect(addr);
            while (!socketChannel.finishConnect()) {
                System.out.println("check finish connection");
            }

            String ss = "Server,how are you?";
            ByteBuffer buffer = ByteBuffer.wrap(ss.getBytes("UTF-8"));
            while (buffer.hasRemaining()) {
                System.out.println("buffer.hasRemaining() is true.");
                socketChannel.write(buffer);
            }

            System.out.println("client");
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }

}
