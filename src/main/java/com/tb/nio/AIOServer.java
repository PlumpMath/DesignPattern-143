package com.tb.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangzhuo02 on 2016/1/14.
 */
public class AIOServer {
    public static void main(String[] args) {
        try {
            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(channelGroup).bind(new InetSocketAddress("0.0.0.0", 8013));

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                @Override
                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    server.accept(null, this);
                    String now = "success";
                    ByteBuffer buffer = ByteBuffer.wrap(now.getBytes());

                    Future<Integer> future = result.write(buffer);
                    int num = 0;
                    try {
                        num = future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                    }
                    if(num > 0) {
                        System.out.println("success");
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    exc.printStackTrace();
                }
            });
            channelGroup.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (IOException  | InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
