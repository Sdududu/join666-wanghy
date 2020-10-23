package com.join.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * sqlSessionFactory-> sqlSession
 * 从 XML 中构建 SqlSessionFactory
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 放在静态快中，刚初始就会被加载
     * 下面三句是固定的
     */
    static {
        try {
            //使用Mybatis第一步：获取sqlSessionFactory工厂
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。SqlSession
     * 提供了在数据库执行 SQL 命令所需的所有方法。你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
     */

    //第二步，获取SqlSession连接
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }



}
