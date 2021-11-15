import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is responsible for methods needed for Hotel management
 */
public interface RoomManager extends Remote {

    /**
     * These are the existing rooms in the array indices that represent the room types
     * rooms[0] - type 0
     * rooms[1] - type 1
     * rooms[2] - type 2
     * rooms[3] - type 3
     * rooms[4] - type 4
     */
    int[] rooms = {10, 20, 5, 3, 2};

    /**
     * These are the descriptions to the various room types in the respective indicies
     */

    String[] descriptions = {"Single rooms", "Double rooms", "Twin rooms", "Triple rooms", "Quad rooms"};


    /**
     * These are the prices to the various room types in the respective indicies
     */

    int[] prices = {55000, 75000, 80000, 150000, 230000};



    /**
     * This lists the available rooms in each price range
     * @return the available rooms
     * @throws RemoteException
     */
    int[] list() throws RemoteException;

    /**
     * This books a room of a specified type and registers the guest's name
     * @param type the type of room
     * @param guest the guest's name
     * @return true for success and false for failure
     * @throws RemoteException
     */
    boolean book(int type, String guest) throws RemoteException;

    /**
     * This lists the names of all registered guests
     * @return the list of guests
     * @throws RemoteException
     */
    String[] guests() throws RemoteException;

    /**
     * This prints the revenue breakdown based on the booked rooms and their types
     * @return the list of revenue for the various room types
     * @throws RemoteException
     */
    int[] revenue() throws RemoteException;

}
