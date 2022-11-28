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
            assertTrue (BookingSystem.getRandomNumberOfRooms() < 5 || BookingSystem.getRandomNumberOfRooms() > 50);

        }
    }

    // This is only necessary so that the coverage report stops thinking I forgot to cover a method in BookingSystem?
    @Test
    void bookingSystem_NewBookingSystem_NewBookingSystem() {
        assertNotNull(new BookingSystem());
    }

    @Test
    void createRooms_BookingSystem_3Rooms() {
        // Make sure that the correct number of rooms are created in the booking system
        assertEquals(3, BookingSystem.createRooms(3).length);
    }

}