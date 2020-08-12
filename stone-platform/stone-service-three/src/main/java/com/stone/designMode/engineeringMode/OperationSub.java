package com.stone.designMode.engineeringMode;

/**
 * 减法操作
 */
public class OperationSub implements Operation{
    @Override
    public double getResult(double num1, double num2) {
        return num1 - num2;
    }
}
