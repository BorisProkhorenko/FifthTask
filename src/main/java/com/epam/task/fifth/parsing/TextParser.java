package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.TextComposite;

import java.util.Arrays;
import java.util.List;


public class TextParser extends AbstractParser {

    private final static String SPLITTER = "\t";
    private final static String DELIMITER = "\n";

    public TextParser(Parser successor) {
        setSuccessor(successor);
    }

    @Override
    public Component parseComponents(String input) {
        String[] paragraphs = input.split(SPLITTER);
        TextComposite text = new TextComposite();
        Arrays.stream(paragraphs).forEach(paragraph -> {
            Component component = getSuccessor().parseComponents(paragraph);
            text.addComponent(component);
        });
        return text;
    }

    @Override
    public String parseString(TextComposite composite) {
        StringBuffer buffer = new StringBuffer();
        List<Component> components = composite.getComponents();
        components.stream().forEach(component -> {
            TextComposite textComposite = (TextComposite) component;
            String value = getSuccessor().parseString(textComposite);
            buffer.append(SPLITTER);
            buffer.append(value);
            buffer.append(DELIMITER);
        });
        String text = buffer.toString();
        return SPLITTER + text.trim();
    }
}
