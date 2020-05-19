package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    // 最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1() {
        System.out.printf("Thread id: %s%n", Thread.currentThread().getId());
        System.out.println("测试用例1");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod：这是在测试方法之前运行的");
    }

    @Test
    public void testCase2() {
        System.out.printf("Thread id: %s%n", Thread.currentThread().getId());
        System.out.println("测试用例2");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod：这是在测试方法之后运行的");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClass，在类之前运行的方法");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("afterClass，在类之后运行的方法");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite 测试套件");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite  测试套件");
    }
}
