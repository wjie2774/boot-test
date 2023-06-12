package com.zzzwww.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerImpl implements InvocationHandler {

    private Object object;

    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理。。");
        System.out.println(method.getName());
        Object result = method.invoke(object, args);
        System.out.println("结束代理。。");
        return result;
    }

    public static void main(String[] args) {
        Animal dog = new Dog();
        InvocationHandler invocationHandle = new InvocationHandlerImpl(dog);
        ClassLoader classLoader = dog.getClass().getClassLoader();
        Class<?>[] interfaces = dog.getClass().getInterfaces();
        Animal dogProxxy = (Animal) Proxy.newProxyInstance(classLoader, interfaces, invocationHandle);
        dogProxxy.eat();
    }
}
