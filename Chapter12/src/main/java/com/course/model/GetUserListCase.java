package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private String userName;
    private Integer age;
    private String sex;
    private String expected;
}
