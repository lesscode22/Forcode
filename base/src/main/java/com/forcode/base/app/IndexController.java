package com.forcode.base.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void add() {
        System.out.println("========add");
    }
}
