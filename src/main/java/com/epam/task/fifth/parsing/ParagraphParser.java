package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.TextComposite;

import java.util.Arrays;
import java.util.List;

public class ParagraphParser extends AbstractParser {
    private final static String SPLITTER = "(?<=[.!?])\\s";
    private final static String DELIMITER = " ";

    public ParagraphParser(Parser successor) {
        setSuccessor(successor);
    }

    @Override
    public Component parseComponents(String input) {
        String[] sentences = input.split(SPLITTER);
        TextComposite paragraph = new TextComposite();
        Arrays.stream(sentences).forEach(sentence -> {
            sentence = sentence.trim();
            Component component = getSuccessor().parseComponents(sentence);
            paragraph.addComponent(component);
        });
        return paragraph;
    }

    @Override
    public String parseString(TextComposite composite) {
        StringBuffer buffer = new StringBuffer();
        List<Component> components = composite.getComponents();
        components.stream().forEach(component -> {
            TextComposite textComposite = (TextComposite) component;
            String value = getSuccessor().parseString(textComposite);
            buffer.append(value);
            buffer.append(DELIMITER);
        });
        String paragraph = buffer.toString();
        return paragraph.trim();
    }


}
