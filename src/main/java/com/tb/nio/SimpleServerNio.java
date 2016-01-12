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
    public static void main(String[] args) {
        SimpleServerNio simpleServerNio = new SimpleServerNio();
        simpleServerNio.testRead();
    }

    private void testRead() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(5508));


            Selector selector = Selector.open();
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
                        } else if (selectionKey.isReadable()) {
                            System.out.println("is readable");
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int result = socketChannel.read(buffer);
                            while (result != -1) {
                                System.out.println(buffer);
                                result = socketChannel.read(buffer);
                            }
                        } else if (selectionKey.isConnectable()) {
                            System.out.println("is connect");
                        } else if (selectionKey.isWritable()) {
                            System.out.println("is write");
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
