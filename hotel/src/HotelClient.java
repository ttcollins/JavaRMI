import java.rmi.Naming;

public class HotelClient {

    /**
     * This method when called provides the various options to
     * use to execute actions on this program
     */
    private static void printOptions() {
        //This method prints the options available for the program and successfully exits
        System.out.println("Available options:\n\t1. java HotelClient list <server address> \n" +
                "\t2. java HotelClient book <server address> <type> <guest name>\n" +
                "\t3. java HotelClient guests <server address> \n" +
                "\t4. java HotelClient revenue <server address> \n");

        System.exit(0);
    }

    /**
     * This method invokes book() on the server.
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

    /**
     * This method invokes revenue() on the server
     * @param r
     */
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
     * This method invokes list() on the server
     * @param r
     */
    public static void list (RoomManager r){
        try{
            //array to store what is returned by list() from the server
            int[] p = new int[5];
            p = r.list();

            //properly formatted room costs
            String[] cost= {"55,000", "75,000", "80,000", "150,000", "230,000"};

            //prints out available rooms for each tpye and the respective price
            for (int i=0;i<5;i++){
                System.out.println( p[i] + " rooms of type " + i + "are available for " + cost[i] + " UGX per night");
            }
        }
        catch (Exception e){
            System.out.println("Received exception: "+e);
        }
    }
    
    /**
     * This method invokes guests() on the server
     * @param r
     */
    private static void guestDetails(RoomManager r){
        try{
            //Storing whatever is returned by the server into an array
            String[] guests = r.guests();

             //Checking the array to see if anyone really booked
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

    /**
     * This is the main method to which all commands from the terminal arrive
     * @param args
     */
    public static void main(String[] args) {

        //This will print the options if no specific method and server address are provided
        if(args.length < 2) {
            printOptions();
        }

        try {
            RoomManager r = (RoomManager) Naming.lookup("rmi://" + args[1] + "/HotelService");

            //checks on the commands entered to refer them to the right actions
            if(args[0].equals("book")) {
                booking(args, r);
            } else if(args[0].equals("guests")) {
                guestDetails(r);
            } else if(args[0].equals("revenue")){
                revenue(r);
            } else if(args[0].equals("list")){
                list(r);
            }
        } catch(Exception e) {
            System.out.println("Received exception: " + e);
        }
    }

}
