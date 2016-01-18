package com.tb.proxy.dynamicproxy;


/**
 * Created by yangzhuo02 on 2016/1/18.
 */
public class Main {
    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy();
        PersonImpl person = (PersonImpl) personProxy.getInstance(new PersonImpl());
        person.sayHello("hello world");
    }
}
