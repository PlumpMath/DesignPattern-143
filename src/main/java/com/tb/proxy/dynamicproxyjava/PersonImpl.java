package com.tb.proxy.dynamicproxyjava;

/**
 * Created by yangzhuo02 on 2016/1/18.
 */
public class PersonImpl implements IPerson {
    @Override
    public void sayHello(String str) {

        System.out.println("start");
        System.out.println(str);
        System.out.println("end");

    }
}
