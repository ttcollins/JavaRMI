import java.rmi.Naming;

public class HotelClient {

    /**
     * prints the usage and exits
     */
    static private void printUsage(){
        System.out.println("Available options:\n\thotelClient <address> list \n" +
                "\thotelClient <address> book <type> <Guest name>\n" +
                "\thotelClient <address> guests \n");

        System.exit(1);
    }

    /**
     * handle the request for a list of free rooms
     * @param c
     */
    static private void handleList(Hotel c){
        int[] rooms = new int[3];

        try{
            rooms = c.list();
        } catch (Exception e) {
            System.out.println("Received exception: " + e);
        }

        System.out.println(rooms[0] + "\t " + rooms[1] + "\t" + rooms[2]);
    }

    /**
     * handle the rquest for booking a room
     * @param args
     * @param c
     */
    static private void handleBook(String[] args, Hotel c){
        String name = "";

        if(args.length<4)
            printUsage();

        for(int i = 3; i< args.length; i++){
            if(i!=3)
                name += " ";
            name += args[i];
        }

        try {
            if(!c.book(Integer.parseInt(args[2]),name))
                System.out.println("Failed to book");
            else
                //we have to strip this string and run a loop to properly display the names
                System.out.println("Booked room for" + name);
        } catch(Exception e){
            System.out.println("Received exception: " + e);
        }
    }

    /**
     * handles the request for a list of guests.
     * @param c
     */
    static private void handleGuests(Hotel c){
        try{
            String[] guests = c.guests();

            for(int i=0; i< guests.length; i++){
                System.out.println(guests[i]);
            }

        } catch (Exception e){
            System.out.println("Received exception: "+e);
        }
    }

    public static void main(String[] args) {
        if(args.length < 2)
            printUsage();

        try{
            Hotel c = (Hotel) Naming.lookup("rmi://" + args[0] + "/HotelService");
            if(args[1].compareTo("list") == 0)
                handleList(c);
            else if(args[1].compareTo("book") == 0)
                handleBook(args,c);
            else if(args[1].compareTo("guests") == 0)
                handleGuests(c);
            else
                printUsage();

        }catch(Exception e){
            System.out.println("Received exception: " + e);
        }
    }
}