import java.util.Scanner;
import java.util.Random;

public class BookingSystem {

    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);

    //returns a random String from the above array.
    public static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    public static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code.
    /**
     * create an array of rooms
     * @param numOfRooms number of rooms to create
     * @return the array of rooms
     */
    public static Room[] createRooms(int numOfRooms) {
        // create an array of rooms of length numOfRooms
        Room[] rooms = new Room[numOfRooms];
        // loop over the array to create rooms with random generated type
        for (int i = 0; i < numOfRooms; i++)
            rooms[i] = new Room(getRandomType());
        // return the array of rooms
        return rooms;
    }

}