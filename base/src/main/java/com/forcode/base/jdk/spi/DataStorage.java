package com.forcode.base.jdk.spi;

import java.util.ServiceLoader;

/**
 * 在 META-INF/services 下指定实现类
 */
public interface DataStorage {

    String search(String key);

    public static void main(String[] args) {
        ServiceLoader<DataStorage> serviceLoader = ServiceLoader.load(DataStorage.class);
        System.out.println("============ Java SPI 测试============");
        serviceLoader.forEach(loader -> System.out.println(loader.search("")));
    }
}
