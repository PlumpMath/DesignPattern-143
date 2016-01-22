package com.tb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Condition来实现一个带有边界判断的队列
 * Created by yangzhuo on 16-1-8.
 */
public class TestConditionList {

    private int size = 2000000000;
    private int currentCount = 0;
    private Integer[] array = new Integer[size];
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition notEmpty = reentrantLock.newCondition();
    private Condition notFull = reentrantLock.newCondition();

    public void add(int value) {
        reentrantLock.lock();
        try {
            //为什么是使用while判断
            while (currentCount == size) {
                notFull.await();
            }
            array[currentCount++] = value;
            notEmpty.signal();
        } catch (InterruptedException ex) {

        } finally {
            reentrantLock.unlock();
        }
    }

    public void remove() {
        reentrantLock.lock();
        try {
            while (currentCount == 0) {
                notEmpty.await();
            }
            array[currentCount--] = null;
            notFull.await();
        } catch (InterruptedException ex) {

        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final TestConditionList testConditionList = new TestConditionList();
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int size = 10000000;
                for (int index = 0; index < size; ++index) {
                    testConditionList.add(index);
                }
            }
        });
        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
