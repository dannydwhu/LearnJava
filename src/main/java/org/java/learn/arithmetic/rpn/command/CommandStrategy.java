package org.java.learn.arithmetic.rpn.command;


import org.java.learn.arithmetic.rpn.command.impl.*;

/**
 * Command Strategy
 * Map all operators for single instance
 *
 * @author Danny Hu
 * @date 2019/03/09
 * @see (+,-,*,/) method dynamic find command
 */
public enum CommandStrategy {

    Add("+", new AddCommand()),
    Clear("clear", new ClearCommand()),
    Divide("/", new DivideCommand()),
    Multiply("*", new MultiplyCommand()),
    Sub("-", new SubCommand()),
    Undo("undo", new UndoCommand()),
    ;

    public static CommandStrategy parseFrom(String operator) {
        for (CommandStrategy cmd : values()) {
            if (cmd.getCode().equals(operator)) {
                return cmd;
            }
        }
        return null;
    }

    private CommandStrategy(String code, Command command) {
        this.code = code;
        this.command = command;
    }

    private String code;
    private Command command;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
