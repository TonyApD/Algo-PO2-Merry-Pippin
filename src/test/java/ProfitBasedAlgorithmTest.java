import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class ProfitBasedAlgorithmTest extends ExampleCases {

    @Override
    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ProfitBasedAlgorithm.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
