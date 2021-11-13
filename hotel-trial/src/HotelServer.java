import java.rmi.Naming;

public class HotelServer{

    public HotelServer(){
        try{
            Hotel c = new HotelImpl();
            Naming.rebind("rmi://localhost/HotelService", c);
        }catch (Exception e){
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new HotelServer();
    }

}