import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MoneySaveAppTest {

    //private SubstractMaximum app;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        //app = new SubstractMaximum();
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

    @Test
    public void testExample7() {
        executeAlgorithm("7 2\n" +
                "9 4 4 7 11 3 13", 40);
    }

    @Test
    public void testExample8() {
        executeAlgorithm("60 2\n" +
                "5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5", 290);
    }

    @Test
    public void testExample9(){
        executeAlgorithm("5 3\n" +
                "6 5 4 6 5", 20);
    }




    private void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        //SubstractMaximum.main(null);

        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
