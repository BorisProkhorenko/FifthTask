import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.SentenceParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class SentenceParserTest {


    private static final String SENTENCE = "Hello [365+*] World!";
    private static SentenceParser parser;
    private static Composite testComponent;

    @BeforeClass
    public static void initialize() {
        parser = new SentenceParser();
        testComponent = new Composite();
        testComponent.addComponent(Lexeme.buildLexeme("Hello", LexemeType.WORD));
        testComponent.addComponent(Lexeme.buildLexeme("[365+*]", LexemeType.EXPRESSION));
        testComponent.addComponent(Lexeme.buildLexeme("World!", LexemeType.WORD));

    }

    @Test
    public void testParseText() {
        //when
        Component actual = parser.parseText(SENTENCE);
        //then
        Assert.assertEquals(testComponent, actual);
    }


}
