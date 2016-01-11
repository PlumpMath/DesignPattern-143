package com.tb.thread;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程
 * 查看后台线程优先级
 *
 * Created by yangzhuo on 16-1-9.
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonThread());
        thread.setDaemon(true);
        System.out.println(thread.getPriority());
        thread.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(0);
            }
        }).start();
    }

    static class DaemonThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(1);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("end");
            }
        }
    }

}
