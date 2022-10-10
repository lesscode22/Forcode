package com.forcode.base.app;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-25
 **/
@RestController
@RequestMapping("api")
public class IndexController {

    @GetMapping("add")
    public Map<String, Object> add(int pageNo, int pageSize) {
        System.out.println("========add");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("userCode", String.valueOf(i));
            map.put("userName", "小明" + i);
            map.put("phone", 12345678901L);
            map.put("email", "qq.com-" + i);
            map.put("address", "快三大部分is开始交的方式都开始" + i);
            list.add(map);
        }
        int size = list.size();
        list = CollUtil.page(pageNo, pageSize, list);
        return ImmutableMap.of("data", list, "page", size);
    }
}
