package com.forcode.orm.primitive;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-07-30
 **/
public class Index {

    public static void main(String[] args) throws IOException {

        // mybatis初始化
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行SQL语句
        List list = sqlSession.selectList("com.forcode.orm.primitive.IndexMapper.findList");
//        IndexMapper mapper = sqlSession.getMapper(IndexMapper.class);
//        List list = mapper.findList();
        System.out.println(list);
    }
}
