package ToyCalculator;

import java.rmi.Naming;

public class CalculatorClient {

    public static void main(String[] args) {
        try{
            Calculator c = (Calculator) Naming.lookup("rmi://localhost:1099/CalculatorService");

            System.out.println( c.add(4,5));
            System.out.println(c.sub(4,3));

        } catch(Exception e){
            System.out.println("Received Exception:");
            System.out.println(e);
        }
    }

}
