package org.java.learn.arithmetic.rpn.modle;

import java.math.BigDecimal;

public class Operation {
    
    private BigDecimal a;
    private BigDecimal b;
    private String operator;
    
    public Operation(BigDecimal a, String operator, BigDecimal b) {
        this.a = a;
        this.operator = operator;
        this.b = b;
    }
    public BigDecimal getA() {
        return a;
    }
    public void setA(BigDecimal a) {
        this.a = a;
    }
    public BigDecimal getB() {
        return b;
    }
    public void setB(BigDecimal b) {
        this.b = b;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public String toString(){
        return a + " " + operator + " " + b;
    }

}
