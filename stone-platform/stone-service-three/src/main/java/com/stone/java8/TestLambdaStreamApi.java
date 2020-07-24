package com.stone.java8;

import com.stone.vo.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * P 12
 * 一、Stream 的三个操作步骤
 * 1、创建 Stream
 * 2、中间操作
 * 3、终止操作
 */
public class TestLambdaStreamApi {

    List<Employee> employees = Arrays.asList(
            new Employee(1L, "张三1", 21),
            new Employee(2L, "张三2", 32),
            new Employee(3L, "张三3", 45),
            new Employee(4L, "张三4", 18),
            new Employee(5L, "张三4", 32),
            new Employee(5L, "张三3", 36)
    );


    /**
     * 收集
     * collect -- 将流转换为其他形式，接收一个 Collector 接口的实现，用于给Stream 中元素做汇总的方法
     */
    @Test
    public void test6(){
        // 1、List 收集员工姓名
        List<String> empNames = employees.stream()
                .map(Employee::getEmplName)
                .collect(Collectors.toList());
        empNames.forEach(System.err::println);

        // 2、Set 收集员工姓名
        Set<String> empSets = employees.stream()
                .map(Employee::getEmplName)
                .collect(Collectors.toSet());
        empSets.forEach(System.err::println);

        // 3、指定 容器存储
        HashSet<String> hashSetEmp = employees.stream()
                .map(Employee::getEmplName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSetEmp.forEach(System.err::println);

        // 4、总数
        Long countEmp = employees.stream()
                .collect(Collectors.counting());
        System.err.println("countEmp : " + countEmp);

        // 5、年龄的平均值
        Double avgEmpl = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getAge));
        System.err.println("avgEmpl : " + avgEmpl);

        // 6、总和
        Double sumEmpl = employees.stream()
                .collect(Collectors.summingDouble(Employee::getAge));
        System.err.println("sumEmpl : " + sumEmpl);

        // 7、最大值
        Employee maxEmpl = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> e1.getAge().compareTo(e2.getAge()))).orElse(null);
        System.err.println("maxEmpl : " + maxEmpl);

        // 8、最小值
        Integer minAge = employees.stream()
                .map(Employee::getAge)
                .collect(Collectors.minBy(Integer::compareTo)).orElse(0);
        System.err.println("minAge : " + minAge);

        // 9、按照姓名分组
        Map<String, List<Employee>> groupEmpl = employees.stream()
                .collect(Collectors.groupingBy(Employee::getEmplName));
        System.err.println("groupEmpl : " + groupEmpl);

        // 10、多级分组
        Map<String, Map<String, List<Employee>>> mapMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getEmplName, Collectors.groupingBy((e) -> {
                    if (e.getAge() < 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.err.println("mapMap : " + mapMap);

        // 11、分区，符合条件的一个，其他另外一个
        Map<Boolean, List<Employee>> partitionMap = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 30));
        System.err.println("partitionMap : " + partitionMap);

        // 12、DoubleSummary
        IntSummaryStatistics intSumEmpl = employees.stream()
                .collect(Collectors.summarizingInt(Employee::getAge));
        System.err.println("max: " + intSumEmpl.getMax());
        System.err.println("average: " + intSumEmpl.getAverage());

        // 13、连接字符串
        String joinEmplName = employees.stream()
                .map(Employee::getEmplName).distinct()
                .collect(Collectors.joining(","));
        System.err.println("joinEmplName : " + joinEmplName);


    }


    /**
     * 查找与匹配
     * allMatch  -- 检查是否匹配所有元素
     * anyMatch -- 检查是否匹配一个元素
     * noneMatch -- 检查是否没有匹配所有元素
     * findFirst -- 返回第一个元素
     * findAny -- 返回当前流中的任意元素
     * count -- 返回流中元素的总个数
     * max  -- 返回流中最大值
     * min  -- 返回流中最小值
     */
    @Test
    public void test5(){

        // allMatch
        boolean allMatch = employees.stream().allMatch((e) -> e.getEmplName().equals("张三1"));
        System.err.println("allMatch : " + allMatch);

        // anyMatch
        boolean anyMatch = employees.stream().anyMatch((e) -> e.getEmplName().equals("张三1"));
        System.err.println("anyMatch : " + anyMatch);

        // noneMatch
        boolean noneMatch = employees.stream().noneMatch((e) -> e.getEmplName().equals("张三1"));
        System.err.println("noneMatch : " + noneMatch);

        // findFirst
        Employee employee = employees.stream().sorted((e1, e2) -> {
            return e1.getAge().compareTo(e2.getAge());
        }).findFirst().orElse(null);
        System.err.println("employee : " + employee);

        // findAny
        Employee emptyEmployee = employees.parallelStream().sorted((e1, e2) -> {
            return e1.getAge().compareTo(e2.getAge());
        }).findAny().orElse(null);
        System.err.println("emptyEmployee : " + emptyEmployee);

        // count
        long count = employees.stream().count();
        System.err.println("count: " + count);

        // max, 年龄最大员工
        Employee maxEmployee = employees.stream().max((e1, e2) -> {
            return e1.getAge().compareTo(e2.getAge());
        }).orElse(null);
        System.err.println("maxEmployee : " + maxEmployee);

        // min ， 工资最少是多少
        Integer minAge = employees.stream().map(Employee::getAge).min(Integer::compareTo).orElse(0);
        System.err.println("minAge : " + minAge);

    }


    /**
     * 排序
     * sorted()  -- 自然排序
     * sorted(Comparator com) -- 定制排序
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("aaa","bbb","fff","ddd");
        // 1、自然排序
         List<String> sortedString = list.stream().sorted().collect(Collectors.toList());
        sortedString.forEach(System.err::println);
        // 2、定制排序
        List<Employee> collect = employees.stream().sorted((e1, e2) -> {
            if (e1.getId() == e2.getId()) {
                return e1.getEmplName().compareTo(e2.getEmplName());
            } else {
                return e1.getId().compareTo(e2.getId());
            }
        }).collect(Collectors.toList());
        collect.forEach(System.err::println);
    }

    /**
     * 映射
     * map -- 接收 lambda , 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将
     * 其映射成一个新的元素。
     * flatMap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连成一个流
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
        // 1、把list中的元素全部转为大写
        Stream<String> upStream = list.stream().map((str) -> str.toUpperCase());
        List<String> upName = upStream.collect(Collectors.toList());
        upName.forEach(System.err::println);

        // 2、获取员工中的所有员工姓名
        Stream<String> employeeStream = employees.stream().map((e) -> e.getEmplName());
        List<String> employeeName = employeeStream.collect(Collectors.toList());
        employeeName.forEach(System.err::println);
    }

    // 2、中间操作
    /**
     * 帅选与切片
     * filter -- 接收 lambda 从流中排除某些元素
     * limit  -- 截断流， 使其元素不超过给定数量
     * skip(n) -- 跳过元素，返回一个扨掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n) 互补
     * distinct -- 帅选，通过流所生产元素的hashCode() 和 equals（） 去除重复元素
     */
    @Test
    public void test2() {
        // 1、Filter
        Stream<Employee> filterStream = employees.stream()
                .filter((e) -> e.getAge() > 21);
        List<Employee> filterEmployee = filterStream.collect(Collectors.toList());
        filterEmployee.forEach(System.err::println);
        System.err.println("==========  Filter  ================\n");
        // 2、limit
        Stream<Employee> limitStream = employees.stream().filter((e) -> e.getId() > 1).limit(2);
        List<Employee> limitEmployee = limitStream.collect(Collectors.toList());
        limitEmployee.forEach(System.err::println);
        System.err.println("==========  limit  ================\n");

        // 3、skip
        Stream<Employee> skipStream = employees.stream().filter((e) -> e.getId() > 1).skip(2);
        List<Employee> skipEmployee = skipStream.collect(Collectors.toList());
        skipEmployee.forEach(System.err::println);
        System.err.println("==========  skip  ================\n");

        // 4、distinct
        Stream<Employee> distinctStream = employees.stream().filter((e) -> e.getId() > 1).skip(2).distinct();
        List<Employee> distinctEmployee = distinctStream.collect(Collectors.toList());
        distinctEmployee.forEach(System.err::println);

    }


    // 1、创建 Stream
    @Test
    public void test1() {
        // 1、可以通过 Collection 系列集合提供的 Stream() 或者 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2、通过Arrays 中的静态方法Stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3、通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb");

        // 4、创建无限流, 迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        // 5、创建无限流， 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }


}
