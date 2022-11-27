import java.util.NoSuchElementException;

public class Hotel {
    private String name;
    private Room[] rooms;
    private Reservation[] reservations;
    private int numOfReservations;
    /**
     * @param name name of hotel
     * @param rooms array of hotel rooms
     */
    public Hotel(String name, Room[] rooms) {
        this.name = name;
        // create data member array of rooms
        this.rooms = new Room[rooms.length];
        // copy of the rooms passed as parameter into the data member array of rooms
        for (int i=0; i<rooms.length; i++)
            this.rooms[i] = rooms[i];
        // we could have reservations as much as we have rooms
        // creation of array of reservation to store the reservation
        reservations = new Reservation[rooms.length];
        // initialize the array to null as we don't have reservation yet
        for (int i=0; i<rooms.length; i++)
            reservations[i] = null;
        // initialize number of reservation to 0
        numOfReservations = 0;
    }
    /**
     * add a reservation to the array of reservation
     * @param reservation the reservation to add to the array of reservation
     */
    private void addReservation(Reservation reservation) {
        // verify if there is place in the reservation array to add new one
        if (numOfReservations < reservations.length) {
            reservations[numOfReservations] = reservation;
            numOfReservations++;
        }
        else {
            System.out.println("Sorry, all rooms are already reserved");
        }
    }
    /**
     * remove a reservation from the array of reservation
     * @param name the client name registered the reservation with his name
     * @param type type of reserved room
     */
    private void removeReservation(String name, String type) {
        // loop over the array of reservation
        for (int i = 0; i < numOfReservations; i++) {
            // test the name and type of current reservation if match with parameter
            if (reservations[i].getName().equalsIgnoreCase(name) &&
                    reservations[i].getRoom().getType().equalsIgnoreCase(type)) {
                // make the room available
                reservations[i].getRoom().changeAvailability();
                // loop from found reservation till last reservation in the array to shift them
                // one place to the left to remove the found reservation from the reservation array
                for (int j=i+1; j<numOfReservations; j++) {
                    reservations[j-1] = reservations[j];
                }
                // decrement the number of reservations
                numOfReservations--;
                return;
            }
        }
        // throw exception if reservation is not found
        throw new NoSuchElementException("There is no reservation for a " + type +
                " room under the name of " + name + ".");
    }
    /**
     * create a new reservation for the provided name and type
     * @param name name of client to reserve a room for
     * @param type type of room to reserve
     */
    public void createReservation(String name, String type) {
        // search for a room of provided type
        Room room = Room.findAvailableRoom(rooms, type);
        // test if there is available room of provided type
        if (room == null) {
            System.out.println("Sorry " + name + ", we have no available rooms of the desired type.");
        }
        else {
            // verify if there is a place for new reservation
            if (numOfReservations < reservations.length) {
                // make the room not available
                room.changeAvailability();
                // add new reservation to the array of reservation
                addReservation(new Reservation(room, name));
                System.out.println("You have successfully reserved a " + type +
                        " room under the name " + name + ". We look forward having you at " + this.name);
            }
            else {
                System.out.println("There is an issue with the reservation system");
            }
        }
    }
    /**
     * cancel a reservation concerning the provided client name and room type
     * @param name client name
     * @param type room type
     */
    public void cancelReservation(String name, String type) {
        try {
            // call method remove reservation to cancel it
            // if the reservation does not exist an exception is thrown
            removeReservation(name, type);
            System.out.println(name + ", your reservation for a " + type + "room has been successfully cancelled.");
        }
        // catch the thrown exception if no reservation found
        catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * calculate and print the invoice for a client
     * @param name client name
     */
    public void printInvoice(String name) {
        // loop over the reservation to verify the reservation of rooms
        double total = 0;
        for (int i=0; i<numOfReservations; i++) {
            // test if current reservation is for provided client name
            if (reservations[i].getName().equalsIgnoreCase(name)) {
                // add room price to the total
                total += reservations[i].getRoom().getPrice();
            }
        }
        // print the invoice
        System.out.println(name + "'s invoice is of $" + String.format("%.1f", total));
    }
    /**
     * return the available rooms in the hotel
     */
    public String toString() {
        int d=0, q=0, k=0; // counter to count number of rooms of each room type
        // loop over the hotl rooms
        for (int i = 0; i < rooms.length; i++) {
            // test if the current rooms is available
            if (rooms[i].getAvailability()) {
                // verify the type of room to increment corresponding type counter
                if (rooms[i].getType().equalsIgnoreCase("double"))
                    d++;
                else if (rooms[i].getType().equalsIgnoreCase("queen"))
                    q++;
                else if (rooms[i].getType().equalsIgnoreCase("king"))
                    k++;
            }
        }
        // create a string containing the counter of room types
        String res = "Hotel name: " + name + "\n";
        res += "Available Rooms: " + d + " double, " + q + " queen, " + k + " king.";
        return res;
    }
}