package com.summary.util;



import com.mchange.v2.c3p0.ComboPooledDataSource;
import sun.applet.Main;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class C3P0Util {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static String url;
    static String user;
    static String password;
    static String driverClass;
    static String acquireIncrement;
    static String initialPoolSize;
    static String maxPoolSize;
    static String minPoolSize;

    //跟反射有关，要放在static中
    static{
        Properties pro= new Properties();
        InputStream is = null;
        try{
            is = C3P0Util.class.getResourceAsStream("properties.properties");

            pro.load(is);
            url = pro.getProperty("jdbcUrl");
            user = pro.getProperty("jdbcUser");
            password = pro.getProperty("password");
            driverClass = pro.getProperty("driverClass");
            acquireIncrement = pro.getProperty(acquireIncrement);
            initialPoolSize = pro.getProperty(initialPoolSize);
            maxPoolSize = pro.getProperty(maxPoolSize);
            minPoolSize = pro.getProperty(minPoolSize);
        }catch(IOException e){
            e.printStackTrace();

        }

    }






    public static void configDataSource() {



        try {

            dataSource.setDriverClass(url);      //设置驱动类
            dataSource.setJdbcUrl(user); //设置URL
            dataSource.setUser(password);                                 //设置User
            dataSource.setPassword(driverClass);                             //设置Password
            dataSource.setInitialPoolSize(Integer.parseInt(acquireIncrement));                           //初始化数据库连接池时连接的数量
            dataSource.setMaxPoolSize(Integer.parseInt(initialPoolSize));                              //设置数据库连接池中的最大的数据库连接数
            dataSource.setMinPoolSize(Integer.parseInt(maxPoolSize));                               //设置数据库连接池中的最小的数据库连接数
            dataSource.setAcquireIncrement(Integer.parseInt(minPoolSize));







//            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");      //设置驱动类
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/final_test"); //设置URL
//            dataSource.setUser("root");                                 //设置User
//            dataSource.setPassword("root");                             //设置Password
//            dataSource.setInitialPoolSize(3);                           //初始化数据库连接池时连接的数量
//            dataSource.setMaxPoolSize(10);                              //设置数据库连接池中的最大的数据库连接数
//            dataSource.setMinPoolSize(3);                               //设置数据库连接池中的最小的数据库连接数
//            dataSource.setAcquireIncrement(3);                          //设置如果池中数据连接不够时一次增长多少个
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从连接池中获取连接
     *
     */
    public static Connection getConnection() {
        configDataSource();
        Connection connection = null;
        try {

            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return connection;



    }
    /**
     * 释放连接回连接池中
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void releaseConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private static Connection conn;
//    //配置文件默认配置，必须给出c3p0-config.xml
//    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
//
//    public static Connection getConnection(){
//        try{
//            conn = dataSource.getConnection();
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return conn;
//    }
//    public ComboPooledDataSource getDataSource(){
//        return dataSource;
//    }
}

