package com.epam.task.fifth.parsing;


public abstract class AbstractParser implements Parser {

    private Parser successor;

    protected Parser getSuccessor() {
        return successor;
    }

    protected void setSuccessor(Parser successor) {
        this.successor = successor;
    }
}
