package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.interpretor.ExpressionCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextLogic {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final CompositeTypeChecker TYPE_CHECKER = new CompositeTypeChecker();
    private static final String SENTENCE = "SENTENCE";
    private static final String TEXT = "TEXT";

    public Component sortParagraphs(Composite text) throws ComponentException {
        TYPE_CHECKER.checkCompositeType(text, TEXT);
        List<Component> components = text.getComponents()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new Composite(components);
    }

    public Component calculateExpressions(Composite sentence) throws ComponentException {
        TYPE_CHECKER.checkCompositeType(sentence, SENTENCE);
        List<Lexeme> lexemes = new ArrayList<>();
        sentence.getComponents().stream().forEach(component -> {
            Lexeme lexeme = (Lexeme) component;
            LexemeType type = lexeme.getType();
            if (type.equals(LexemeType.EXPRESSION)) {
                ExpressionCalculator expressionCalculator = new ExpressionCalculator(lexeme.getValue());
                Number result = expressionCalculator.calculate();
                lexeme =  Lexeme.buildLexeme(result.toString(), LexemeType.WORD);
            }
            lexemes.add(lexeme);
        });
        List<Component> components = new ArrayList<>(lexemes);
        return new Composite(components);
    }


    public Component sortLexemes(Composite sentence) throws ComponentException {
        TYPE_CHECKER.checkCompositeType(sentence, SENTENCE);
        List<Component> components = sentence.getComponents()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new Composite(components);
    }

    public Component calculateExpressionsInEachSentence(Composite text) throws ComponentException {
        if (!TYPE_CHECKER.isCompositeType(text, SENTENCE)) {
            return calculateExpressionsInEachSentenceRecursion(text);
        } else {
            throw new ComponentException("composite is not a sentence");
        }
    }

    public Component sortLexemesInEachSentence(Composite text) throws ComponentException {
        if (!TYPE_CHECKER.isCompositeType(text, SENTENCE)) {
            return sortLexemesInEachSentenceRecursion(text);
        } else {
            throw new ComponentException("composite is not a sentence");
        }
    }

    private Component sortLexemesInEachSentenceRecursion(Composite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            Composite composite = (Composite) component;
            if (composite.getChild(0) instanceof Composite) {
                sortLexemesInEachSentenceRecursion(composite);
            } else {
                int index = components.indexOf(component);
                try {
                    components.set(index, sortLexemes(composite));
                } catch (ComponentException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        return new Composite(components);
    }

    private Component calculateExpressionsInEachSentenceRecursion(Composite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            Composite composite = (Composite) component;
            if (composite.getChild(0) instanceof Composite) {
                calculateExpressionsInEachSentenceRecursion(composite);
            } else {
                int index = components.indexOf(component);
                try {
                    components.set(index, calculateExpressions(composite));
                } catch (ComponentException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        return new Composite(components);
    }

}
