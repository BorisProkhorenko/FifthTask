import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.logic.TextBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class TextBuilderTest {


    private static final Lexeme FIRST = Lexeme.buildLexeme("First", LexemeType.WORD);
    private static final Lexeme SENTENCE = Lexeme.buildLexeme("Sentence.", LexemeType.WORD);
    private static final Lexeme SECOND = Lexeme.buildLexeme("Second", LexemeType.WORD);
    private static final Lexeme THIRD = Lexeme.buildLexeme("Third", LexemeType.WORD);

    private static final Composite FIRST_SENTENCE = new Composite();
    private static final Composite SECOND_SENTENCE = new Composite();
    private static final Composite THIRD_SENTENCE = new Composite();
    private static final Composite FIRST_PARAGRAPH = new Composite();
    private static final Composite SECOND_PARAGRAPH = new Composite();
    private static final Composite TEXT_COMPOSITE = new Composite();

    private static final TextBuilder BUILDER = new TextBuilder();


    @BeforeClass
    public static void initialize() {
        FIRST_SENTENCE.setComponents(Arrays.asList(FIRST, SENTENCE));
        SECOND_SENTENCE.setComponents(Arrays.asList(SECOND, SENTENCE));
        FIRST_PARAGRAPH.setComponents(Arrays.asList(FIRST_SENTENCE, SECOND_SENTENCE));
        THIRD_SENTENCE.setComponents(Arrays.asList(THIRD, SENTENCE));
        SECOND_PARAGRAPH.addComponent(THIRD_SENTENCE);
        TEXT_COMPOSITE.setComponents(Arrays.asList(FIRST_PARAGRAPH, SECOND_PARAGRAPH));

    }

    @Test
    public void testBuildTextWhenSentenceApplied() {
        //given
        String expected = "First Sentence.";
        //when
        String actual = BUILDER.build(FIRST_SENTENCE);
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBuildTextWhenParagraphApplied() {
        //given
        String expected = "First Sentence. Second Sentence.";
        //when
        String actual = BUILDER.build(FIRST_PARAGRAPH);
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBuildTextWhenTextApplied() {
        //given
        String expected = "\tFirst Sentence. Second Sentence. \n\t Third Sentence.";
        //when
        String actual = BUILDER.build(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(expected, actual);
    }
}
