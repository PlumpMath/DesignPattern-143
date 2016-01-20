package com.tb.proxy.staticproxy;

/**
 * Created by yangzhuo02 on 2016/1/18.
 */
public class Main {
    public static void main(String[] args) {
        IPerson person = new PersonProxy(new PersonImpl());
        person.sayHello("sdfdsfs");
    }
}
