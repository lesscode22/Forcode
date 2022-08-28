package com.forcode.base.app;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Object> add() {
        System.out.println("========add");
        return ImmutableMap.of("code", "001", "name", "小红");
    }
}
