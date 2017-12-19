import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class SubstractMaximumTest extends ExampleCases {

    @Override
    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SubstractMaximum.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }

}
