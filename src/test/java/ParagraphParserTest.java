import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.LexemeLeaf;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.SentenceParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "Hello World! I love Java.";
    private static final String FIRST_SENTENCE = "Hello World!";
    private static final String SECOND_SENTENCE = "I love Java.";
    private static TextComposite testComposite;


    @Test
    public void testParseText() {
        //given
        testComposite = new TextComposite();
        TextComposite firstSentence = new TextComposite();
        TextComposite secondSentence = new TextComposite();

        firstSentence.addComponent(new LexemeLeaf("Hello", LexemeType.WORD));
        firstSentence.addComponent(new LexemeLeaf("World", LexemeType.WORD));
        firstSentence.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));

        secondSentence.addComponent(new LexemeLeaf("I", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf("love", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf("Java", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));
        testComposite.addComponent(firstSentence);
        testComposite.addComponent(secondSentence);

        SentenceParser mockSentenceParser = Mockito.mock(SentenceParser.class);
        when(mockSentenceParser.parseText(anyString())).thenReturn(firstSentence, secondSentence);
        ParagraphParser parser = new ParagraphParser(mockSentenceParser);
        //when
        Component actual = parser.parseText(PARAGRAPH);
        //then
        Assert.assertEquals(testComposite, actual);
    }

    @Test
    public void testParseComponent() {
        //given
        testComposite = new TextComposite();
        Component firstComponent = new TextComposite();
        Component secondComponent = new TextComposite();
        testComposite.addComponent(firstComponent);
        testComposite.addComponent(secondComponent);
        SentenceParser mockSentenceParser = Mockito.mock(SentenceParser.class);
        when(mockSentenceParser.parseComponent(any(TextComposite.class))).thenReturn(FIRST_SENTENCE, SECOND_SENTENCE);
        ParagraphParser parser = new ParagraphParser(mockSentenceParser);
        //when
        String actual = parser.parseComponent(testComposite);
        //then
        Assert.assertEquals(PARAGRAPH, actual);
    }
}
