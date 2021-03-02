package com.epam.task.fifth;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.SentenceParser;
import com.epam.task.fifth.parsing.TextParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class Director {

    private TextParser textParser;
    private static final String LINE_DELIMITER = "\n";

    public Director() {
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        textParser = new TextParser(paragraphParser);
    }


    public Component parseComponentsFromFile(String filename) throws IOException {
        String text = readFromFile(filename);
        return chainParseText(text);
    }

    public String readFromFile(String filename) throws IOException {
        StringJoiner joiner = new StringJoiner(LINE_DELIMITER);
        Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(joiner::add);
        return joiner.toString();
    }

    public Component chainParseText(String text) {
        return textParser.parseText(text);
    }


}
