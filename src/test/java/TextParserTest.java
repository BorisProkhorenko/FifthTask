import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.LexemeLeaf;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.TextParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextParserTest {
    private static final String TEXT = "\tHello World! I love Java.\n\tSecond paragraph? Yes!";
    private static final String FIRST_PARAGRAPH = "Hello World! I love Java.";
    private static final String SECOND_PARAGRAPH = "Second paragraph? Yes!";
    private static TextComposite testComposite = new TextComposite();

    @Test
    public void testParse() {
        //given
        TextComposite firstSentence = new TextComposite();
        firstSentence.addComponent(new LexemeLeaf("Hello", LexemeType.WORD));
        firstSentence.addComponent(new LexemeLeaf("World", LexemeType.WORD));
        firstSentence.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));

        TextComposite secondSentence = new TextComposite();
        secondSentence.addComponent(new LexemeLeaf("I", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf("love", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf("Java", LexemeType.WORD));
        secondSentence.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));

        TextComposite firstParagraph = new TextComposite();
        firstParagraph.addComponent(firstSentence);
        firstParagraph.addComponent(secondSentence);

        TextComposite thirdSentence = new TextComposite();
        thirdSentence.addComponent(new LexemeLeaf("Second", LexemeType.WORD));
        thirdSentence.addComponent(new LexemeLeaf("paragraph", LexemeType.WORD));
        thirdSentence.addComponent(new LexemeLeaf("?", LexemeType.PUNCTUATION));

        TextComposite fourthSentence = new TextComposite();
        fourthSentence.addComponent(new LexemeLeaf("Yes", LexemeType.WORD));
        fourthSentence.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));

        TextComposite secondParagraph = new TextComposite();
        secondParagraph.addComponent(thirdSentence);
        secondParagraph.addComponent(fourthSentence);

        testComposite.addComponent(firstParagraph);
        testComposite.addComponent(secondParagraph);

        ParagraphParser mockParagraphParser = Mockito.mock(ParagraphParser.class);
        when(mockParagraphParser.parseComponents(anyString())).thenReturn(firstParagraph, secondParagraph);
        TextParser parser = new TextParser(mockParagraphParser);
        //when
        Component actual = parser.parseComponents(TEXT);
        //then
        Assert.assertEquals(testComposite, actual);
    }

    @Test
    public void testParseString() {
        //given
        testComposite = new TextComposite();
        Component firstComponent = new TextComposite();
        Component secondComponent = new TextComposite();
        testComposite.addComponent(firstComponent);
        testComposite.addComponent(secondComponent);
        ParagraphParser mockParagraphParser = Mockito.mock(ParagraphParser.class);
        when(mockParagraphParser.parseString(any(TextComposite.class))).thenReturn(FIRST_PARAGRAPH, SECOND_PARAGRAPH);
        TextParser parser = new TextParser(mockParagraphParser);
        //when
        String actual = parser.parseString(testComposite);
        //then
        Assert.assertEquals(TEXT, actual);
    }
}
