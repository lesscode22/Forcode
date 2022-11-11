package com.forcode.base.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @description: 全局日期时间类型序列化/反序列化格式
 * 
 * @author: TJ
 * @date:  2022-10-22
 **/
@JsonComponent
public class DateFormatConfig {

    /**
     * 全局配置 Date 类型返回格式
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder() {

        return builder -> {

            builder.failOnUnknownProperties(false)  // JSON串含有未知字段时, 反序列化依旧可以成功
                    .failOnEmptyBeans(false)        // 如果一个类没有public的方法或属性时序列化为空JSON串
            ;

            // Long 转 String
            builder.serializerByType(Long.class, ToStringSerializer.instance);

            // Date类型格式
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            builder.dateFormat(df);

            // LocalDate, LocalDateTime 格式
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        };
    }
}
