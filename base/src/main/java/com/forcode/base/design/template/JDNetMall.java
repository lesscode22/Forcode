package com.forcode.base.design.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class JDNetMall extends NetMall {

    public JDNetMall(String id, String pwd) {
        super(id, pwd);
        System.out.println("JDNetMall===");
    }

    @Override
    protected Boolean login(String uId, String uPwd) {
        System.out.println("JD-登录成功");
        return true;
    }

    @Override
    protected Map<String, String> reptile(String skuUrl) {
        System.out.println("JD-获取商品成功");
        return new HashMap<>();
    }

    @Override
    protected String createBase64(Map<String, String> goodsInfo) {
        System.out.println("JD-生成海报");
        return null;
    }

    public static void main(String[] args) {
        NetMall mall = new JDNetMall("1", "2");
        mall.generateGoodsPoster("111");
    }
}
