import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MoneySaveAppTest extends ExampleCases {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Override
    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MoneySaverApp.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }

}
