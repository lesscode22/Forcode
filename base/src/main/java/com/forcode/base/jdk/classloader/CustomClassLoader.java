package com.forcode.base.jdk.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义类加载器直接继承 URLClassLoader 最简单;
 * 一般并不需要单独自定义类加载器, 直接使用 URLClassLoader 加载外部文件即可
 *      - URLClassLoader: 支持从 Jar 和文件夹中获取 Class
 */
public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

}
