package com.tb.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
 * Created by yangzhuo02 on 2016/1/14.
 */
public class AIOClient {
    public static void main(String[] args) {

        try {
            AsynchronousSocketChannel client =  AsynchronousSocketChannel.open();
            Future<Void> future = client.connect(new InetSocketAddress("127.0.0.1", 8013));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
                    client.read(buffer, null, new CompletionHandler<Integer, Void>() {
                        @Override
                        public void completed(Integer result, Void attachment) {
                            System.out.println(buffer.asCharBuffer());
                            System.out.println(1);
                        }

                        @Override
                        public void failed(Throwable exc, Void attachment) {

                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }
}
