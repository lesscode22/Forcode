package com.forcode.base.practices.org;

import lombok.Data;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-12
 **/
@Data
public class OrgDO {

    private Long id;
    private String name;
    private Integer left;
    private Integer right;
    private Integer level;
}
