package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.entity.components.Lexeme;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextBuilder {

    private final static String DELIMITER = " ";
    private static final CompositeTypeChecker TYPE_CHECKER = new CompositeTypeChecker();
    private static final String TEXT = "TEXT";
    private final static String PARAGRAPH_DELIMITER = "\n\t";
    private final static String TABULATION = "\t";
    private final static int START_INDEX_TABULATION = 1;
    private final static int STEP_TABULATION = 2;


    public String build(Composite composite) {
        List<Component> copy = new ArrayList<>(composite.getComponents());
        StringJoiner joiner = new StringJoiner(DELIMITER,TABULATION,"");
        if (TYPE_CHECKER.isCompositeType(composite, TEXT)) {
            for (int i = START_INDEX_TABULATION; i < copy.size(); i += STEP_TABULATION) {
                copy.add(i, buildDelimiterComposite());
            }
        }

        return traverse(new Composite(copy), joiner);
    }

    private Composite buildDelimiterComposite() {
        Lexeme lexeme = Lexeme.buildLexeme(PARAGRAPH_DELIMITER);
        Composite composite = new Composite();
        composite.addComponent(lexeme);
        return composite;
    }

    private String traverse(Composite composite, StringJoiner joiner) {
        for (Component component : composite.getComponents()) {
            if (component instanceof Composite) {
                traverse((Composite) component, joiner);
            } else {
                Lexeme lexeme = (Lexeme) component;
                joiner.add(lexeme.getValue());
            }
        }
        return joiner.toString();

    }

}
