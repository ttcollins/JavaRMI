import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hotel extends Remote {

    //getting available rooms.
    public int[] list() throws RemoteException;

    //booking a room for a guest
    public boolean book(int type, String guest) throws RemoteException;

    //getting the guests occupying a particular type of room
    public String[] guests() throws RemoteException;

}