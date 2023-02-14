package com.forcode.base.grovvy.scripts

import com.forcode.base.utils.JsonUtil
import groovy.sql.Sql

class Index {

    Integer addNum(Integer a, Integer b) {
        println ">>>>>>>> groovy执行"
        a + b
        int i = 0
    }

    static void main(String[] args) {

        forDatabase()
    }

    static void forDatabase() {

        def instance = Sql.newInstance("",
                "",
                "",
        "com.mysql.cj.jdbc.Driver")
        def rows = instance.firstRow("select * from sys_user")
        println JsonUtil.toJson(rows)
    }
}
