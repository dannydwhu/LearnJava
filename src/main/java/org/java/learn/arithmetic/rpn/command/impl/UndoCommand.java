package org.java.learn.arithmetic.rpn.command.impl;

import org.java.learn.arithmetic.rpn.command.Command;
import org.java.learn.arithmetic.rpn.exception.NotSupportCommandException;

import java.math.BigDecimal;

public class UndoCommand implements Command {

    @Override
    public BigDecimal calc(BigDecimal a, BigDecimal b) {
        throw new NotSupportCommandException();
    }

}
