package com.forcode.base.design.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibInterceptor 用于对方法调用拦截以及回调
 */
public class CglibInterceptor implements MethodInterceptor {

    /**
     * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，
     * Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
     */
    private Enhancer enhancer = new Enhancer();

    /**
     *
     * @param obj  被代理的对象
     * @param method 代理的方法
     * @param args 方法的参数
     * @param proxy CGLIB方法代理对象
     * @return  cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("方法调用之前");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("方法调用之后");
        return o;
    }


    /**
     * 使用动态代理创建一个代理对象
     */
    public  Object newProxyInstance(Class<?> c) {
        // 设置产生的代理对象的父类,增强类型
        enhancer.setSuperclass(c);
        // 定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
        enhancer.setCallback(this);
        // 使用默认无参数的构造函数创建目标对象,这是一个前提,被代理的类要提供无参构造方法
        return enhancer.create();
    }

    public static void main(String[] args) {
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        HelloService instance = (HelloService)cglibInterceptor.newProxyInstance(HelloService.class);
        instance.sayHello("xiaoming");
    }
}
