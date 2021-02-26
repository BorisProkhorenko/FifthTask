package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.LexemeLeaf;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.interpretor.ExpressionCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextLogic {

    private static final String SENTENCE = "SENTENCE";
    private static final String PARAGRAPH = "PARAGRAPH";
    private static final String TEXT = "TEXT";
    private static final Logger LOGGER = LogManager.getLogger();

    public Component sortParagraphs(TextComposite text) throws ComponentException {
        checkCompositeType(text, TEXT);
        List<Component> components = text.getComponents()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new TextComposite(components);
    }

    public Component calculateExpressions(TextComposite sentence) throws ComponentException {
        checkCompositeType(sentence, SENTENCE);
        List<LexemeLeaf> lexemes = new ArrayList<>();
        sentence.getComponents().stream().forEach(component -> {
            LexemeLeaf leaf = (LexemeLeaf) component;
            LexemeType type = leaf.getType();
            if (type.equals(LexemeType.EXPRESSION)) {
                ExpressionCalculator expressionCalculator = new ExpressionCalculator(leaf.getValue());
                Number result = expressionCalculator.calculate();
                leaf = new LexemeLeaf(result.toString(), LexemeType.WORD);
            }
            lexemes.add(leaf);
        });
        List<Component> components = new ArrayList<>(lexemes);
        return new TextComposite(components);
    }


    public Component sortLexemes(TextComposite sentence) throws ComponentException {
        checkCompositeType(sentence, SENTENCE);
        List<Component> components = sentence.getComponents()
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return new TextComposite(components);
    }

    public Component calculateExpressionsInEachSentence(TextComposite text) throws ComponentException {
        if (!isCompositeType(text, SENTENCE)) {
            return calculateExpressionsInEachSentenceRecursion(text);
        } else {
            throw new ComponentException("composite is not a sentence");
        }
    }

    public Component sortLexemesInEachSentence(TextComposite text) throws ComponentException {
        if (!isCompositeType(text, SENTENCE)) {
            return sortLexemesInEachSentenceRecursion(text);
        } else {
            throw new ComponentException("composite is not a sentence");
        }
    }

    private Component sortLexemesInEachSentenceRecursion(TextComposite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            TextComposite composite = (TextComposite) component;
            if (composite.getChild(0) instanceof TextComposite) {
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
        return new TextComposite(components);
    }

    private Component calculateExpressionsInEachSentenceRecursion(TextComposite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            TextComposite composite = (TextComposite) component;
            if (composite.getChild(0) instanceof TextComposite) {
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
        return new TextComposite(components);
    }

    private void checkCompositeType(TextComposite composite, String type) throws ComponentException {
        if (!isCompositeType(composite, type)) {
            throw new ComponentException("Incorrect type of composite");
        }
    }


    private boolean isCompositeType(TextComposite composite, String type) throws ComponentException {
        try {
            switch (type.toUpperCase()) {
                case SENTENCE:
                    if (composite.getChild(0) instanceof LexemeLeaf) {
                        return true;
                    } else {
                        return false;
                    }
                case PARAGRAPH:
                    TextComposite sentence = (TextComposite) composite.getChild(0);
                    return isCompositeType(sentence, SENTENCE);
                case TEXT:
                    TextComposite paragraph = (TextComposite) composite.getChild(0);
                    return isCompositeType(paragraph, PARAGRAPH);
                default:
                    throw new ComponentException("Incorrect type of composite");
            }
        } catch (ClassCastException e) {
            throw new ComponentException("Incorrect type of composite", e);
        }
    }
}
