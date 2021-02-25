import com.epam.task.fifth.interpretor.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionCalculatorTest {

    private static final String EXPRESSION = "[12345+-/*]";
    private static final Number RESULT = 3;
    private static ExpressionCalculator calculator = new ExpressionCalculator(EXPRESSION);

    @Test
    public void testCalculate() {
        //when
        Number actual = calculator.calculate();
        //then
        Assert.assertEquals(actual,RESULT);
    }
}
