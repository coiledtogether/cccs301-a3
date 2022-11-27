
public class Room {
    private String type;
    private double price;
    private boolean availability;

    public Room(String type) {
        // verify the type of room to set the corresponding price
        type = type.toLowerCase();
        if (type.equals("double")) {
            price = 90;
        }
        else if (type.equals("queen")) {
            price = 110;
        }
        else if (type.equals("king")) {
            price = 150;
        }
        else {
            throw new IllegalArgumentException("No room of type '" + type + "' can be created");
        }
        this.type = type;
        availability = true;
    }
    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
    public boolean getAvailability() {
        return availability;
    }
    public void changeAvailability() {
        availability = ! availability;
    }

    public static Room findAvailableRoom(Room [] rooms, String type) {
        // loop over the array of rooms to search an availabe room of passed type
        for (int i=0; i<rooms.length; i++) {
            // verify the type and avaolability of current room
            if (rooms[i].getType().equalsIgnoreCase(type) && rooms[i].getAvailability())
                // return reference to found room
                return rooms[i];
        }
        // no room was found, return null
        return null;
    }

}