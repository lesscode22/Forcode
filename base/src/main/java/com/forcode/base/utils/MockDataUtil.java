package com.forcode.base.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-27
 **/
public class MockDataUtil {

    private static final Faker faker = new Faker(new Locale("zh_CN"));

    public static String getName() {
        return faker.name().fullName();
    }

    public static String getPhone() {
        return faker.phoneNumber().cellPhone();
    }

    public static void main(String[] args) {
        System.out.println(getName());
        System.out.println(getPhone());
    }
}
