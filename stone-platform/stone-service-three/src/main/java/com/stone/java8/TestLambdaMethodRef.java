package com.stone.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.function.Consumer;

/**
 * 测试 lambda 方法应用：若lambda 体中的内容有方法已经实现了，我们可以使用"方法应用"
 * （可以理解为方法引用是lambda 表达式的另外一种表现形式）
 * 主要有三种语法格式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 */
public class TestLambdaMethodRef {

    @Test
    public void test1(){
        Consumer<String> con = (x)-> System.err.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
    }


}
