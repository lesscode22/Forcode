package com.forcode.base;

import cn.hutool.core.util.IdUtil;
import com.forcode.base.test.Person;
import com.forcode.base.test.User;
import com.forcode.base.utils.JsonUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-23
 **/
public class MainTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(IdUtil.getSnowflakeNextId());
        user.setMoney(new BigDecimal("12.365"));
        user.setPoint(23.14000d);
        user.setCreateTimeV1(new Date());
        user.setCreateTimeV2(LocalDateTime.now());
        user.setUser_job("院士");
        System.out.println(JsonUtil.toJson(user));
        System.out.println("=====================================");

//        String json = "{\"name\":15,\"id\":1584069508336623616,\"money\":12.365,\"point\":23.14,\"createTimeV1\":\"2022-10-23 14:29:36\",\"createTimeV2\":\"2022-10-23 14:29:36\"}";
        String json = "";
        User user1 = JsonUtil.fromJson(json, User.class);
        System.out.println(user1);

        System.out.println("=========================");
        Person person = new Person();
        String per = JsonUtil.toJson(person);
        System.out.println(per);

        System.out.println(JsonUtil.fromJson(per, Person.class));

    }
}
