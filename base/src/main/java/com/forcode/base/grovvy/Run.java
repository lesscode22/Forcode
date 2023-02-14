package com.forcode.base.grovvy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        GroovyClassLoader loader = new GroovyClassLoader();
        Class parseClass = loader.parseClass(new File("F:\\software documents\\codedemo\\my-pro\\Forcode\\base\\src\\main\\java\\com\\forcode\\base\\grovvy\\scripts\\Index.groovy"));
        GroovyObject groovyObject = (GroovyObject) parseClass.newInstance();
        Object result = groovyObject.invokeMethod("addNum", new Integer[]{1, 2});
        System.out.println("==== " + result);
    }
}
