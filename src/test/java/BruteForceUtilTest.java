import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class BruteForceUtilTest extends ExampleCases {

    @Override
    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        BruteForceUtil.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
