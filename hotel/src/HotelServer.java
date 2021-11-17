import java.rmi.Naming;

public class HotelServer {

    public HotelServer(){
        try{
            RoomManager r = new RoomManagerImpl();
            Naming.rebind("rmi://localhost/HotelService", r);
        }catch (Exception e){
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new HotelServer();
    }

}
