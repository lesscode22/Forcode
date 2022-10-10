package com.forcode.base.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-10-03
 **/
@RestController
@RequestMapping("security")
public class SecurityController {

    @GetMapping("test")
    private String test() {

        return "spring security";
    }
}
