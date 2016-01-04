package com.tb.singleton;

/**
 * 饿汉式单例模式
 * 在对象创建的时候就创建一个对象属性
 * Created by yangzhuo on 16-1-3.
 */
public class SingletonHungry {
    private static SingletonHungry singletonLazybones = new SingletonHungry();

    public SingletonHungry getInstance() {
        return singletonLazybones;
    }

    public static void main(String[] args) {
        SingletonHungry test = new SingletonHungry();
        SingletonHungry object = test.getInstance();
    }

}
