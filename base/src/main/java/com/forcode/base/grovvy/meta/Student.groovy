package com.forcode.base.grovvy.meta

class Student implements GroovyInterceptable{

    // 自定义动态属性
    public dynamicProp = [:]

    @Override
    void setProperty(String propertyName, Object newValue) {
        dynamicProp[propertyName] = newValue
    }

    @Override
    Object getProperty(String propertyName) {
        return dynamicProp[propertyName]
    }

    static void main(String[] args) {
        def student = new Student()
        student.name = 'ABC'

        println student.name
    }
}
