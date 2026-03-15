import java.util.ArrayList;
public abstract class Machine {

    private Position location;
    protected boolean selfDestructed;
    protected String reason;
    protected boolean isANodriza;
    protected ArrayList<Capsule> subordinados;
    protected boolean destructed;
    
    
    /**
     * retorna un booleano preguntando a la location si de puede mover o no
     */
    public boolean canMoveLat(int distancia,int minLat, int maxLat){
        return location.canMoveLat(distancia,minLat,maxLat);
    }
    
    /**
     * retorna un booleano preguntando a la location si de puede mover o no
     */
    public boolean canMoveLon(int distancia,int minLon,int maxLon){
        return location.canMoveLon(distancia, minLon , maxLon); 
    }
    
    /**
     * mueve una distancia en logitud como en latirud
     * @param dLat, la distancia que queremos que recorra en latitude
     * @param dLon, la distancia que queremos que recorra en logitude
     */
    public void moveDistance(int dLat,int dLon){
        int posLat = location.getLat();
        int posLon = location.getLon();
        location.setLat(dLat + posLat);
        location.setLon(dLat + posLat);
    }
    
    /**
     * willBeDestroyed
     * revisa si el avion sera destruido coincidiendo con la posicion y si se encuentra en aire
     * @param lon, longitude a donde será el ataque
     * @param lat, latitude a donde será el ataque
     * @return BeDestroyed booleano que cofirma si será destruido o no
     */
    public boolean willBeDestroyed(int lon, int lat){
        boolean BeDestroyed=false;
        boolean compPos=location.samePosition(lon,lat);
        if (compPos){
            BeDestroyed=true;
        }
        return BeDestroyed;
    }
    
    public Position getLocation(){
       return location; 
    }
    
    public abstract boolean weakMachine();
    
    
    public boolean getDestructed(){
        return destructed;
    }
}
