import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by: Nil Mackay
 * Student ID: 261077199
 */

class ReservationTest {

    Room doubleRoom = new Room("double");
    Reservation testReservation = new Reservation(doubleRoom, "for me");

    // Checks if a new reservation has the correct name assigned to it
    @Test
    void getName_testReservation_ForMe() {
        assertEquals(testReservation.getName(), "for me");
    }

    // Checks if a new reservation has the correct type
    @Test
    void getRoom_testReservation_DoubleRoom() {
        assertEquals(testReservation.getRoom(), doubleRoom);
    }


}