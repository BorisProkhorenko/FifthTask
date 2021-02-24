package com.epam.task.fifth.logic;

import com.epam.task.fifth.components.Component;
import com.epam.task.fifth.components.LexemeLeaf;
import com.epam.task.fifth.components.TextComposite;
import com.epam.task.fifth.data.DataException;
import com.epam.task.fifth.enums.LexemeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextLogic {

    private static final String SENTENCE = "SENTENCE";
    private static final String PARAGRAPH = "PARAGRAPH";
    private static final String TEXT = "TEXT";
    private static final Logger LOGGER = LogManager.getLogger();

    public Component sortParagraphs(TextComposite text) throws DataException {
        if (isCompositeType(text, TEXT)) {
            List<TextComposite> composites = new ArrayList<>();
            text.getComponents().stream().forEach(component -> {
                TextComposite composite = (TextComposite) component;
                composites.add(composite);
            });
            Collections.sort(composites);
            List<Component> components = new ArrayList<>(composites);
            return new TextComposite(components);
        } else {
            throw new DataException("composite is not a sentence");
        }
    }

    public Component calculateExpressions(TextComposite sentence) throws DataException {
        if (isCompositeType(sentence, SENTENCE)) {
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
        } else {
            throw new DataException("composite is not a sentence");
        }
    }


    public Component sortLexemes(TextComposite sentence) throws DataException {
        if (isCompositeType(sentence, SENTENCE)) {
            List<LexemeLeaf> lexemes = new ArrayList<>();
            sentence.getComponents().stream().forEach(component -> {
                LexemeLeaf leaf = (LexemeLeaf) component;
                lexemes.add(leaf);
            });
            Collections.sort(lexemes);
            List<Component> components = new ArrayList<>(lexemes);
            return new TextComposite(components);
        } else {
            throw new DataException("composite is not a sentence");
        }
    }

    public Component calculateExpressionsInEachSentenceSafe(TextComposite text) throws DataException {
        if (!isCompositeType(text, SENTENCE)) {
            return calculateExpressionsInEachSentence(text);
        } else {
            throw new DataException("composite is not a sentence");
        }
    }

    public Component sortLexemesInEachSentenceSafe(TextComposite text) throws DataException {
        if (!isCompositeType(text, SENTENCE)) {
            return sortLexemesInEachSentence(text);
        } else {
            throw new DataException("composite is not a sentence");
        }
    }

    private Component sortLexemesInEachSentence(TextComposite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            TextComposite composite = (TextComposite) component;
            if (composite.getChild(0) instanceof TextComposite) {
                sortLexemesInEachSentence(composite);
            } else {
                int index = components.indexOf(component);
                try {
                    components.set(index, sortLexemes(composite));
                } catch (DataException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        return new TextComposite(components);
    }

    private Component calculateExpressionsInEachSentence(TextComposite text) {
        List<Component> components = text.getComponents();
        components.stream().forEach(component -> {
            TextComposite composite = (TextComposite) component;
            if (composite.getChild(0) instanceof TextComposite) {
                calculateExpressionsInEachSentence(composite);
            } else {
                int index = components.indexOf(component);
                try {
                    components.set(index, calculateExpressions(composite));
                } catch (DataException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        return new TextComposite(components);
    }


    private boolean isCompositeType(TextComposite composite, String type) throws DataException {
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
                    throw new DataException("Incorrect type of composite");
            }
        } catch (ClassCastException e) {
            throw new DataException("Incorrect type of composite", e);
        }
    }
}
