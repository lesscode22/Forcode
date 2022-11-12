package com.forcode.base.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-12
 **/
public class JasyptUtil {

    public static String encrypt(String plaintext) {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        // 算法类型
        config.setAlgorithm("PBEWithMD5AndDES");
        // 加密秘钥
        config.setPassword("PEB123@321BEP");
        encryptor.setConfig(config);
        return encryptor.encrypt(plaintext);
    }

    public static String decrypt(String ciphertext) {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        // 秘钥
        config.setPassword("PEB123@321BEP");
        encryptor.setConfig(config);
        return encryptor.decrypt(ciphertext);
    }
}
