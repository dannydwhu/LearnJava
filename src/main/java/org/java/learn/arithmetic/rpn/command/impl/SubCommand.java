package org.java.learn.arithmetic.rpn.command.impl;


import org.java.learn.arithmetic.rpn.command.Command;

import java.math.BigDecimal;

public class SubCommand implements Command {

    @Override
    public BigDecimal calc(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

}
