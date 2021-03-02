package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.enums.LexemeType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceParser extends AbstractParser {

    private final static String PATTERN_EXPRESSION = "\\[[\\d*+/\\-]+]";
    private final static String DELIMITER = "\\s";
    private final static String EMPTY = "";


    @Override
    public Component parseText(String input) {
        String[] lexemes = input.split(DELIMITER);
        List<Component> components = Arrays.stream(lexemes)
                .filter(lexeme->!lexeme.equals(EMPTY))
                .map(Lexeme::buildLexeme)
                .map(this::setLexemeType)
                .collect(Collectors.toList());
        return new Composite(components);
    }

    private Lexeme setLexemeType(Lexeme lexeme) {
        String value = lexeme.getValue();
        if (Pattern.matches(PATTERN_EXPRESSION, value)) {
            lexeme.setType(LexemeType.EXPRESSION);
        } else {
            lexeme.setType(LexemeType.WORD);
        }
        return lexeme;
    }
}
