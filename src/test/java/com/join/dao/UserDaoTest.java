package com.join.dao;

import com.join.entity.User;
import com.join.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 注意；1.org.apache.ibatis.binding.BindingException: Type interface com.join.dao.UserDao is not known to the MapperRegistry.
 * 记得在映射文件 ，配置路径
 * 2.org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource com/join/dao/UserMapper.xml
 *maven由于他的约定大于配置，我们之后可以能遇到我们写的配置文件，无法被导出或者生效的问题，解决方案:
 *要在pom.xml中build中配置resource，来防止资源导出失败的问题
 * 3.配置中一定要一一对应
 * 4.刚才又出现了错误，是因为忘记maven要导入一下刚才的resource
 *
 *
 *
 *
 */
public class UserDaoTest {

        @Test
        public void selectUser() {
            //1.获取SqlSession对象
            SqlSession sqlSession = MybatisUtils.getSession();
            //方法一:
            //List<User> users = session.getUserList("com.join.dao.UserMapper.getUserList");
            //方法二,执行，以后直接用Mapper代替Dao:
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            List<User> users = mapper.getUserList();

            for (User user: users){
                System.out.println(user);
            }
            sqlSession.close();
        }
    }


