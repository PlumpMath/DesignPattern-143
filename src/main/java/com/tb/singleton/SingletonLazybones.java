package com.tb.singleton;

/**
 * 懒汉式单例模式
 * 懒汉式单例模式生成的对象只有在使用的时候才会被创建
 * Created by yangzhuo on 16-1-3.
 */
public class SingletonLazybones {

    private static SingletonLazybones singletonLazybones = null;

    public  SingletonLazybones getInstance() {
        if (singletonLazybones == null) {
            singletonLazybones = new SingletonLazybones();
        }
        return singletonLazybones;
    }

    public static void main(String[] args) {
        SingletonLazybones test = new SingletonLazybones();
        SingletonLazybones object = test.getInstance();
    }

}
