package com.offcn;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class apptest {
    @Test
    public void test01() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource bean = context.getBean(DruidDataSource.class);
        DruidPooledConnection connection = bean.getConnection();
        System.out.println(connection);
    }
}
