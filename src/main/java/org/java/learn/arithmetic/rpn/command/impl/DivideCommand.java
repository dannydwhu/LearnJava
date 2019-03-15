package org.java.learn.arithmetic.rpn.command.impl;

import org.java.learn.arithmetic.rpn.command.Command;

import java.math.BigDecimal;

public class DivideCommand implements Command {

    @Override
    public BigDecimal calc(BigDecimal a, BigDecimal b) {
        return a.divide(b,15, BigDecimal.ROUND_DOWN);
    }

}
