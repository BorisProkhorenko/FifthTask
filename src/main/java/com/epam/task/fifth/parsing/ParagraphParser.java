package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Composite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParagraphParser extends AbstractParser {
    private final static String DELIMITER = "(?<=[.!?])\\s";

    public ParagraphParser(Parser successor) {
        setSuccessor(successor);
    }

    @Override
    public Component parseText(String input) {
        String[] paragraphs = input.split(DELIMITER);
        List<Component> components = Arrays.stream(paragraphs)
                .map(getSuccessor()::parseText)
                .collect(Collectors.toList());
        return new Composite(components);
    }




}
