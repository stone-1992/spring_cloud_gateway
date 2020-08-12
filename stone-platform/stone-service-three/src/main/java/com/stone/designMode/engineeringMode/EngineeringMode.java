package com.stone.designMode.engineeringMode;

/**
 * 1、设计模式之简单工程模式
 *
 * 设计一个计算器，通过传递符号，来获取不同类型的操作符
 *
 */
public class EngineeringMode {

    /**
     * 简单工厂方法获取实例
     * @param operater
     * @return
     */
    public static Operation getOperation(String operater){
        Operation operation = null;
        switch (operater){
            case "+" :
                operation = new OperationAdd();
                break;
            case "-" :
                operation = new OperationSub();
                break;
        }
        return operation;
    }

    public static void main(String[] args) {
        double num1 = 1.2;
        double num2 = 1.3;
        double add = EngineeringMode.getOperation("+").getResult(num1, num2);
        System.err.println(num1 + " + " + num2 + " = " + add);

        double sub = EngineeringMode.getOperation("-").getResult(num1, num2);
        System.err.println(num1 + " - " + num2 + " = " + sub);
    }

}

