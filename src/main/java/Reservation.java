
public class Reservation {

    private String name;
    private Room roomReserved;

    public Reservation(Room room, String name) {
        this.name = name;
        this .roomReserved = room;
    }
    public String getName() {
        return name;
    }
    public Room getRoom() {
        return roomReserved;
    }
}