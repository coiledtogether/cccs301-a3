import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by: Nil Mackay
 * Student ID: 261077199
 */

class HotelTest {

    // Output stream to dump console output into. Shared by most methods
    static ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

    // The old system output stream.
    // This is needed to reset the stream after making an assertion based on the console logs
    static PrintStream oldOut = System.out;

    // Evil code to redirect sysout to a byte array that I can read and judge
    void captureTheSystemOutput() {

        // Reset the byte array
        consoleOutput.reset();

        // This is where sysout will be redirected to
        PrintStream newOut = new PrintStream(consoleOutput);
        System.setOut(newOut);
    }

    // This flushes the byte array sysout and sets it back to the old sysout
    void releaseTheSystemOutput() {
        System.out.flush();
        System.setOut(oldOut);
    }

    @Test
    void createReservation_HotelWithNoReservedRooms_NoAvailableRoomsOfType() {

        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Try to make a reservation for a nonexistent room
        captureTheSystemOutput();

            cuteHotel.createReservation("nil", "king");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("Sorry nil, we have no available rooms of the desired type.\n"))) {
            fail();
        }
    }

    @Test
    void createReservation_HotelWithRequestedAvailableRoom_SuccessfullyReserved() {

        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Try to reserve an available room of the correct type.
        captureTheSystemOutput();

            cuteHotel.createReservation("nil", "double");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("You have successfully reserved a double room under the name nil. We look forward having you at the cute hotel\n"))) {
            fail();
        }
    }

    @Test
    void createReservation_HotelWithCorrectButUnavailableRooms_NoAvailableRooms() {

        // Create some rooms
        Room[] evilRooms = {new Room("king"),
                new Room("king"),
        };

        // Make all rooms unavailable
        for (Room r : evilRooms) {
            r.changeAvailability();
        }

        // Add them to the hotel
        Hotel evilHotel = new Hotel("evil hotel", evilRooms);

        // Try to reserve an unavailable room
        captureTheSystemOutput();

            evilHotel.createReservation("nil", "king");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("Sorry nil, we have no available rooms of the desired type.\n"))) {
            fail();
        }
    }

    @Test
    void cancelReservation_HotelWithOneReservedRoom_SuccessfullyCanceled() {
        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Create one reservation
        cuteHotel.createReservation("nil", "double");

        // Now cancel it
        captureTheSystemOutput();

            cuteHotel.cancelReservation("nil", "double");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("nil, your reservation for a doubleroom has been successfully cancelled.\n"))) {
            fail();
        }
    }

    @Test
    void cancelReservation_ReservedRoomInHotelWithManyReservations_SuccessfullyCanceled() {

        // Create a bunch of rooms
        Room[] cuteRooms = {new Room("king"),
                new Room("king"),
                new Room("double"),
                new Room("double"),
                new Room("double"),
                new Room("double"),
                new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Create a bunch of reservations
        cuteHotel.createReservation("nil", "king");
        cuteHotel.createReservation("nill", "king");
        cuteHotel.createReservation("nilll", "double");
        cuteHotel.createReservation("nillll", "double");
        cuteHotel.createReservation("nilllll", "double");
        cuteHotel.createReservation("nillllll", "double");

        // Cancel one of them
        captureTheSystemOutput();

            cuteHotel.cancelReservation("nil", "king");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("nil, your reservation for a kingroom has been successfully cancelled.\n"))) {
            fail();
        }
    }

    @Test
    void cancelReservation_NoReservedRoomInHotel_FailedToCancel() {
        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Create a reservation
        cuteHotel.createReservation("nil", "double");

        // Try to cancel a reservation that does not exist
        captureTheSystemOutput();

            cuteHotel.cancelReservation("nil's clone", "king");

        releaseTheSystemOutput();

        // Test will succeed if we get the proper failure console output
        if (!(consoleOutput.toString().equals("There is no reservation for a king room under the name of nil's clone.\n"))) {
            fail();
        }
    }

    @Test
    void printInvoice_HotelWithTwoReservations_200DollarTotal() {
        Room[] cuteRooms = {new Room("queen"),
                new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Reserve both the rooms under the same name
        cuteHotel.createReservation("nil", "double");
        cuteHotel.createReservation("nil", "queen");

        // Print nil's invoice. This should sum the price of both reservations
        captureTheSystemOutput();

            cuteHotel.printInvoice("nil");

        releaseTheSystemOutput();

        if (!(consoleOutput.toString().equals("nil's invoice is of $200.0\n"))) {
            fail();
        }
    }

    @Test
    void printInvoice_HotelWithTwoReservations_90DollarTotalFromJustNil() {
        Room[] cuteRooms = {new Room("king"),
                new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Create two reservations in different names
        cuteHotel.createReservation("nil", "double");
        cuteHotel.createReservation("nil's worst enemy who deserves to be left with a huge bill", "king");

        // Print nil's invoice, this should only display the price of one of the reservations
        captureTheSystemOutput();

            cuteHotel.printInvoice("nil");

        releaseTheSystemOutput();

        if (!(consoleOutput.toString().equals("nil's invoice is of $90.0\n"))) {
            fail();
        }
    }

    @Test
    void toString_HotelWithAllThreeRoomTypes_CorrectOutput() {
        Room[] cuteRooms = {new Room("double"),
                new Room("double"),
                new Room("double"),
                new Room("queen"),
                new Room("queen"),
                new Room("king"),};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        captureTheSystemOutput();

            System.out.println(cuteHotel);

        releaseTheSystemOutput();

        if (!(consoleOutput.toString().equals("Hotel name: the cute hotel\nAvailable Rooms: 3 double, 2 queen, 1 king.\n"))) {
            fail();
        }
    }


    // These following two tests are for lines of code that (as far as I can tell) can't be reached through normal use of
    // the code assigned to us, at least in the state it's in.
    // There's no reason you'd actually want to do this in real life, but I
    // wanted to see if it was possible because I had a lot of fun writing all the other tests.
    //
    // Please let me know in the feedback if there is indeed a way to access lines 39 and 92
    // in Hotel without doing these horrible things. I'm really curious as to if I missed something obvious

    @Test
    void addReservation_HotelWithOneReservation_ForbiddenOutput() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Make a reservation
        cuteHotel.createReservation("nil","double");

        // This creates a method object tied to any method in a given class (Hotel.class) here.
        // getDeclaredMethod lets you bypass Java's normal method access control since it's just
        // reflecting the declared code in a class without technically accessing it in the normal way.
        Method addReservation = Hotel.class.getDeclaredMethod("addReservation", Reservation.class);

        // Sets the method instance to be accessible publicly.
        addReservation.setAccessible(true);

        // Invokes the private method to try and create a new reservation.

        captureTheSystemOutput();

            // Again, this should Never Ever happen
            addReservation.invoke(cuteHotel, new Reservation(cuteRooms[0], "why"));

        releaseTheSystemOutput();

        if (!(consoleOutput.toString().equals("Sorry, all rooms are already reserved\n"))) {
            fail();
        }
    }

    // You can do the same thing with a field too
    @Test
    void createReservation_HotelWithOneReservation_ForbiddenOutput() throws NoSuchFieldException, IllegalAccessException {
        Room[] cuteRooms = {new Room("double")};
        Hotel cuteHotel = new Hotel("the cute hotel", cuteRooms);

        // Instantiates a reflection of numOfReservations in Hotel.class
        Field numOfReservations = Hotel.class.getDeclaredField("numOfReservations");

        // Makes the field accessible
        numOfReservations.setAccessible(true);

        // Sets the number of reservations to some fake number that is too high
        numOfReservations.set(cuteHotel, 1234);

        // Try to create a reservation, the condition (numOfReservations < reservations.length) @ line 83 will fail
        captureTheSystemOutput();

            cuteHotel.createReservation("nil","double");

        releaseTheSystemOutput();

        // I broke it!
        if (!(consoleOutput.toString().equals("There is an issue with the reservation system\n"))) {
            fail();
        }
    }


}