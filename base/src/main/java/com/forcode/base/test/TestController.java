package com.forcode.base.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.forcode.base.utils.JsonUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-25
 **/
@RestController
@RequestMapping("api")
public class TestController {

    @PostMapping("test2")
    public User test2(@RequestBody User param) {
        System.out.println(JsonUtil.toJsonPretty(param));
        System.out.println("==============================");
        User user = new User();
        user.setId(IdUtil.getSnowflakeNextId());
        user.setMoney(new BigDecimal("12.365"));
        user.setPoint(23.14000d);
        user.setCreateTimeV1(new Date());
        user.setCreateTimeV2(LocalDateTime.now());
        String json = JsonUtil.toJson(user);
        System.out.println(json);
        System.out.println("========================");

        User user1 = JsonUtil.fromJson(json, User.class);

        return user;
    }
}
