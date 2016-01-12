package com.tb.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * BIO¿Í»§¶Ë
 * Created by yangzhuo02 on 2016/1/12.
 */
public class SimpleClientSocket {
    public static void main(String[] args) {

        int threadCount = 100;
        for (int index = 0; index < threadCount; ++index) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleClientSocket simpleClientSocket = new SimpleClientSocket();
                    simpleClientSocket.testWrite();
                }
            });
            thread.start();
        }


    }

    private void testWrite() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5506));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println("test\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }


}
