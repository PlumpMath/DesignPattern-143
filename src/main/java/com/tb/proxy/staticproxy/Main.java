package com.tb.proxy.staticproxy;

/**
 * Created by yangzhuo02 on 2016/1/18.
 */
public class Main {
    public static void main(String[] args) {

        PersonProxy personProxy = new PersonProxy();
        IPerson person = (IPerson)personProxy.bind(new PersonImpl());
        person.sayHello("hello world");

    }
}
