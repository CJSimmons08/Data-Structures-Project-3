import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class StudentTest {

    @Test
    public void testWire0() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/wire0.in");
        });
    }

    // your tests go here

    @Test
    public void testFiveByFiveOneWire(){
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/firstTest.in");
        });
    }

    @Test
    public void testFiveByFiveTwoWires(){
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/secondTest.in");
        });
    }

    @Test
    public void testTenByTenTwoWires(){
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/thirdTest.in");
        });
    }

    @Test
    public void testTenByTenThreeWires(){
        //Notably the third wire has the same destination as its source
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/fourthTest.in");
        });
    }

    @Test
    public void testFortyByFortyTenWires(){
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/fortyByFortyTest.in");
        });
    }

    @Test
    public void testRemoveBlockingWires(){
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/blockingWires.in");
        });
    }

}
