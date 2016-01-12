package com.tb.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO·þÎñ¶Ë
 * Created by yangzhuo02 on 2016/1/12.
 */
public class SimpleServerSocket {

    private final static int port = 5506;
    private static InetSocketAddress socketAddress = null;

    static {

        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
        }

    }

    public static void main(String[] args) {
        SimpleServerSocket simpleServerSocket = new SimpleServerSocket();
        simpleServerSocket.testRead();
    }

    private void testRead() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(socketAddress, 5506);

            ExecutorService executorService = Executors.newFixedThreadPool(6);

            while (true) {
                executorService.execute(new Thread(new ServerThread(serverSocket.accept())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    static class ServerThread implements Runnable {

        private Socket socket = null;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            if (this.socket != null) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.printf("socket_%s:%s%s",
                                Thread.currentThread().getName(),
                                line, "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
    }

}
