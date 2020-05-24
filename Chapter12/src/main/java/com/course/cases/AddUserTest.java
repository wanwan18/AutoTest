package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.utils.DatabaseUtil;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);

        System.out.println("addUserCase: " + addUserCase.toString());
        System.out.println("Url: " + TestConfig.addUserUrl);
    }
}
