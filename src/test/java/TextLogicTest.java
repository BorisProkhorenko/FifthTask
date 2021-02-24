import com.epam.task.fifth.components.Component;
import com.epam.task.fifth.components.LexemeLeaf;
import com.epam.task.fifth.components.TextComposite;
import com.epam.task.fifth.data.DataException;
import com.epam.task.fifth.enums.LexemeType;
import com.epam.task.fifth.logic.TextLogic;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextLogicTest {
    private static final TextLogic LOGIC = new TextLogic();
    private static final TextComposite TEXT_COMPOSITE = new TextComposite();
    private static final TextComposite FIRST_SENTENCE = new TextComposite();
    private static final TextComposite SECOND_SENTENCE = new TextComposite();
    private static final TextComposite THIRD_SENTENCE = new TextComposite();
    private static final TextComposite FIRST_PARAGRAPH = new TextComposite();
    private static final TextComposite SECOND_PARAGRAPH = new TextComposite();

    private static final TextComposite TEXT_COMPOSITE_SORTED_LEXEMES = new TextComposite();
    private static final TextComposite FIRST_SENTENCE_SORTED_LEXEMES = new TextComposite();
    private static final TextComposite SECOND_SENTENCE_SORTED_LEXEMES = new TextComposite();
    private static final TextComposite THIRD_SENTENCE_SORTED_LEXEMES = new TextComposite();
    private static final TextComposite FIRST_PARAGRAPH_SORTED_LEXEMES = new TextComposite();
    private static final TextComposite SECOND_PARAGRAPH_SORTED_LEXEMES = new TextComposite();

    private static final TextComposite EXPRESSIONS_SENTENCE = new TextComposite();
    private static final TextComposite CALCULATED_SENTENCE = new TextComposite();
    private static final TextComposite FIRST_EXPRESSION_PARAGRAPH = new TextComposite();
    private static final TextComposite SECOND_EXPRESSION_PARAGRAPH = new TextComposite();
    private static final TextComposite FIRST_CALCULATED_PARAGRAPH = new TextComposite();
    private static final TextComposite SECOND_CALCULATED_PARAGRAPH = new TextComposite();
    private static final TextComposite EXPRESSIONS_TEXT = new TextComposite();
    private static final TextComposite CALCULATED_TEXT = new TextComposite();


    @BeforeClass
    public static void initialize() {

        FIRST_SENTENCE.addComponent(new LexemeLeaf("Hello", LexemeType.WORD));
        FIRST_SENTENCE.addComponent(new LexemeLeaf("World", LexemeType.WORD));
        FIRST_SENTENCE.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));

        SECOND_SENTENCE.addComponent(new LexemeLeaf("I", LexemeType.WORD));
        SECOND_SENTENCE.addComponent(new LexemeLeaf("love", LexemeType.WORD));
        SECOND_SENTENCE.addComponent(new LexemeLeaf("Java", LexemeType.WORD));
        SECOND_SENTENCE.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));

        FIRST_PARAGRAPH.addComponent(FIRST_SENTENCE);
        FIRST_PARAGRAPH.addComponent(SECOND_SENTENCE);

        THIRD_SENTENCE.addComponent(new LexemeLeaf("Second", LexemeType.WORD));
        THIRD_SENTENCE.addComponent(new LexemeLeaf("paragraph", LexemeType.WORD));
        THIRD_SENTENCE.addComponent(new LexemeLeaf("?", LexemeType.PUNCTUATION));

        SECOND_PARAGRAPH.addComponent(THIRD_SENTENCE);

        TEXT_COMPOSITE.addComponent(FIRST_PARAGRAPH);
        TEXT_COMPOSITE.addComponent(SECOND_PARAGRAPH);


        FIRST_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("!", LexemeType.PUNCTUATION));
        FIRST_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("Hello", LexemeType.WORD));
        FIRST_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("World", LexemeType.WORD));

        SECOND_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));
        SECOND_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("I", LexemeType.WORD));
        SECOND_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("Java", LexemeType.WORD));
        SECOND_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("love", LexemeType.WORD));


        FIRST_PARAGRAPH_SORTED_LEXEMES.addComponent(FIRST_SENTENCE_SORTED_LEXEMES);
        FIRST_PARAGRAPH_SORTED_LEXEMES.addComponent(SECOND_SENTENCE_SORTED_LEXEMES);

        THIRD_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("?", LexemeType.PUNCTUATION));
        THIRD_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("Second", LexemeType.WORD));
        THIRD_SENTENCE_SORTED_LEXEMES.addComponent(new LexemeLeaf("paragraph", LexemeType.WORD));


        SECOND_PARAGRAPH_SORTED_LEXEMES.addComponent(THIRD_SENTENCE_SORTED_LEXEMES);

        TEXT_COMPOSITE_SORTED_LEXEMES.addComponent(FIRST_PARAGRAPH_SORTED_LEXEMES);
        TEXT_COMPOSITE_SORTED_LEXEMES.addComponent(SECOND_PARAGRAPH_SORTED_LEXEMES);

        EXPRESSIONS_SENTENCE.addComponent(new LexemeLeaf("Test", LexemeType.WORD));
        EXPRESSIONS_SENTENCE.addComponent(new LexemeLeaf("[12345+-/*]", LexemeType.EXPRESSION));
        EXPRESSIONS_SENTENCE.addComponent(new LexemeLeaf("[12345+-*/]", LexemeType.EXPRESSION));
        EXPRESSIONS_SENTENCE.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));

        CALCULATED_SENTENCE.addComponent(new LexemeLeaf("Test", LexemeType.WORD));
        CALCULATED_SENTENCE.addComponent(new LexemeLeaf("3", LexemeType.WORD));
        CALCULATED_SENTENCE.addComponent(new LexemeLeaf("12", LexemeType.WORD));
        CALCULATED_SENTENCE.addComponent(new LexemeLeaf(".", LexemeType.PUNCTUATION));


        FIRST_EXPRESSION_PARAGRAPH.addComponent(EXPRESSIONS_SENTENCE);
        FIRST_EXPRESSION_PARAGRAPH.addComponent(SECOND_SENTENCE);

        SECOND_EXPRESSION_PARAGRAPH.addComponent(FIRST_SENTENCE);
        SECOND_EXPRESSION_PARAGRAPH.addComponent(EXPRESSIONS_SENTENCE);
        EXPRESSIONS_TEXT.addComponent(FIRST_EXPRESSION_PARAGRAPH);
        EXPRESSIONS_TEXT.addComponent(SECOND_EXPRESSION_PARAGRAPH);

        FIRST_CALCULATED_PARAGRAPH.addComponent(CALCULATED_SENTENCE);
        FIRST_CALCULATED_PARAGRAPH.addComponent(SECOND_SENTENCE);

        SECOND_CALCULATED_PARAGRAPH.addComponent(FIRST_SENTENCE);
        SECOND_CALCULATED_PARAGRAPH.addComponent(CALCULATED_SENTENCE);
        CALCULATED_TEXT.addComponent(FIRST_CALCULATED_PARAGRAPH);
        CALCULATED_TEXT.addComponent(SECOND_CALCULATED_PARAGRAPH);


    }

    @Test
    public void testSortParagraphsWhenTextCompositeApplied() throws DataException {
        //given
        TextComposite expected = new TextComposite();
        expected.addComponent(SECOND_PARAGRAPH);
        expected.addComponent(FIRST_PARAGRAPH);
        //when
        Component actual = LOGIC.sortParagraphs(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(expected, actual);
    }


    @Test(expected = DataException.class)
    public void testSortParagraphsWhenParagraphCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortParagraphs(FIRST_PARAGRAPH);
    }

    @Test(expected = DataException.class)
    public void testSortParagraphsWhenSentenceCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortParagraphs(FIRST_SENTENCE);
    }


    @Test
    public void testSortLexemesWhenSentenceCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemes(THIRD_SENTENCE);
        //then
        Assert.assertEquals(THIRD_SENTENCE_SORTED_LEXEMES, actual);
    }

    @Test(expected = DataException.class)
    public void testSortLexemesWhenTextCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemes(TEXT_COMPOSITE);
    }

    @Test(expected = DataException.class)
    public void testSortLexemesWhenParagraphCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemes(FIRST_PARAGRAPH);
    }

    @Test
    public void testSortLexemesForEachSentenceSafeWhenTextCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemesInEachSentenceSafe(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(TEXT_COMPOSITE_SORTED_LEXEMES, actual);
    }

    @Test
    public void testSortLexemesForEachSentenceSafeWhenParagraphCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemesInEachSentenceSafe(FIRST_PARAGRAPH);
        //then
        Assert.assertEquals(FIRST_PARAGRAPH_SORTED_LEXEMES, actual);
    }

    @Test(expected = DataException.class)
    public void testSortLexemesForEachSentenceSafeWhenSentenceCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.sortLexemesInEachSentenceSafe(FIRST_SENTENCE);
    }

    @Test
    public void testCalculateExpressionsWhenSentenceCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressions(EXPRESSIONS_SENTENCE);
        //then
        Assert.assertEquals(CALCULATED_SENTENCE, actual);
    }

    @Test(expected = DataException.class)
    public void testCalculateExpressionsWhenTextCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressions(TEXT_COMPOSITE);
    }

    @Test(expected = DataException.class)
    public void testCalculateExpressionsWhenParagraphCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressions(SECOND_PARAGRAPH);
    }

    @Test
    public void testCalculateExpressionForEachSentenceSafeWhenTextCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressionsInEachSentenceSafe(EXPRESSIONS_TEXT);
        //then
        Assert.assertEquals(CALCULATED_TEXT, actual);
    }

    @Test
    public void testCalculateExpressionForEachSentenceSafeWhenParagraphCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressionsInEachSentenceSafe(FIRST_EXPRESSION_PARAGRAPH);
        //then
        Assert.assertEquals(FIRST_CALCULATED_PARAGRAPH, actual);
    }

    @Test(expected = DataException.class)
    public void testCalculateExpressionForEachSentenceSafeWhenSentenceCompositeApplied() throws DataException {
        //when
        Component actual = LOGIC.calculateExpressionsInEachSentenceSafe(FIRST_SENTENCE);
    }
}
