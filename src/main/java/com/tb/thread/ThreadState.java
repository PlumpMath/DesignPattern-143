package com.tb.thread;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * 该程序是实现了线程的各个状态
 * 需要通过jstack来查看线程
 * Created by yangzhuo on 16-1-9.
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new SleepThread()).start();
        new Thread(new WaitThread()).start();
        new Thread(new BlockThread()).start();
        new Thread(new BlockThread()).start();
    }

    static class SleepThread implements Runnable {

        @Override
        public void run() {
            SleepUnit.sleep(1000);
        }
    }

    static class WaitThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WaitThread.class) {
                    try {
                        WaitThread.class.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    static class BlockThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (BlockThread.class) {
                    SleepUnit.sleep(1000);
                }
            }
        }
    }

    static class SleepUnit {
        public static void sleep(int second) {
            try {
                TimeUnit.SECONDS.sleep(second);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
