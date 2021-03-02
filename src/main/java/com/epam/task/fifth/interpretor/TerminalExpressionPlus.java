package com.epam.task.fifth.interpretor;

public class TerminalExpressionPlus extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue((context.popValue() + context.popValue()));
    }
}
