package com.tb.thread;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by yangzhuo on 16-1-6.
 */
public class CountTask extends RecursiveTask<BigInteger> {

    private BigInteger start = new BigInteger("0");
    private BigInteger end = new BigInteger("0");
    private static final BigInteger THRESHOLD = new BigInteger("100000");

    public CountTask(BigInteger startValue, BigInteger endValue) {
        this.start = startValue;
        this.end = endValue;
    }

    @Override
    protected BigInteger compute() {
        BigInteger sum = new BigInteger("0");
        if (end.subtract(start).compareTo(THRESHOLD) < 0) {
            for (BigInteger index = start; index.compareTo(end) <= 0; index = index.add(new BigInteger("1"))) {
                sum = sum.add(index);
            }
        } else {
            BigInteger middle = start.add(end).divide(new BigInteger("2"));
            CountTask left = new CountTask(start, middle.subtract(new BigInteger("1")));
            CountTask right = new CountTask(middle, end);
            left.fork();
            right.fork();
            BigInteger leftValue = left.join();
            BigInteger rightValue = right.join();
            sum = leftValue.add(rightValue);
        }
        return sum;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        final BigInteger size = new BigInteger("100000000");
        CountTask countTask = new CountTask(new BigInteger("0"), size);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<BigInteger> forkJoinTask = forkJoinPool.submit(countTask);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start));

    }

}
