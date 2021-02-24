import com.epam.task.fifth.data.DataException;
import com.epam.task.fifth.data.DataReader;
import org.junit.Assert;
import org.junit.Test;


public class DataReaderTest {

    private final static String TEST_FILE_PATH = "src/test/java/resources/input.txt";
    private final static String INVALID_FILE_PATH = "src/test/resources/invalid_input.txt";
    private final static String TEST_DATA = "Hello World!\nSecond line;)\n";
    private DataReader reader = new DataReader();

    @Test
    public void testReadDataWithValidFile() throws DataException {
        //when
        String actual = reader.readData(TEST_FILE_PATH);

        //then
        Assert.assertEquals(TEST_DATA, actual);
    }

    @Test(expected = DataException.class)
    public void testReadDataWithInvalidPath() throws DataException {

        //when
        String actual = reader.readData(INVALID_FILE_PATH);

    }

}
