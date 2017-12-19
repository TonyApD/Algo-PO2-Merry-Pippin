import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExampleCases {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
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
    public void testExample5() {
        executeAlgorithm("5 1\n" +
                "16 16 16 15 14", 70);
    }

    @Test
    public void testExample6() {
        executeAlgorithm("6 2\n" +
                "4 4 4 4 4 4", 20);
    }

    @Test
    public void testExample7() {
        executeAlgorithm("7 2\n" +
                "9 4 4 7 11 3 13", 40);
    }

    @Test
    public void testExample8() {
        executeAlgorithm("10 2\n" +
                "1 1 1 8 1 1 6 2 2 1", 20);
    }

    @Test
    public void testExample9() {
        executeAlgorithm("10 2\n" +
                "1 1 1 8 1 1 6 2 2 1", 20);
    }

    @Test
    public void testExample10() {
        executeAlgorithm("60 2\n" +
                "5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5 5 3 6 8 9 5 4 3 2 5", 290);
    }

    @Test
    public void testExample11() {
        executeAlgorithm("5 3\n" +
                "6 5 4 6 5", 20);
    }

    @Test
    public void testExample12() {
        executeAlgorithm("4 1\n" +
                "7 7 7 7", 20);
    }


    @Test
    public void testExample13() {
        executeAlgorithm("16 1\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 70);
    }

    @Test
    public void testExample14() {
        executeAlgorithm("16 2\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 70);
    }

    @Test
    public void testExample15() {
        executeAlgorithm("16 3\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 60);
    }

    @Test
    public void testExample16() {
        executeAlgorithm("16 4\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 60);
    }

    @Test
    public void testExample17() {
        executeAlgorithm("16 5\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 60);
    }

    @Test
    public void testExample18() {
        executeAlgorithm("16 6\n" +
                "10 3 1 9 4 1 3 10 8 10 1 5 1 2 4 3", 50);
    }

    @Test
    public void testExample19() {
        executeAlgorithm("13 2\n" +
                "56 7 26 78 27 4 2 90 7 5 56 32 1 ", 380);
    }

    @Test
    public void testExample20() {
        executeAlgorithm("13 1\n" +
                "56 7 26 78 27 4 2 90 7 5 56 32 1", 390);
    }

    @Test
    public void testExample21() {
        executeAlgorithm("18 0\n" +
                "56 7 26 4 1 3 10 8 78 27 4 2 90 7 5 56 32 1", 420);
    }

    @Test
    public void testExample22() {
        executeAlgorithm("18 1\n" +
                "56 7 26 4 1 3 10 8 78 27 4 2 90 7 5 56 32 1", 410);
    }

    @Test
    public void testExample23() {
        executeAlgorithm("18 2\n" +
                "56 7 26 4 1 3 10 8 78 27 4 2 90 7 5 56 32 1 ", 410);
    }

    @Test
    public void testExample24() {
        executeAlgorithm("18 3\n" +
                "56 7 26 4 1 3 10 8 78 27 4 2 90 7 5 56 32 1", 410);
    }

    @Test
    public void testExample25() {
        executeAlgorithm("18 4\n" +
                "56 7 26 4 1 3 10 8 78 27 4 2 90 7 5 56 32 1 ", 400);
    }

    @Test
    public void testExample26() {
        executeAlgorithm("6 2\n" +
                "12 24 39 12 24 39", 140);
    }

    @Test
    public void testExample27() {
        executeAlgorithm("27 5\n" +
                "27 95 21 62 97 35 34 86 25 8 23 68 24 87 22 56 89 38 28 21 80 45 65 32 23 34 88", 1290);
    }

    @Test
    public void testExample28() {
        executeAlgorithm("59 5\n" +
                "27 95 21 62 97 35 34 86 25 8 23 68 24 87 22 56 89 38 28 21 80 45 65 32 23 34 88 8 15 8 5 7 7 2 4 8 1 7 4 1 2 3 6 9 5 1 1 10 5 8 1 5 9 5 8 2 10 1 8", 1470);
    }

    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ProfitBasedAlgorithm.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
