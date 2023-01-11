package com.forcode.base.jdk.classloader;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.URLUtil;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String projectPath = FileUtil.getWebRoot().getAbsolutePath();
        String b = projectPath + File.separator + "src\\main\\java\\com\\forcode\\base\\jdk\\classloader\\resource\\datamanager_b.jar";
        String c = projectPath + File.separator + "src\\main\\java\\com\\forcode\\base\\jdk\\classloader\\resource\\datamanager_c.jar";

        // 用不同的类加载器实例可以加载不同版本的Jar, 全限定类名相同
        divideLoad(b, c);
        System.out.println(" ");

        // 同一类加载器, 相同全限定类名的类只会被加载一次
        uniqueLoad(b, c);
        System.out.println(" ");

        // 双亲委派, 加载类会向上传递
        String p = projectPath + File.separator + "src\\main\\java\\com\\forcode\\base\\jdk\\classloader\\PersonADTO.java";
        upLoad(p);
        System.out.println(" ");

        // 使用自定义类加载器, 注意文件夹的路径
        p = projectPath + File.separator + "src\\main\\java";
        breakLoad(p);
    }

    private static void divideLoad(String b, String c) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL[] urLs = URLUtil.getURLs(new File(b));
        ClassLoader loaderB = new URLClassLoader(urLs);
        Class<?> BClass = loaderB.loadClass("com.DataManager");
        Object B = BClass.newInstance();
        System.out.println("b jar ===> " + ReflectUtil.invoke(B, "go", "param").toString());

        urLs = URLUtil.getURLs(new File(c));
        ClassLoader loaderC = new URLClassLoader(urLs);
        Class<?> CClass = loaderC.loadClass("com.DataManager");
        Object C = CClass.newInstance();
        System.out.println("c jar ===> " + ReflectUtil.invoke(C, "go", "param").toString());
    }

    private static void uniqueLoad(String b, String c) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // b、c谁在前面就会被加载, 后面的就不会被重复加载了, 通过 go 方法输出可确认实际加载的 Jar
        URL[] urLs = URLUtil.getURLs(new File(c), new File(b));
        ClassLoader loader = new URLClassLoader(urLs);
        Class<?> BClass = loader.loadClass("com.DataManager");
        Object B = BClass.newInstance();
        System.out.println("jar ===> " + ReflectUtil.invoke(B, "go", "param").toString());

    }

    private static void upLoad(String p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        URL[] urLs = URLUtil.getURLs(new File(p));
        ClassLoader loader = new URLClassLoader(urLs);
        Class<?> loadClass = loader.loadClass("com.forcode.base.jdk.classloader.PersonADTO");
        Object obj = loadClass.newInstance();
        System.out.println("PersonADTO 方法执行: " + ReflectUtil.invoke(obj, "show").toString());
        // 使用的 AppClassLoader
        ClassLoader classLoader = loadClass.getClassLoader();
        System.out.println("PersonADTO classLoader: " + classLoader);
    }

    private static void breakLoad(String p) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        URL[] urLs = URLUtil.getURLs(new File(p));
        ClassLoader loader = new CustomClassLoader(urLs);
        Class<?> loadClass = loader.loadClass("com.forcode.base.jdk.classloader.PersonBDTO");
        Object obj = loadClass.newInstance();
        System.out.println("PersonBDTO 方法执行: " + ReflectUtil.invoke(obj, "show").toString());
        // 使用的 CustomClassLoader
        ClassLoader classLoader = loadClass.getClassLoader();
        System.out.println("PersonBDTO classLoader: " + classLoader);
    }
}
