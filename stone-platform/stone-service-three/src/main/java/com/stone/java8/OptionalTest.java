package com.stone.java8;

import com.stone.vo.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional 类，尽量避免空指针异常
 * 常用方法：
 * Optional.of() : 创建一个Optional 实例
 * Optional.empty() : 创建一个空的 Optional 实例
 * Optional.ofNullable(T t) 若 t 不为null, 创建Optional 实例，否则创建空实例
 * isPresent() ： 判断是否包含值
 * orElse(T t) : 如果调用对象包含值，返回改制，否则返回t
 * orElseGet(Supplier s) :
 *
 * @author stone
 */
public class OptionalTest {


    @Test
    public void test1(){
        // 1、Optional.of()
        Optional<Employee> employee = Optional.of(new Employee());
        System.err.println("empl : " + employee.get());

        // 2、Optional.ofNullable(T t)
        Optional<Object> emptyEmpl = Optional.empty();
        System.err.println("emptyEmpl : " + emptyEmpl);

        // 3、Optional.ofNullable(T t)
        Optional<Object> emptyEmpl2 = Optional.ofNullable(null);
        if(emptyEmpl2.isPresent()){
            System.err.println("emptyEmpl2 : " + emptyEmpl2.get());
        }

        // 4、Optional.orElse(T t)
        Employee emp = null;
        Employee orElse = Optional.ofNullable(emp).orElse(new Employee());
        System.err.println("orElse : " + orElse);
    }


}
