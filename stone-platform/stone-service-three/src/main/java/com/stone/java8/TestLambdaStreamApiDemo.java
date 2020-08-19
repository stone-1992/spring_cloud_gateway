package com.stone.java8;
import com.stone.vo.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 练习
 * @author stone
 */
public class TestLambdaStreamApiDemo {

    List<Employee> employees = Arrays.asList(
            new Employee(1L, "张三1", 21),
            new Employee(2L, "张三2", 32),
            new Employee(3L, "张三3", 45),
            new Employee(4L, "张三4", 18),
            new Employee(5L, "张三4", 32),
            new Employee(5L, "张三3", 36)
    );

    /**
     * 2、给定一个数字列表，如何返回一个由每个数的平方构成的列表
     * 1,2,3,4,5  -->  1,4, 9, 16, 25
     */
    @Test
    public void test1Test(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
        List<Integer> sumNums = Arrays.stream(nums)
                .map((x) -> x * x).collect(Collectors.toList());
        sumNums.forEach(System.err::println);

    }

    /**
     * 2、怎么用 amp 和 reduce 方法数一数流中有多少个 Employee
     */
    @Test
    public void test2Test(){
        Integer count = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum).orElse(0);
        System.err.println("count : " + count);
    }
}
