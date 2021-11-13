import ToyCalculator.Calculator;
import ToyCalculator.CalculatorImpl;

import java.rmi.Naming;

public class CalculatorServer {

    public CalculatorServer(){
        try{
            Calculator c = new CalculatorImpl();

            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e){
            System.out.println("Trouble:_" + e);
        }
    }

    public static void main(String args[]){
        new CalculatorServer();
    }

}
