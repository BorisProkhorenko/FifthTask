import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.TextParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextParserTest {

    private static final String TEXT = "\tHello World! I love Java.\n\tSecond paragraph? Yes!";
    private static Composite testComposite = new Composite();

    @Test
    public void testParseText() {
        //given
        Composite firstSentence = new Composite();
        firstSentence.addComponent(Lexeme.buildLexeme("Hello", LexemeType.WORD));
        firstSentence.addComponent(Lexeme.buildLexeme("World!", LexemeType.WORD));

        Composite secondSentence = new Composite();
        secondSentence.addComponent(Lexeme.buildLexeme("I", LexemeType.WORD));
        secondSentence.addComponent(Lexeme.buildLexeme("love", LexemeType.WORD));
        secondSentence.addComponent(Lexeme.buildLexeme("Java.", LexemeType.WORD));

        Composite firstParagraph = new Composite();
        firstParagraph.addComponent(firstSentence);
        firstParagraph.addComponent(secondSentence);

        Composite thirdSentence = new Composite();
        thirdSentence.addComponent(Lexeme.buildLexeme("Second", LexemeType.WORD));
        thirdSentence.addComponent(Lexeme.buildLexeme("paragraph?", LexemeType.WORD));

        Composite fourthSentence = new Composite();
        fourthSentence.addComponent(Lexeme.buildLexeme("Yes!", LexemeType.WORD));

        Composite secondParagraph = new Composite();
        secondParagraph.addComponent(thirdSentence);
        secondParagraph.addComponent(fourthSentence);

        testComposite.addComponent(firstParagraph);
        testComposite.addComponent(secondParagraph);

        ParagraphParser mockParagraphParser = Mockito.mock(ParagraphParser.class);
        when(mockParagraphParser.parseText(anyString())).thenReturn(firstParagraph, secondParagraph);
        TextParser parser = new TextParser(mockParagraphParser);
        //when
        Component actual = parser.parseText(TEXT);
        //then
        Assert.assertEquals(testComposite, actual);
    }

}
