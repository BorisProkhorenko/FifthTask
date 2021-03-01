import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.Composite;
import com.epam.task.fifth.entity.components.Lexeme;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.logic.ComponentException;
import com.epam.task.fifth.logic.TextLogic;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class TextLogicTest {

    private static final TextLogic LOGIC = new TextLogic();


    private static final Lexeme TEST = Lexeme.buildLexeme("test!", LexemeType.WORD);
    private static final Lexeme SENTENCE = Lexeme.buildLexeme("Sentence", LexemeType.WORD);
    private static final Lexeme WITH = Lexeme.buildLexeme("with", LexemeType.WORD);
    private static final Lexeme EXPRESSION_WORD = Lexeme.buildLexeme("expression.", LexemeType.WORD);
    private static final Lexeme SECOND = Lexeme.buildLexeme("Second", LexemeType.WORD);
    private static final Lexeme PARAGRAPH = Lexeme.buildLexeme("paragraph?", LexemeType.WORD);
    private static final Lexeme EXPRESSION = Lexeme.buildLexeme("[12345+-/*]", LexemeType.EXPRESSION);
    private static final Lexeme CALCULATED_EXPRESSION = Lexeme.buildLexeme("3", LexemeType.WORD);

    private static final Composite FIRST_SENTENCE = new Composite();
    private static final Composite SECOND_SENTENCE = new Composite();
    private static final Composite THIRD_SENTENCE = new Composite();
    private static final Composite FIRST_PARAGRAPH = new Composite();
    private static final Composite SECOND_PARAGRAPH = new Composite();
    private static final Composite TEXT_COMPOSITE = new Composite();

    private static final Composite FIRST_SENTENCE_SORTED_LEXEMES = new Composite();
    private static final Composite SECOND_SENTENCE_SORTED_LEXEMES = new Composite();
    private static final Composite PARAGRAPH_SORTED_LEXEMES = new Composite();
    private static final Composite CALCULATED_SENTENCE = new Composite();
    private static final Composite CALCULATED_PARAGRAPH = new Composite();




    @BeforeClass
    public static void initialize() {
        FIRST_SENTENCE.setComponents(Arrays.asList(SENTENCE,WITH,EXPRESSION,EXPRESSION_WORD));
        SECOND_SENTENCE.setComponents(Arrays.asList(SENTENCE,TEST));
        FIRST_PARAGRAPH.setComponents(Arrays.asList(FIRST_SENTENCE,SECOND_SENTENCE));
        THIRD_SENTENCE.setComponents(Arrays.asList(SECOND,PARAGRAPH));
        SECOND_PARAGRAPH.addComponent(THIRD_SENTENCE);
        TEXT_COMPOSITE.setComponents(Arrays.asList(FIRST_PARAGRAPH,SECOND_PARAGRAPH));

        FIRST_SENTENCE_SORTED_LEXEMES.setComponents(Arrays.asList(WITH,SENTENCE,EXPRESSION,EXPRESSION_WORD));
        SECOND_SENTENCE_SORTED_LEXEMES.setComponents(Arrays.asList(TEST,SENTENCE));
        PARAGRAPH_SORTED_LEXEMES.setComponents(Arrays.asList(FIRST_SENTENCE_SORTED_LEXEMES,SECOND_SENTENCE_SORTED_LEXEMES));

        CALCULATED_SENTENCE.setComponents(Arrays.asList(SENTENCE,WITH,CALCULATED_EXPRESSION,EXPRESSION_WORD));
        CALCULATED_PARAGRAPH.setComponents(Arrays.asList(CALCULATED_SENTENCE,SECOND_SENTENCE));

    }

    @Test
    public void testSortParagraphsWhenTextCompositeApplied() throws ComponentException {
        //given
        Composite expected = new Composite();
        expected.addComponent(SECOND_PARAGRAPH);
        expected.addComponent(FIRST_PARAGRAPH);
        //when
        Component actual = LOGIC.sortParagraphs(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(expected, actual);
    }


    @Test(expected = ComponentException.class)
    public void testSortParagraphsWhenParagraphCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortParagraphs(FIRST_PARAGRAPH);
    }

    @Test(expected = ComponentException.class)
    public void testSortParagraphsWhenSentenceCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortParagraphs(FIRST_SENTENCE);
    }


    @Test
    public void testSortLexemesWhenSentenceCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortLexemes(FIRST_SENTENCE);
        //then
        Assert.assertEquals(FIRST_SENTENCE_SORTED_LEXEMES, actual);
    }

    @Test(expected = ComponentException.class)
    public void testSortLexemesWhenTextCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortLexemes(TEXT_COMPOSITE);
    }

    @Test(expected = ComponentException.class)
    public void testSortLexemesWhenParagraphCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortLexemes(FIRST_PARAGRAPH);
    }


    @Test
    public void testSortLexemesForEachSentenceWhenParagraphCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortLexemesInEachSentence(FIRST_PARAGRAPH);
        //then
        Assert.assertEquals(PARAGRAPH_SORTED_LEXEMES, actual);
    }

    @Test(expected = ComponentException.class)
    public void testSortLexemesForEachSentenceSafeWhenSentenceCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.sortLexemesInEachSentence(FIRST_SENTENCE);
    }

    @Test
    public void testCalculateExpressionsWhenSentenceCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.calculateExpressions(FIRST_SENTENCE);
        //then
        Assert.assertEquals(CALCULATED_SENTENCE, actual);
    }

    @Test(expected = ComponentException.class)
    public void testCalculateExpressionsWhenTextCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.calculateExpressions(TEXT_COMPOSITE);
    }

    @Test(expected = ComponentException.class)
    public void testCalculateExpressionsWhenParagraphCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.calculateExpressions(SECOND_PARAGRAPH);
    }

    @Test(expected = ComponentException.class)
    public void testCalculateExpressionForEachSentenceSafeWhenSentenceCompositeApplied() throws ComponentException {
        //when
        Component actual = LOGIC.calculateExpressionsInEachSentence(FIRST_SENTENCE);
    }
}
