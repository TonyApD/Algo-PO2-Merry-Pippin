import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class LinearPartitionTest extends ExampleCases {

    @Override
    public void executeAlgorithm(String input, int expectedOutput) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        LinearPartitionUtil.main(null);
        assertEquals(String.valueOf(expectedOutput), outContent.toString());
    }
}
