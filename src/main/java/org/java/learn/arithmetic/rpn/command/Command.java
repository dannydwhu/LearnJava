package org.java.learn.arithmetic.rpn.command;

import java.math.BigDecimal;


/**
 * Command base interface
 * All operators should implements this interface for detail algorithms operation
 * @author Danny Hu
 * @date 2019/03/09
 */
public interface Command {

    /**
     * operators for + - * / command
     * case for 1.a + b  2.a - b 3.a * b 4.a / b
     * @param a
     * @param b
     * @return
     */
    BigDecimal calc(BigDecimal a, BigDecimal b);

}
