import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.LexemeLeaf;
import com.epam.task.fifth.entity.components.TextComposite;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.parsing.SentenceParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class SentenceParserTest {

    private static final String SENTENCE = "Hello [365+*] World!";
    private static SentenceParser parser;
    private static TextComposite testComponent;

    @BeforeClass
    public static void initialize() {
        parser = new SentenceParser();
        testComponent = new TextComposite();
        testComponent.addComponent(new LexemeLeaf("Hello", LexemeType.WORD));
        testComponent.addComponent(new LexemeLeaf("[365+*]", LexemeType.EXPRESSION));
        testComponent.addComponent(new LexemeLeaf("World", LexemeType.WORD));
        testComponent.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));
    }

    @Test
    public void testParseComponents() {
        //when
        Component actual = parser.parseComponents(SENTENCE);
        //then
        Assert.assertEquals(testComponent, actual);
    }

    @Test
    public void testParseString() {
        //when
        String actual = parser.parseString(testComponent);
        //then
        Assert.assertEquals(SENTENCE, actual);
    }
}
