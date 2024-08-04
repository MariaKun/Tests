import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxEarningsTests {

    @ParameterizedTest
    @CsvSource({
            "0",
            "16"
    })
    public void taxIsZero_success(int earnings) throws Exception {
        int result = Main.taxEarnings(earnings);
        assertThat(result, is(0));
    }

    @Test
    public void invalidValue_fail() {
        Throwable exception = assertThrows(Exception.class, () ->
                Main.taxEarnings(-30)
        );
        assertThat(exception.getMessage(), is("Невалидное значение параметра"));
    }

    @Test
    public void earnings_greaterThanSpendings_success() throws Exception {
        int result = Main.taxEarnings(17);
        assertThat(result, is(1));
    }
}
