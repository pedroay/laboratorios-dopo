public class Position {

    private int longitude;

    private int latitude;
    
    /**
     * returna un booleano si se puede mover en latitude sumando su posiciona actual con la distancia
     * @param distancia, distancia que se va a gregar
     * @param minLat, latitud menor en el board
     * @param maxLat, latitud mayor en el board
     * @return can, boleano que nos dice si se puede mover o no
     */
    public boolean canMoveLat(int distancia,int minLat,int maxLat){
        boolean can = false ;
        if(distancia + latitude >= minLat && distancia + latitude <= maxLat)can = true;
        return can;
    }
    
    /**
     * returna un booleano si se puede mover en longitude sumando su posiciona actual con la distancia
     * @param distancia, distancia que se va a gregar
     * @param minLon, longitud menor en el board
     * @param maxLon, longitud mayor en el board
     * @return can, boleano que nos dice si se puede mover o no
     */
    public boolean canMoveLon(int distancia,int minLon,int maxLon){
        boolean can = false ;
        if(distancia + latitude >= minLon && distancia + latitude <= maxLon)can = true;
        return can;
    }
    
    public int getLat(){
        return latitude;
    }
    
    public int getLon(){
        return longitude;
    }
    
    public void setLat(int i){
        latitude = i;
    }
    
    public void setLon(int i){
        longitude = i;
    }
    
    /**
     * compara la posicion mandada, con la posicion actual
     * @param lon, entero a comparar con longitude
     * @param lat, entero a comparar con latitude
     * @return coincide, booleano que confirma si lon y lat coincide
     */
    public boolean samePosition(int lon,int  lat){
        boolean coincide=false;
        if(latitude==lat && longitude==lon){
            coincide=true;
        }
        return coincide;
    }
}
