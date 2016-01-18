package com.tb.proxy.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * Created by yangzhuo02 on 2016/1/18.
 *
 */
public class PersonProxy implements MethodInterceptor {

    private Object target = null;

    public Object getInstance(Object object) {

        this.target = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
//        创建回调方法
        enhancer.setCallback(this);
//        生成代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        //这里必须填写obj，否则会报错
        result = proxy.invokeSuper(obj, args);

        return result;
    }
}
