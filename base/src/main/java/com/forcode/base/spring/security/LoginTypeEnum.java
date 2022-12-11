package com.forcode.base.spring.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    PC_USERNAME("/login", "PC_USERNAME"),
    PC_PHONE("/sms/login", "PC_PHONE");

    private final String url;
    private final String loginType;

    public static boolean enableType(String loginType) {
        for (LoginTypeEnum value : LoginTypeEnum.values()) {
            if (value.loginType.equals(loginType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean enableUrl(String url) {
        for (LoginTypeEnum value : LoginTypeEnum.values()) {
            if (value.url.equals(url)) {
                return true;
            }
        }
        return false;
    }

    public static String getType(String url) {
        for (LoginTypeEnum value : LoginTypeEnum.values()) {
            if (value.url.equals(url)) {
                return value.loginType;
            }
        }
        return null;
    }

    public static final String LOGIN_IDENTITY = "loginType";
}
