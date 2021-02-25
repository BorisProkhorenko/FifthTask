package com.epam.task.fifth;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.SentenceParser;
import com.epam.task.fifth.parsing.TextParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Director {

    private SentenceParser sentenceParser;
    private ParagraphParser paragraphParser;
    private TextParser textParser;
    private static final String LINE_DELIMITER = "\n";

    public Director() {
        sentenceParser = new SentenceParser();
        paragraphParser = new ParagraphParser(sentenceParser);
        textParser = new TextParser(paragraphParser);
    }

    public Director(SentenceParser sentenceParser) {
        this.sentenceParser = sentenceParser;
        paragraphParser = new ParagraphParser(sentenceParser);
        textParser = new TextParser(paragraphParser);
    }

    public Component parseComponentsFromFile(String filename) throws IOException {
        String text = readFromFile(filename);
        return chainParseComponents(text);
    }

    public String readFromFile(String filename) throws IOException {
        StringBuffer text = new StringBuffer();
        Files.lines(Paths.get(filename), StandardCharsets.UTF_8)
                .forEach(line -> text.append(line).append(LINE_DELIMITER));
        int index = text.lastIndexOf(LINE_DELIMITER);
        text.deleteCharAt(index);
        return text.toString();
    }

    public Component chainParseComponents(String text) {
        return textParser.parseComponents(text);
    }

    public String chainParseString(TextComposite composite) {
        return textParser.parseString(composite);
    }

}
