package com.stone.java8;

import com.stone.vo.Employee;
import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Java8 Lambda 表达式
 *
 * Lamdba 表达式参数列表  -> Lambda 表达式中所需要执行的功能，即Lambda 体
 * 左侧： 抽象方法的参数列表
 * 右侧： 抽象方法的实现体
 * 语法格式一：无参数，无返回值
 *  () -> System.out.println("sss");
 * 语法格式二：有一个参数，并且无返回值
 *  ()
 *
 *  @author stone
 *
 */
public class TestLambdaController {

    @Test
    public void test1Test(){
        // 匿名内部类
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        // lambda 表达式
        Comparator<Integer> comparator1 = (x,y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet1 = new TreeSet<>(comparator1);
    }

    @Test
    public void test2Test(){
        List<Employee> employees = Arrays.asList(
                new Employee(1L,"张三1",21),
                new Employee(2L,"张三2",32),
                new Employee(3L,"张三3",45),
                new Employee(4L,"张三4",18)
        );
        // 需求：获取年龄大于18的员工, 员工id大于2
        List<Employee> collect = employees.stream().filter((e) -> e.getAge() > 18 && e.getId() > 2 ).collect(Collectors.toList());
        collect.forEach(System.err::println);

    }

    @Test
    public void test3Test(){
        // 以前写法
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.err.println("111");
            }
        };
        r.run();
        // lambda 表达式
        Runnable r1 = () -> System.err.println("222");
        r1.run();
    }

    @Test
    public void test4Test(){
        Consumer<String> consumer = (x) -> System.err.println(x);
        consumer.accept("我到");
    }

}
