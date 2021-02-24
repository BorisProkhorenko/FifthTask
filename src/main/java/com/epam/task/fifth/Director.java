package com.epam.task.fifth;

import com.epam.task.fifth.components.Component;
import com.epam.task.fifth.components.TextComposite;
import com.epam.task.fifth.data.DataException;
import com.epam.task.fifth.data.DataReader;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.SentenceParser;
import com.epam.task.fifth.parsing.TextParser;

public class Director {

    private SentenceParser sentenceParser;
    private ParagraphParser paragraphParser;
    private TextParser textParser;
    private DataReader reader;

    public Director() {
        DataReader reader = new DataReader();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
    }

    public Director(SentenceParser sentenceParser, DataReader reader) {
        this.sentenceParser = sentenceParser;
        this.reader = reader;
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextParser textParser = new TextParser(paragraphParser);
    }

    public Component parseComponentsFromFile(String filename) throws DataException {
        String text = reader.readData(filename);
        return chainParseComponents(text);
    }

    public Component chainParseComponents(String text) {
        return textParser.parseComponents(text);
    }

    public String chainParseString(TextComposite composite) {
        return textParser.parseString(composite);
    }


}
