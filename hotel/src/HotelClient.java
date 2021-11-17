import java.rmi.Naming;

public class HotelClient {

    private static void printOptions() {

        //This method prints the options available for the program and successfully exits
        System.out.println("Available options:\n\tHotelClient list <server address> \n" +
                "\tHotelClient book <server address> <type> <guest name>\n" +
                "\tHotelClient guests <server address> \n" +
                "\tHotelClient revenue <server address> \n");

        System.exit(0);
    }

    /**
     * This method invoces the book method on the server.
     * @param args
     * @param r
     */

    private static void booking(String[] args, RoomManager r) {
        String guest = "";

        //check if book <server address> <room type> and <guest name> have been provided
        if(args.length < 4) {
            printOptions();
        }

        //Add all provided guest names to the String guest
        for(int i = 3; i< args.length; i++){
            if(i!=3)
                guest += " ";
            guest += args[i];
        }

        //Calling the book method from the server
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


    private static void revenue(RoomManager r) {

        try {
            int[] roomRevenues ;
            int totalRevenue = 0;

            roomRevenues = r.revenue(); //Calling the revenue method from the server and storing them in an array

            for(int i=0; i< r.rooms.length; i++){
                totalRevenue += roomRevenues[i];
            }
            System.out.println("Total revenue: " + totalRevenue );

            System.out.println("Room types--------revenue generated");
            for(int i=0; i< r.rooms.length; i++){
                System.out.println( i + "\t \t revenue:" + roomRevenues[i]); //prints each of the revenue breakdowns
            }
        } catch(Exception e) {
            System.out.println("Received exception:" + e);
        }
    }


    /**
     * This method invokes the guests method on the server
     * @param r
     */
    private static void guestDetails(RoomManager r){
        try{
            /**
             * Storing whatever is returned by the server into an array
             */
            String[] guests = r.guests();

            /**
             * Checking the array to see if anyone really booked
             */
            if(guests.length < 1) {
                System.out.println("Nobody has booked yet...");
            } else {
                for(int i=0; i< guests.length; i++){
                    System.out.println(guests[i]);
                }
            }
        } catch (Exception e){
            System.out.println("Received exception: "+e);
        }
    }

    public static void list (RoomManager r){
        try{ 

        int[] p = new int[5]; 
        p = r.list();

        //prints out available rooms for each tpye and the respective price
        System.out.println( p[0] + " rooms of type 0 are available for 55,000 UGX per night");
        System.out.println( p[1] + " rooms of type 1 are available for 75,000 UGX per night"); 
        System.out.println( p[2] + " rooms of type 2 are available for 80,000 UGX per night");
        System.out.println( p[3] + " rooms of type 3 are available for 150,000 UGX per night");
        System.out.println( p[4] + " rooms of type 4 are available for 230,000 UGX per night");    
        }
        catch (Exception e){
            System.out.println("Received exception: "+e);
        }
    }

    public static void main(String[] args) {

        //This will print the options if no specific method and server address are provided
        if(args.length < 2) {
            printOptions();
        }

        try {
            RoomManager r = (RoomManager) Naming.lookup("rmi://" + args[1] + "/HotelService");
            
            if(args[0].equals("book")) {
                booking(args, r);
            } else if(args[0].equals("guests")) {
                guestDetails(r);
            }

            if(args[0].equals("revenue")){
                revenue(r);
            }

            if (args[0].equals("list")){
                list(r);
            }
        } catch(Exception e) {
            System.out.println("Received exception: " + e);
        }
    }

}
