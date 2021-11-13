package ToyCalculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

   public CalculatorImpl() throws RemoteException{
       super();
   }


    public long add(long a, long b) throws RemoteException {
        return a + b;
    }


    public long sub(long a, long b) throws RemoteException {
        return a - b;
    }
}
