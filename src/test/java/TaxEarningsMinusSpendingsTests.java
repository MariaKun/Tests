import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxEarningsMinusSpendingsTests {

    @ParameterizedTest
    @CsvSource({
            "30, 50",
            "50, 50",
            "7, 1",
            "0, 0"
    })
    public void taxIsZero_success(int earnings, int spendings) throws Exception {
        int result = Main.taxEarningsMinusSpendings(earnings, spendings);
        assertThat(result, is(0));
    }

    @ParameterizedTest
    @CsvSource({
            "-30, 50",
            "50, -30"
    })
    public void invalidValues_fail(int earnings, int spendings) {
        Throwable exception = assertThrows(Exception.class, () ->
                Main.taxEarningsMinusSpendings(earnings, spendings)
        );
        assertThat(exception.getMessage(), is("Невалидное значение параметра"));
    }

    @Test
    public void earnings_greaterThanSpendings_success() throws Exception {
        int result = Main.taxEarningsMinusSpendings(9, 2);
        assertThat(result, is(1));
    }
}
