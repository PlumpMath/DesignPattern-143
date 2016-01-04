package com.tb.singleton;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程安全的双重检查机制,该机制也会带来一下问题
 * 1.性能问题
 * 2.由于jvm会重排序，所以可能引起问题
 * Created by yangzhuo on 16-1-3.
 */
public class SingletonDoubleCheck {

    private static HashSet<SingletonDoubleCheck> hashSet = new HashSet<SingletonDoubleCheck>();
    private  static SingletonDoubleCheck singletonDoubleCheck = null;

    public SingletonDoubleCheck getInstance() {
        if (singletonDoubleCheck == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (singletonDoubleCheck == null) {
                    singletonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singletonDoubleCheck;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        final int threadSize = 100;
        for (int index = 0; index < threadSize; ++index) {
            executorService.submit(new Thread(new Test()));
        }
        executorService.shutdown();
        System.out.println(hashSet.size());
    }

    static class Test implements Runnable {
        public void run() {
            SingletonDoubleCheck singletonDoubleCheck = new SingletonDoubleCheck();
            SingletonDoubleCheck.hashSet.add(singletonDoubleCheck.getInstance());
        }
    }

}
