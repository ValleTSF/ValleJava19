import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    private Gym gym = new Gym();

    @Test
    public void findKund() {
        assertTrue(gym.findKund("7603021234") != null);
        assertTrue(gym.findKund("9472738473") != "");
        assertFalse(gym.findKund("Alhambra Aromes").matches("\\d+"));
        assertTrue(gym.findKund("1337 smash") == "Personen är inte medlem!");

    }

    @Test
    public void frånFiltoList() {
        assertTrue(gym.frånFilToList());
        assertFalse(!gym.frånFilToList());
    }

}