package com.forcode.base.jdk.classloader;

import lombok.Data;

@Data
public class PersonADTO {

    private String name;

    public String show() {
        return "========== PersonADTO";
    }
}
