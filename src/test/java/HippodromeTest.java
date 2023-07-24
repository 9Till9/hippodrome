import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    void nullInConstructor () {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void emptyHorsesInConstructor () {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    void getHorsesTest () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("name" + i, i + 5, i + 12));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses
             ) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerTest () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1, j = 1000; i <= 10; i++, j -= 50) {
            horses.add(new Horse("name" + i, i, j));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(0), hippodrome.getWinner());
    }
}