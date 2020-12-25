package com.stone.designMode.engineeringMode;

/**
 * 加法操作
 * @author stone
 */
public class OperationAdd implements Operation{

    @Override
    public double getResult(double num1, double num2) {
        return num1 + num2;
    }
}
