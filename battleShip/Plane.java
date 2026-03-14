public class Plane extends Machine {

    private String plate;
    private boolean inAir;
    private Sailor pilot;
    private Sailor copilot;
    
    /**
     * willBeDestroyed
     * revisa si el avion sera destruido coincidiendo con la posicion y si se encuentra en aire
     * @param lon, longitude a donde será el ataque
     * @param lat, latitude a donde será el ataque
     * @return BeDestroyed booleano que cofirma si será destruido o no
     */
    @Override
    public boolean willBeDestroyed(int lon, int lat){
        boolean BeDestroyed=false;
        boolean compPos=super.getLocation().samePosition(lon,lat);
        if (compPos && !inAir){
            BeDestroyed=true;
        }
        return BeDestroyed;
    }
    
    /**
     * revisa si el avion es una week machine con la condcion de que si el piloto principlal no existe es una weekmachine
     * @return is, booleano que nos dice si es una week machine o no
     */
    @Override
    public boolean weekMachine(){
        boolean is = false;
        if(pilot == null)is = true;
        return is;
    }
}
