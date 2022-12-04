package com.forcode.base.spring.security.userdetails.sysuser;

import cn.hutool.core.util.IdUtil;
import com.forcode.base.utils.MockDataUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-27
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;
    private String userName;

    public static UserInfo getInstance() {
        return new UserInfo(IdUtil.getSnowflakeNextId(), MockDataUtil.getName());
    }
}
