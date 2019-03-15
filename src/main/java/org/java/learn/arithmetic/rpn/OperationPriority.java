package org.java.learn.arithmetic.rpn;

/**
 * @auther Danny Hu
 * @date 2019/3/12
 */
public enum OperationPriority {

    add("+"),
    sub("-"),
    multi("*"),
    divide("/"),
    ;

    private String operator;
    private OperationPriority(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public static OperationPriority parseFrom(String value){
        for(OperationPriority operator : values()){
            if(operator.getOperator().equals(value)){
                return operator;
            }
        }
        return null;
    }

    public int getPriority(){
        switch (this){
            case multi:
            case divide:
                return 1;
            default:
                return 0;
        }
    }
}
