import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hotel extends Remote {

    boolean book(int type, String guest) throws RemoteException;

    String[] guest() throws RemoteException;

    int[] revenue() throws RemoteException;

}
