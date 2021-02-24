package com.epam.task.fifth.interpretor;

public class NonTerminalExpressionNumber extends AbstractExpression {
    private int number;
    public NonTerminalExpressionNumber(int number) {
        this.number = number;
    }
    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
