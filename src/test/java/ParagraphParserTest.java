import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.ParagraphParser;
import com.epam.task.fifth.parsing.SentenceParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "Hello World! I love Java.";
    private static Composite testComposite;


    @Test
    public void testParseText() {
        //given
        testComposite = new Composite();
        Composite firstSentence = new Composite();
        Composite secondSentence = new Composite();

        firstSentence.addComponent( Lexeme.buildLexeme("Hello", LexemeType.WORD));
        firstSentence.addComponent(Lexeme.buildLexeme("World!", LexemeType.WORD));

        secondSentence.addComponent(Lexeme.buildLexeme("I", LexemeType.WORD));
        secondSentence.addComponent(Lexeme.buildLexeme("love", LexemeType.WORD));
        secondSentence.addComponent(Lexeme.buildLexeme("Java.", LexemeType.WORD));
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


}
