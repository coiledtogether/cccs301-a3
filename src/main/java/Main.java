import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Mcgill booking system");
        System.out.println("Please enter the name of the hotel you'd like to book");
        String hotelName = input.nextLine();
        // get random number of room by calling getRandomNumberOfRooms()
        int numOfRooms = BookingSystem.getRandomNumberOfRooms();
        // create array of rooms by calling createRooms()
        Room[] rooms = BookingSystem.createRooms(numOfRooms);
        // create a hotel object constituted by the generated array of rooms
        Hotel hotel = new Hotel(hotelName, rooms);
        char choice ;
        String clientName = null;
        String roomType = null;
        do {
            System.out.println("\n***************************************************************");
            System.out.println("Welcome to " + hotelName + ". Chose one of the following options:");
            System.out.println("1) Make a reservation");
            System.out.println("2) Cancel a reservation");
            System.out.println("3) See an invoice");
            System.out.println("4) See hotel info");
            System.out.println("5) Exit the Booking System");
            System.out.println("***************************************************************\n");
            choice = input.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    // read client name and room time then call createReservation to try to create the reservation
                    System.out.print("Please enter your name: ");
                    clientName = input.nextLine();
                    System.out.print("What type of room would you like to reserve (double, queen, king)? ");
                    roomType = input.nextLine();
                    hotel.createReservation(clientName, roomType);
                    break;
                case '2':
                    // read client name and room time then call cancelReservation to try to cancel the reservation
                    System.out.print("Please enter the name you used to make the reservation: ");
                    clientName = input.nextLine();
                    System.out.print("What type of room did you reserve (double, queen, king)? ");
                    roomType = input.nextLine();
                    hotel.cancelReservation(clientName, roomType);
                    break;
                case '3':
                    // read the client name then call printInvoice to calculate and print its invoice
                    System.out.print("Please enter your name: ");
                    clientName = input.nextLine();
                    hotel.printInvoice(clientName);
                    break;
                case '4':
                    // call toString() on hotel object to print the available rooms
                    System.out.println("Here is the hotel info");
                    System.out.println(hotel.toString());
                    break;
                case '5':
                    System.out.println("It was a pleasure doing business with you!");
                    break;
                default:
                    System.out.println("Please enter a valid option.");

            }
        } while (choice != '5');

    }

}