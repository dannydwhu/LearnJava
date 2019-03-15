package org.java.learn.arithmetic.rpn;

import org.java.learn.arithmetic.rpn.command.Command;
import org.java.learn.arithmetic.rpn.command.CommandStrategy;
import org.java.learn.arithmetic.rpn.exception.NotSupportCommandException;
import org.java.learn.arithmetic.rpn.modle.Operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Reverse Polish Notation (RPN) is a math notation which every operator follow its operands
 * The rule is that the first operator occurrence is the first use
 * Use strategy model for each + - * / undo clear 6 command operators
 * The idea of RPN calculator algorithms is:
 *  1. Each character of the expression is read in turn
 *  2. If it is an operand, push into stack
 *  3. If it is + - * / operator , pop 2 data in stack, then push the calculate result back to stack
 *     for example: 3 4 * , pop 4 * 3 ,push result 12 back , now stack top is 12
 *  4. For special command undo : pop the first data stack
 *           clear command clear: clear all the data stack
 * @author Danny Hu
 * @date 2019/03/09
 */
public class RpnCalculator {

    // data stack store operands
    private Stack<BigDecimal> stack ;
    // Command sequential execution queues
    private List<Operation> operators ;
    // expression tokens
    private List<String> tokens;

    public RpnCalculator(){
    }

    /**
     * RPN main method
     * @param expression partition by whitespace such as: 3 4 + 5 *
     * @return BigDecimal
     */
    public synchronized BigDecimal calcResult(String expression) {
        tokens = Arrays.asList(expression.split(" "));
        stack = new Stack<>();
        operators = new ArrayList<>();

        try{
            for(int i=0; i< tokens.size(); i++) {
                String c = tokens.get(i);
                CommandStrategy commandStrategy = CommandStrategy.parseFrom(c);
                // no operator command found and operands will be push to stack
                if(null == commandStrategy){
                    BigDecimal number = getBigDecimal(c);
                    // if char is not number throw exception
                    if(null == number){
                        throw new NotSupportCommandException(c, i+1);
                    }
                    stack.push(number);
                } else {
                    switch(commandStrategy){
                    case Undo:
                        if(stack.size() < 1){
                            throw new NotSupportCommandException(c, i+1);
                        }
                        stack.pop();
                        break;
                    case Clear:
                        if(stack.size() < 1){
                            throw new NotSupportCommandException(c, i+1);
                        }
                        stack.clear();
                        break;   
                    default:
                        // for + - * / operators pop 2 data then calculate result
                        // then push calculate result back to stack
                        Command command = commandStrategy.getCommand();
                        if(stack.size() < 2){
                            throw new NotSupportCommandException(c, i+1);
                        } 
                        BigDecimal b = stack.pop();
                        BigDecimal a = stack.pop();
                        operators.add(new Operation(a,commandStrategy.getCode(),b));
                        stack.push(command.calc(a, b));
                        break;
                    }
                }
            }
            if(stack.size() != 1 || operators.size() < 1){
                throw new NotSupportCommandException(tokens.get(tokens.size()-1), tokens.size());
            }
            System.out.println(operators);
            return stack.pop();
        } catch (NotSupportCommandException nsce) {
            System.err.println(nsce.getMessage());
            throw nsce;
        }
    }
    
    private BigDecimal getBigDecimal(String value) {
        BigDecimal number = null;
        try{
            number = new BigDecimal(value);
        } catch (Exception e) {
        }
        return number;
    }

}
