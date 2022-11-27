import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by: Nil Mackay
 * Student ID: 261077199
 */

class RoomTest {

    @Test
    void newRoom_WrongType_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Room("indoor pool"));
    }

    @Test
    void getType_NewRoom_KingOrQueenOrDouble() {
        Room roomKing = new Room("king");
        assertEquals("king", roomKing.getType());

        Room roomQueen = new Room("queen");
        assertEquals("queen", roomQueen.getType());

        Room roomDouble = new Room("double");
        assertEquals("double", roomDouble.getType());
    }

    @Test
    void getPrice_NewRoom_150or110or90() {
        Room roomKing = new Room("king");
        assertEquals(150.0, roomKing.getPrice());

        Room roomQueen = new Room("queen");
        assertEquals(110.0, roomQueen.getPrice());

        Room roomDouble = new Room("double");
        assertEquals(90.0, roomDouble.getPrice());
    }

    @Test
    void changeAvailability_NewRoom_NotAvailable() {
        Room room = new Room("double");
        room.changeAvailability();
        assertFalse(room.getAvailability());
    }

    @Test
    void availableRoomsShouldNotBeEmpty() {
        Room[] rooms = {new Room("double"),
                new Room("double"),
                new Room("queen"),
                new Room("king"), };

        rooms[1].changeAvailability();
        rooms[3].changeAvailability();

        assertEquals(Room.findAvailableRoom(rooms, "double"), rooms[0]);
    }

}