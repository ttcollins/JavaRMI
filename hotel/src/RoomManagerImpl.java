import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {

    public RoomManagerImpl() throws RemoteException {
        super();
    }

    @Override
    public int[] list() throws RemoteException {
        return new int[0];
    }

    @Override
    public boolean book(int type, String guest) throws RemoteException {
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
