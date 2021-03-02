import com.epam.task.fifth.Director;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DirectorTest {
    private static final String FILENAME = "src/test/java/resources/input.txt";
    private static final String TEXT = "\tHello World! I love Java.\n\tSecond paragraph;)";
    private final Director director = new Director();

    @Test
    public void testReadFromFile() throws IOException {
        //when
        String actual = director.readFromFile(FILENAME);
        //then
        Assert.assertEquals(actual,TEXT);
    }
}
