package com.forcode.base.jdk;

import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 获取泛型类型的原理
 * 1.在编译时, 编译器无法确定泛型类的具体泛型类型, 所以在运行期也获取不到具体类型
 * 2.要获取到泛型具体类型, 就要明确指定其类型, 通过构造泛型类的匿名子类来指定泛型类型即可
 *
 * @author: TJ
 * @date:  2022-11-23
 **/
public class GenericType {

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        ParameterizedType paramType = (ParameterizedType) data.getClass().getGenericSuperclass();
        // type = java.util.AbstractList<E>
        System.out.println("type = " + paramType);

        // 创建匿名子类
        // 类似于 NormalWay extends Way<String, Long, Long> 形式, 子类继承父类时指定了父类的泛型类型, 那么从 NormalWay 实例就可以获取到泛型类型
        data = new ArrayList<String>() {};
        paramType = (ParameterizedType) data.getClass().getGenericSuperclass();
        // type = java.util.ArrayList<java.lang.String>, 能正确显示泛型类型
        System.out.println("type = " + paramType);

        // 获取泛型类型
        Type[] typeArguments = paramType.getActualTypeArguments();
        for (Type type : typeArguments) {
            // type = java.lang.String
            System.out.println("type = " + type.getTypeName());
        }

        System.out.println("===================== 测试");
        Token<Way<String, Integer, Integer>> token = new Token<Way<String, Integer, Integer>>() {};
        token.parseGenericInfo();
    }

    /**
     * 封装获取泛型工具
     * 类似于 {@link ParameterizedTypeReference}
     * @param <T>
     */
    public static abstract class Token<T> {

        public void parseGenericInfo() {

            ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type[] typeArguments = paramType.getActualTypeArguments();

            for (Type argument : typeArguments) {
                ParameterizedType type = (ParameterizedType) argument;
                Type[] classTypes = type.getActualTypeArguments();
                for (Type classType : classTypes) {
                    System.out.println(classType.getTypeName());
                }
            }
        }
    }

    public static class Way<T, S, R> {}

    public static class NormalWay extends Way<String, Integer, Integer> {

    }
}
