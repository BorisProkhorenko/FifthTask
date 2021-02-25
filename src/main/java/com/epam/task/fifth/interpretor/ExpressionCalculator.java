package com.epam.task.fifth.interpretor;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionCalculator {


    private ArrayList<AbstractExpression> listExpression;

    public ExpressionCalculator(String expression) {
        listExpression = new ArrayList<>();
        parseExpression(expression);
    }

    private void parseExpression(String expression) {
        for (Character lexeme : expression.toCharArray()) {
            switch (lexeme) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme.toString());
                    if (scan.hasNextInt()) {
                        listExpression.add(new NonTerminalExpressionNumber(scan.nextInt()));
                    }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        for (AbstractExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}
