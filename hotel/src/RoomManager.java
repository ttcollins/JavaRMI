import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is responsible for methods needed for Hotel management
 */
public interface RoomManager extends Remote {

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
