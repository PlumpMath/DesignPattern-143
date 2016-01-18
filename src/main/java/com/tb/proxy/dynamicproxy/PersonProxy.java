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
//        �����ص�����
        enhancer.setCallback(this);
//        ���ɴ������
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        //���������дobj������ᱨ��
        result = proxy.invokeSuper(obj, args);

        return result;
    }
}
