import java.rmi.Naming;

public class CalculatorServer {

    public CalculatorServer () {
        try {
// The calculator creates an CalculatorImpl object
            Calculator c =
                    new CalculatorImpl();
// It binds (registers) to the rmiregistry
            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            System.out.println("Trouble: _" + e);
        }
    }

    public static void main (String args [] ) {
        new CalculatorServer ();
    }
}
