package com.forcode.base.spring.security.userdetails.member;

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
 * @date:  2022-12-01
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {

    private Long memberId;
    private String nickName;
    private String phone;
    private Boolean enabled;

    public static MemberInfo getInstance() {
        return new MemberInfo().setMemberId(IdUtil.getSnowflakeNextId())
                .setNickName(MockDataUtil.getName())
                .setPhone(MockDataUtil.getPhone())
                .setEnabled(true);
    }

}
