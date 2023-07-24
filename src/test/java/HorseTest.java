import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

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

    @Test
    void negativeSpeedTest () {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Ідальго", -10, 100));

        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    void negativeDistanceTest () {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Мінні", 10, -100));

        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    void getNameTest () {
        Horse horse = new Horse("Kary", 10, 100);

        assertEquals("Kary", horse.getName());
    }

    @Test
    void getSpeedTest () {
        Horse horse = new Horse("Mary", 10, 100);

        assertEquals(10, horse.getSpeed());
    }

    @Test
    void getDistanceTest () {
        Horse horse = new Horse("Kary", 10, 100);

        assertEquals(100, horse.getDistance());
    }

    @Test
    void getDistanceIfConstructorHaveTwoArguments () {
        Horse horse = new Horse("Kary", 10);

        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void getRandomDoubleTestWithTwoParams () {
        try(MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Varya", 1, 10);
            horse.move();

            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}