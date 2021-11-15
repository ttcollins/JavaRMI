import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {

    /**
     * This is where guests who book will be stored
     */
    ArrayList<String>[] guests = (ArrayList<String>[]) new ArrayList[]{
            new ArrayList<String>(),
            new ArrayList<String>(),
            new ArrayList<String>(),
            new ArrayList<String>(),
            new ArrayList<String>()
    };




    public RoomManagerImpl() throws RemoteException {
        super();
        book(0,"Ali") ; //pre-booked guests for testing code
        book(1,"Doki") ;
        book(1,"Golder") ;
        book(3,"Sylivia") ;
        book(2,"Eric") ;
        book(3,"Mboizi") ;
        book(4,"Collins") ;
        book(0,"Tamale") ;
        book(0,"Mahmood") ;

    }




    @Override
    public int[] list() throws RemoteException {
        return new int[0];
    }




    /**
     *
     * @param type the type of room
     * @param guest the guest's name
     * @return true, if a room has been booked OR false, if the type is
     *          invalid/rooms of type are fully booked
     * @throws RemoteException
     */
    @Override
    public boolean book(int type, String guest) throws RemoteException {
        //Check if the type passed is a valid one
        if(type<0 || type>5) {
            System.out.println("This type does not exist");
            return false;
        }


        //Saving the guest if there are available rooms
        if(guests[type].size() < rooms[type]) {
            if(guests[type].add(guest)) {
                System.out.println("Booked room for: " + guest);
                System.out.println("Room Type: " + type);
                System.out.println("Room Description: " + descriptions[type]);
                System.out.println("Price: " + prices[type] );
                return true;
            }
        }
        return false;
    }





    @Override
    public String[] guests() throws RemoteException {
        return new String[0];
    }






    /**
     *This method calculates revenue generated by booked guests for the
     * different types of rooms.
     *
     * @return roomRevenues , array holding the different revenues generated for each room
     * @throws RemoteException
     */
    @Override
    public int[] revenue() throws RemoteException {

        int[] roomRevenues = new int[rooms.length];

        //loop to calculate the revenues and store them in an array
        for(int i=0; i < rooms.length ; i++) {
         int numberOfGuestsInRoomType = guests[i].size();
         int roomRevenue = numberOfGuestsInRoomType * prices[i];
         roomRevenues[i] = roomRevenue;
        }

        return roomRevenues;
    }

}
