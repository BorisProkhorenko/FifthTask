package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.LexemeLeaf;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.logic.ComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {


    private final static String PATTERN_WORD = "[a-zA-Z]+";
    private final static String PATTERN_PUNCTUATION = "[.!?,:;]+";
    private final static String PATTERN_EXPRESSION = "\\[[\\d*+/\\-]+]";
    private final static String SPLITTER = "\\s|(?=" + PATTERN_PUNCTUATION + ")";
    private final static String DELIMITER = " ";
    private final static String EMPTY = "";
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public Component parseText(String input) {
        String[] lexemes = input.split(SPLITTER);
        TextComposite sentence = new TextComposite();
        for (String lexeme : lexemes) {
            lexeme = lexeme.trim();
            if (!lexeme.equals(EMPTY)) {
                LexemeLeaf component = new LexemeLeaf(lexeme);
                try {
                    setLexemeType(component);
                    sentence.addComponent(component);
                } catch (ComponentException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return sentence;
    }


    @Override
    public String parseComponent(TextComposite textComposite) {
        StringBuilder builder = new StringBuilder();
        List<Component> components = textComposite.getComponents();
        for (Component component : components) {
            LexemeLeaf leaf = (LexemeLeaf) component;
            String value = leaf.getValue();
            if (Pattern.matches(PATTERN_PUNCTUATION, value)) {
                int index = builder.lastIndexOf(DELIMITER);
                builder.deleteCharAt(index);
            }
            builder.append(value);
            builder.append(DELIMITER);
        }
        String sentence = builder.toString();
        return sentence.trim();
    }

    private void setLexemeType(LexemeLeaf lexeme) throws ComponentException {
        String value = lexeme.getValue();
        if (Pattern.matches(PATTERN_WORD, value)) {
            lexeme.setType(LexemeType.WORD);
        } else if (Pattern.matches(PATTERN_PUNCTUATION, value)) {
            lexeme.setType(LexemeType.PUNCTUATION);
        } else if (Pattern.matches(PATTERN_EXPRESSION, value)) {
            lexeme.setType(LexemeType.EXPRESSION);
        } else {
            throw new ComponentException("Incorrect lexeme in sentence");
        }
    }
}
