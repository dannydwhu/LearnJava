package org.java.learn.arithmetic.rpn;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 二元表达式 -> 逆波兰表达式 (后缀表达式)
 *
 * 规则：
 * 1.遇到数字，直接输出
 * 2.遇到左括号进栈
 * 3.遇到右括号执行出栈操作，直到弹出左括号，左括号和右括号不输出
 * 4.遇到其他操作符，则判断其与栈顶操作符的优先级，如果其优先级（<=）栈顶操作符，则栈顶元素依次出栈，该操作符进栈
 * 5.直到最后，将栈中的元素依次输出
 *
 * @auther Danny Hu
 * @date 2019/3/12
 */
public class RpnTransformer {

    private Stack<String> stack;
    private List<String> tokens;
    private static final String whitespace = " ";
    String regEx = "[1-9]{1,}[0-9]{0,}";


    public String translate(String binaryExpression){
        if(null == binaryExpression || "".equals(binaryExpression)){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        stack = new Stack();
        tokens = Arrays.asList(binaryExpression.split(" "));
        for(int i=0; i<tokens.size(); i++){
            String c = tokens.get(i);
            OperationPriority operation = OperationPriority.parseFrom(c);
            if(isNumber(c)){
                sb.append(c+whitespace);
            } else if(c.startsWith("(")){
                stack.push("(");
                if(c.length() > 1){
                    if(isNumber(c.substring(1))){
                        sb.append(c.substring(1)+whitespace);
                    } else {
                        throw new RuntimeException("character " + c + " index: " + (i+1) + " not supported");
                    }
                }
            } else if(c.endsWith(")")) {
                if(stack.isEmpty()){
                    throw new RuntimeException("character " + c + " index: " + (i+1) + " not supported");
                }
                String operator;
                do{
                    operator = stack.pop();
                    sb.append(operator+whitespace);
                } while (operator.equals("(") );
                // "(" 舍弃
                stack.pop();
                if(c.length()>1){
                    c = c.substring(0,c.length()-2);
                    sb.append(c+whitespace);
                }
            } else if (null != operation) {
                if(stack.isEmpty()){
                    stack.push(c);
                } else {
                    // 判断栈顶元素优先级 依次出栈 最后入栈
                    OperationPriority preOperation = null;
                    do {
                        String operator = stack.peek();
                        preOperation = OperationPriority.parseFrom(operator);
                        if (null != preOperation && operation.getPriority() <= preOperation.getPriority()) {
                            sb.append(stack.pop() + whitespace);
                        }
                    } while (!stack.isEmpty() && null != preOperation && operation.getPriority() <= preOperation.getPriority() );
                    stack.push(c);
                }
            } else {
//                throw new RuntimeException("character " + c + " index: " + (i+1) + " not supported");
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop() + whitespace);
        }
        return sb.append(" ").toString().trim();
    }

    private boolean isNumber(String c){
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(c);
        return matcher.matches();
    }

    public static void main(String[] args){
        String expression = "9 + ( 3 - 1 ) * 3 + 10 / 2";
        RpnTransformer rpnTransformer = new RpnTransformer();
        System.out.println(rpnTransformer.translate(expression));

    }

}
