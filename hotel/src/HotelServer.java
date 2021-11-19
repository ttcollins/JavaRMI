import java.rmi.Naming;

public class HotelServer {

    public HotelServer(){
        try{
            //creating a RoomManagerImpl object
            RoomManager r = new RoomManagerImpl();

            //registering to the rmiregistry
            Naming.rebind("rmi://localhost/HotelService", r);
        }catch (Exception e){
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        new HotelServer();
    }

}
