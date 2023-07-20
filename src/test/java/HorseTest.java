import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @Test
    void nullName () {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 100));

        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t\t", "\n"})
    void blankName (String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10, 100));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

}