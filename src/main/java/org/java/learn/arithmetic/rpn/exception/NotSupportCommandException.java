package org.java.learn.arithmetic.rpn.exception;

public class NotSupportCommandException extends RuntimeException {

    private static final long serialVersionUID = 1231238997776L;

    public NotSupportCommandException(){}
    
    public NotSupportCommandException(String operator, int position) {
        super(formatErrorMsg(operator,position));
    }

    private static String formatErrorMsg(String operator, int position){
        return String.format("operator <%s> (position: <%s>): insufficient operands", operator, position);
    }
}
