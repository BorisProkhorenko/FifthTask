package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.entity.components.Lexeme;

public class CompositeTypeChecker {

    private static final String SENTENCE = "SENTENCE";
    private static final String PARAGRAPH = "PARAGRAPH";
    private static final String TEXT = "TEXT";

    /*package-private*/ boolean isCompositeType(Composite composite, String type) {
        try {
            switch (type.toUpperCase()) {
                case SENTENCE:
                    return composite.getChild(0) instanceof Lexeme;
                case PARAGRAPH:
                    Composite sentence = (Composite) composite.getChild(0);
                    return isCompositeType(sentence, SENTENCE);
                case TEXT:
                    Composite paragraph = (Composite) composite.getChild(0);
                    return isCompositeType(paragraph, PARAGRAPH);
                default:
                    return false;
            }
        } catch (ClassCastException e) {
            return false;
        }
    }

    /*package-private*/ void checkCompositeType(Composite composite, String type) throws ComponentException {
        if (!isCompositeType(composite, type)) {
            throw new ComponentException("Incorrect type of composite");
        }
    }
}
