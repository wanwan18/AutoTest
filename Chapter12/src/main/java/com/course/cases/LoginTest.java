package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "")
    public void beforeTest() {
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.httpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue", description = "用户登录接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println("loginCase: " + loginCase.toString());
        System.out.println("Url: " + TestConfig.loginUrl);

        // 发送请求
         String result = getResult(loginCase);

         // 验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }

    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFail() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 2);
        System.out.println("loginFail: " + loginCase.toString());
        System.out.println("Url: " + TestConfig.loginUrl);
        // 发送请求, ，接口返回数据
        String result = getResult(loginCase);
        // 验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }


    private String getResult(LoginCase loginCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.loginUrl);
        // 设置参数
        JSONObject param = new JSONObject();
        param.put("userName", loginCase.getUserName());
        param.put("password", loginCase.getPassword());

        // 设置头信息
        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        // 提交请求
        HttpResponse response = TestConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");

        TestConfig.store = TestConfig.httpClient.getCookieStore();
        return result;

    }
}
