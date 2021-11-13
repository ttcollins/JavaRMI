import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class HotelImpl extends UnicastRemoteObject implements Hotel{

    final static int[] capacity = {10, 20, 20};
    ArrayList<String>[] rooms = (ArrayList<String>[])new ArrayList[]{
            new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>()
    } ;

    public HotelImpl() throws RemoteException {
        super();
    }

    /**
     * This returns a list of available rooms per type.
     *
     * @return freerooms
     * @throws RemoteException
     */
    @Override
    public int[] list() throws RemoteException {
        int[] freeRooms = {
                capacity[0] - rooms[0].size(),
                capacity[1] - rooms[1].size(),
                capacity[2] - rooms[2].size()
        };
        return freeRooms;
    }

    /**
     * Book a room of a certain type for a guest
     *
     * @param type
     * @param guest
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean book(int type, String guest) throws RemoteException {
        if(type < 1 || type > 3)
            return false;

        if(rooms[type-1].size() < capacity[type-1] ){
            if(rooms[type-1].add(guest)){
                //add room type booked
                System.out.println("Booked room for " + guest);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a list of guests
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] guests() throws RemoteException {
        int i, j, ctr=0;
        String[] guestList = new String[ rooms[0].size() + rooms[1].size() + rooms[2].size() ];

        for(i=0; i<3; i++){
            for(j=0; j<rooms[i].size(); j++){
                guestList[ctr++] = rooms[i].get(j);
            }
        }

        return guestList;
    }
}
