package com.epam.task.fifth.interpretor;

public class TerminalExpressionDivide extends AbstractExpression{
    @Override
    public void interpret(Context context) {
        context.pushValue((context.popValue() / context.popValue()));
    }
}
