import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hotel extends Remote {

    public int[] list() throws RemoteException;

    public boolean book(int type, String guest) throws RemoteException;

    public String[] guests() throws RemoteException;

}
