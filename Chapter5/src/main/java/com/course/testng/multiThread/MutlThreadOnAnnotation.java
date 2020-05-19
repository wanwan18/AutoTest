package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MutlThreadOnAnnotation {
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test() {
        System.out.println();
        System.out.printf("Thread id: %s%n", Thread.currentThread().getId());
    }
}
