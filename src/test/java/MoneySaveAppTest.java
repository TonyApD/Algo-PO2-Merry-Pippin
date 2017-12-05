import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MoneySaveAppTest {

    private MoneySaverApp app;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        app = new MoneySaverApp();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExample1() {
        executeAlgorithm("8 1\n" +
                "1 1 1 1 1 1 1 1", 0);

    }

    @Test
    public void testExample2() {
        executeAlgorithm("5 2\n" +
                "10 17 17 17 37", 90);

    }

    @Test
    public void testExample3() {
        executeAlgorithm("7 2\n" +
                "25 26 11 15 16 12 4", 100);

    }

    @Test
    public void testExample4() {
        executeAlgorithm("4 1\n" +
                "25 16 21 24", 80);

    }

    private void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MoneySaverApp.main(null);

        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
