package com.forcode.base.design.template;

import java.util.Map;

/**
 * @description:
 * 定义：
 *  1.登录外部网站
 *  2.爬取商品信息
 *  3.生成海报
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public abstract class NetMall {

    String uId;   // 用户ID
    String uPwd;  // 用户密码

    public NetMall(String uId, String uPwd) {
        this.uId = uId;
        this.uPwd = uPwd;
        System.out.println("NetMall===");
    }

    /**
     * 生成商品推广海报
     *
     * @param skuUrl 商品地址
     * @return 海报图片base64位信息
     */
    public String generateGoodsPoster(String skuUrl) {
        // 1. 验证登录
        if (!login(uId, uPwd)) {
            return null;
        }
        // 2. 爬虫商品
        Map<String, String> reptile = reptile(skuUrl);

        // 3. 组装海报
        return createBase64(reptile);
    }

    // 模拟登录
    protected abstract Boolean login(String uId, String uPwd);

    // 爬虫提取商品信息
    protected abstract Map<String, String> reptile(String skuUrl);

    // 生成商品海报信息
    protected abstract String createBase64(Map<String, String> goodsInfo);
}
