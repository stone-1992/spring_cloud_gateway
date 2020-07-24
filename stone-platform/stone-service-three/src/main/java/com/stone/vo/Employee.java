package com.stone.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /**
     * 员工id
     */
    private Long id;

    /**
     * 员工姓名
     */
    private String emplName;

    /**
     * 员工年龄
     */
    private Integer age;
}
