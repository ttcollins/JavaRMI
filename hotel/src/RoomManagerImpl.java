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
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean book(int type, String guest) throws RemoteException {
        /**
         * Check if the type passed is a valid one
         */
        if(type<0 || type>5) {
            System.out.println("This type does not exist");
            return false;
        }

        /**
         * Saving the guest if there are available rooms
         */
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

    @Override
    public int[] revenue() throws RemoteException {
        return new int[0];
    }
}
