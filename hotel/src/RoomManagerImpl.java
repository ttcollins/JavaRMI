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

    /**
     *
     * @return an array of all guests who booked rooms
     * @throws RemoteException
     */
    @Override
    public String[] guests() throws RemoteException {
        int i, j, counter=0;
         // Making an array of size equalling to the number of guests that booked
        String[] guestList = new String[ guests[0].size() + guests[1].size() +
                guests[2].size() + guests[3].size() + guests[4].size() + 5];

         //Outer loop for the five arraylists.
         //Inner loop to get the details of each arraylist.
        for(i=0; i<5; i++){
            guestList[counter++] = "===Room Type " + i + " ===";
            for(j=0; j<guests[i].size(); j++){
                guestList[counter++] = guests[i].get(j);
            }
        }
        return guestList;
    }

    /**
     * @return roomRevenues , array holding the different revenues generated for each room
     * @throws RemoteException
     */
    @Override
    public int[] revenue() throws RemoteException {
        //initializing an array to hold the revenue from various types of rooms
        int[] roomRevenues = new int[rooms.length];

        //loop to calculate the revenues and store them in the array above
        for(int i=0; i < rooms.length ; i++) {
            int numberOfGuestsInRoomType = guests[i].size();
            int roomRevenue = numberOfGuestsInRoomType * prices[i];
            roomRevenues[i] = roomRevenue;
        }
        return roomRevenues;
    }

}
