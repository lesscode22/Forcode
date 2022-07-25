package com.forcode.base.design.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 中间类
 */
public class JavaProxyInvocationHandler implements InvocationHandler {

    /**
     * 持有委托类的引用
     */
    private Object obj;

    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 动态生成代理类对象,Proxy.newProxyInstance
     * @return 返回代理类的实例
     */
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(
                //指定代理对象的类加载器
                obj.getClass().getClassLoader(),
                //代理对象需要实现的接口，可以同时指定多个接口
                obj.getClass().getInterfaces(),
                //方法调用的实际处理者，代理对象的方法调用都会转发到这里
                this);
    }

    /**
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke befor");
        Object invoke = method.invoke(obj, args);
        System.out.println("invoke after");
        return invoke;
    }

    public static void main(String[] args) {
        // 委托类对象
        IHelloService helloService = new HelloService();
        JavaProxyInvocationHandler proxyInvocationHandler = new JavaProxyInvocationHandler();
        proxyInvocationHandler.setObj(helloService);

        IHelloService instance = (IHelloService)proxyInvocationHandler.newProxyInstance();
        instance.sayHello("xiaoming");

        showProxyClass();

    }

    private static void showProxyClass() {
        String path = "F:\\software documents\\codedemo\\my-pro\\bootApp\\doc\\$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", HelloService.class.getInterfaces());
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
