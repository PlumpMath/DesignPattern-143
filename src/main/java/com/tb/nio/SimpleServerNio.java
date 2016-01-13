package com.tb.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by yangzhuo02 on 2016/1/12.
 */
public class SimpleServerNio {

    private Selector selector = null;

    public SimpleServerNio() {
        try {
            selector = Selector.open();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SimpleServerNio simpleServerNio = new SimpleServerNio();
        System.out.println("Æô¶¯·þÎñ");
        simpleServerNio.testRead();

    }

    private void testRead() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(5508));
            SelectionKey key = serverSocketChannel.register(selector,
                    SelectionKey.OP_ACCEPT);

            while (true) {
                int select = selector.select();
                if (select > 0) {
                    Iterator iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = (SelectionKey) iterator.next();

                        if (!selectionKey.isValid()) {
                            continue;
                        } else if (selectionKey.isAcceptable()) {
                            System.out.println("is accept");
                            doAccept(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            System.out.println("is readable");

                            doRead(selectionKey);
                        } else if (selectionKey.isConnectable()) {
                            System.out.println("is connect");
                        } else if (selectionKey.isWritable()) {
                            System.out.println("is write");
                        }
                        iterator.remove();
                    }
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void doAccept(SelectionKey selectionKey) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void doRead(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int result = -1;

        try {
            result = socketChannel.read(buffer);

            if (result == -1) {
                socketChannel.close();
                selectionKey.cancel();
            } else {
                buffer.flip();
                System.out.println(buffer);
            }

        } catch (IOException ex) {
            try {
                socketChannel.close();
                selectionKey.cancel();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }


    }

}
