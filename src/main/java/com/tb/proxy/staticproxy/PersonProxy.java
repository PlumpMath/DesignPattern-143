package com.tb.proxy.staticproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangzhuo02 on 2016/1/18.
 */
public class PersonProxy implements InvocationHandler {

    private Object target = null;

    public Object bind(Object object) {
        this.target = object;
        Object proxy = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);

        System.out.println(proxy.getClass());

        return proxy;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始调用代理方法");
        Object result = method.invoke(this.target, args);
        System.out.println("结束调用代理方法");

        return result != null ? result : null;
    }
}
