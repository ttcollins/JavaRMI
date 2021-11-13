import java.rmi.*;

//must extend java.rmi.Remote
public interface Calculator extends Remote {

    //all methods must throw java.rmi.RemoteException

    public long add(long a, long b) throws RemoteException;

    public long sub(long a, long b) throws RemoteException;

}
