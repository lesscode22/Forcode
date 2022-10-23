package com.forcode.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public final class JsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * ParameterNamesModule: 从json串反序列化到对象时使用
     *      1.若对象存在合适的构造器, 则不注册此模块也能反序列化
     *      2.若对象无合适构造器, 此模块可使其正常反序列化(开启-parameters参数编译的前提下),
     *          spring-boot-starter-parent 默认会开启 -parameters 参数编译
     * JavaTimeModule: 提供jdk8下新的时间类型序列化支持
     * Jdk8Module: 提供jdk8新特性序列化支持, 比如: Optional, Stream等
     */
    static {
        OBJECT_MAPPER.registerModules(new ParameterNamesModule(), new Jdk8Module())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Date类型格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.setDateFormat(df);

        // LocalDate, LocalDateTime 格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        OBJECT_MAPPER.registerModule(javaTimeModule);

    }

    private JsonUtil() {}

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * 转为json字符串
     */
    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][toJson]: ", e);
        }
        return "";
    }

    /**
     * 将json字符串格式化后输出
     */
    public static String toJsonPretty(Object obj) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][toJsonPretty]: ", e);
        }
        return "";
    }

    public static String getNodeValue(String jsonStr, String key) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonStr);
            return jsonNode.findValue(key).asText();
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][getNodeValue]: ", e);
        }
        return null;
    }

    /**
     * 转为Class对象
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][fromJson]: ", e);
        }
        return null;
    }

    /**
     * json转List
     *
     * @param jsonArrayStr json串
     * @param clazz        集合中的元素类型
     */
    public static <T> List<T> fromJsonToList(String jsonArrayStr, Class<T> clazz) {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][fromJsonToList]: ", e);
        }
        return new ArrayList<>();
    }

    /**
     * json转List
     * @param jsonArrayStr json串
     * @return 默认泛型: List<Map<String, Object>>
     */
    public static List<Map<String, Object>> fromJsonToListMap(String jsonArrayStr) {

        JavaType javaType = getCollectionType(ArrayList.class);
        try {
            return OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][fromJsonToListMap]: ", e);
        }
        return new ArrayList<>();
    }

    /**
     * json转为Map
     *
     * @param jsonMapStr json串
     */
    public static Map<String, Object> fromJsonToMap(String jsonMapStr) {

        JavaType javaType = getMapType(Map.class, String.class, Object.class);
        try {
            return OBJECT_MAPPER.readValue(jsonMapStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][fromJsonToMap]: ", e);
        }
        return new HashMap<>();
    }

    /**
     * json转Map, 指定泛型
     * @param jsonMapStr json串
     * @param keyClass key类型
     * @param valueClass value类型
     */
    public static <K, V> Map<K, V> fromJsonToMap(String jsonMapStr, Class<K> keyClass, Class<V> valueClass) {

        JavaType javaType = getMapType(Map.class, keyClass, valueClass);
        try {
            return OBJECT_MAPPER.readValue(jsonMapStr, javaType);
        } catch (JsonProcessingException e) {
            log.error("===[JsonUtil][fromJsonToMap]: ", e);
        }
        return new HashMap<>();
    }

    // ============================================== private

    /**
     * 获取collection中的泛型
     *
     * @param collectionClass 泛型的Collection
     * @param elementClass    集中中的元素类型
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClass) {
        return OBJECT_MAPPER.getTypeFactory().constructCollectionLikeType(collectionClass, elementClass);
    }

    private static JavaType getCollectionType(Class<? extends Collection> collectionClass) {
        JavaType mapType = getMapType(Map.class, String.class, Object.class);
        return OBJECT_MAPPER.getTypeFactory().constructCollectionType(collectionClass, mapType);
    }

    /**
     * 获取Map中的泛型
     *
     * @param mapClass   泛型的Collection
     * @param keyClass   Map中key类型
     * @param valueClass Map中value类型
     * @return JavaType Java类型
     */
    private static JavaType getMapType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return OBJECT_MAPPER.getTypeFactory().constructMapLikeType(Map.class, keyClass, valueClass);
    }
}
