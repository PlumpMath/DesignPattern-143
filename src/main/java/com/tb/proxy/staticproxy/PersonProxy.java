package com.tb.proxy.staticproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * Created by yangzhuo02 on 2016/1/18.
 *
 */
public class PersonProxy implements IPerson {

    private IPerson person;

    public PersonProxy(IPerson person) {
        this.person = person;
    }

    @Override
    public void sayHello(String str) {
        System.out.println("代理开始");
        person.sayHello(str);
        System.out.println("代理结束");
    }
}
