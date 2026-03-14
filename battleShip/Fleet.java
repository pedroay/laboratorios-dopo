import java.util.ArrayList;

public class Fleet {

    private String name;
    private ArrayList<Machine> machines;
    private ArrayList<Sailor> sailors;
    private Board board;
    
    /**
     * Moves all machines the defined distance. The world board is circular.
     * @param dLon, advance in longitude
     * param dLat,advance in latitude
     */
    public void advance(int dLon,int dLat){
        int minLon = board.getMinLon();
        int maxLon = board.getMaxLon();
        int maxLat = board.getMaxLat();
        int minLat = board.getMinLat();
        for(Machine m:machines){
            boolean cmla=m.canMoveLat(dLat,minLat,maxLat);
            boolean cmlo = m.canMoveLon(dLon,minLon,maxLon);
            if(cmla && cmlo)m.moveDistance(dLat,dLon);
        }
        }
     
    /**
     * machinesWillBeDestroyed
     * devuelve las maquinas que serán destruidas con un ataque a una posicion
     * @param lon, longitude a donde será el ataque
     * @param lat, latitude a donde será el ataque
     * @return mWillBeDestroyed Arraylist de las maquinas que serán destruidas
     */    
    public ArrayList<Machine> willBeDestroyed(int longitude, int latitude){
        ArrayList<Machine> mWillBeDestroyed = new ArrayList<Machine>();
        for(Machine m : machines){
            boolean willBeDestroyed=m.willBeDestroyed(longitude,latitude);
            if(willBeDestroyed){
                mWillBeDestroyed.add(m);
            }
        }
        return mWillBeDestroyed;
    }
    
    /**
     * Consults the weak machines of a fleet. A ship is weak if it has fewer than five
      *  sailors; an airplane, if it has no main pilot; and an aircraft carrier, if it is
     *   a weak ship or any of its airplanes in the air is weak.
     *   @return The weak machines
     */
    public ArrayList<Machine> weekMachines(){
        ArrayList<Machine> weeksMachines = new ArrayList<Machine>();
        for(Machine m:machines){
            boolean w = m.weekMachine();
            if(w)weeksMachines.add(m);
        }
        return weekMachines();
    }
}

