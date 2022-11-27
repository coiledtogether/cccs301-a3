import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by: Nil Mackay
 * Student ID: 261077199
 */

class BookingSystemTest {

    // This will fail sometimes and is a bug in the original code, but it might not fail every time.
    // I couldn't think of a more elegant way to do this test, but in 500 values
    // there should be one out of range.
    @Test
    void getRandomNumberOfRooms_BookingSystem_Between5And50() {
        for (int i = 0; i < 1000; i++) {

            // Checks if getRandomNumberOfRooms() returned an out of bounds value.
            if (BookingSystem.getRandomNumberOfRooms() < 5 || BookingSystem.getRandomNumberOfRooms() > 50) {

                // Test fails if so.
                fail();
                return;
            }
        }

    }

    @Test
    void createRooms_BookingSystem_3Rooms() {
        int numRooms = 3;
        assertEquals(3, BookingSystem.createRooms(3).length);
    }

}