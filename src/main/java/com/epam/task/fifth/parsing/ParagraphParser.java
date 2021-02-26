package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.TextComposite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParagraphParser extends AbstractParser {
    private final static String SPLITTER = "(?<=[.!?])\\s";
    private final static String DELIMITER = " ";

    public ParagraphParser(Parser successor) {
        setSuccessor(successor);
    }

    @Override
    public Component parseText(String input) {
        String[] paragraphs = input.split(SPLITTER);
        List<Component> components = Arrays.stream(paragraphs)
                .map(getSuccessor()::parseText)
                .collect(Collectors.toList());
        return new TextComposite(components);
    }

    @Override
    public String parseComponent(TextComposite textComposite) {
        List<Component> components = textComposite.getComponents();
        String text = components.stream()
                .map((Component composite) -> getSuccessor().parseComponent((TextComposite) composite))
                .collect(Collectors.joining(DELIMITER));
        return text;
    }


}
