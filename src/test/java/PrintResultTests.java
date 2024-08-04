import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class PrintResultTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void TestPrints() {
        String name = "test";
        int nice = 3;
        int bad = 5;
        Main.printResult(name, nice, bad);
        String result = outputStreamCaptor.toString();
        assertThat(result, containsString("Мы советуем вам " + name));
        assertThat(result, containsString("Ваш налог составит: " + nice + " рублей"));
        assertThat(result, containsString("Налог на другой системе: " + bad + " рублей"));
        assertThat(result, containsString("Экономия: " + (bad - nice) + " рублей"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
