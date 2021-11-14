import java.rmi.Naming;

public class HotelClient {

    /**
     * This method prints the options available for the program and successfully exits
     */
    private static void printOptions() {
        System.out.println("Available options:\n\tHotelClient list <server address> \n" +
                "\tHotelClient book <server address> <type> <guest name>\n" +
                "\tHotelClient guests <server address> \n" +
                "\tHotelClient revenue <server address> \n");

        System.exit(0);
    }

    private static void booking(String[] args, RoomManager r) {
        String guest = "";

        /**
         * check if book <server address> <room type> and <guest name> have been provided
         */
        if(args.length < 4) {
            printOptions();
        }

        /**
         * Add all provided guest names to the String guest
         */
        for(int i = 3; i< args.length; i++){
            if(i!=3)
                guest += " ";
            guest += args[i];
        }

        /**
         * Calling the book method from the server
         */
        try {
            if(!r.book(Integer.parseInt(args[2]), guest)) {
                System.out.println("Booking Failed...");
                System.out.println("All rooms are possibly booked!");
            } else {
                System.out.println("Booked room for: " + guest);
                System.out.println("Room Type: " + args[2]);
                System.out.println("Room Description: " + r.descriptions[Integer.parseInt(args[2])]);
                System.out.println("Price: " + r.prices[Integer.parseInt(args[2])] );            }
        } catch(Exception e) {
            System.out.println("Received exception:" + e);
        }
    }

    public static void main(String[] args) {
        /**
         * This will print the options if no specific method and server address are provided
         */
        if(args.length < 2) {
            printOptions();
        }

        try {
            RoomManager r = (RoomManager) Naming.lookup("rmi://" + args[1] + "/HotelService");
            if(args[0].equals("book")){
                booking(args, r);
            }
        } catch(Exception e) {
            System.out.println("Received exception: " + e);
        }
    }

}
