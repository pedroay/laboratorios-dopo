import java.util.ArrayList;

public class Board {

    private ArrayList<Fleet> fleets;
    private int minLat;
    private int maxLat;
    private int minLon;
    private int maxLon;
    
    public int getMinLat(){
        return minLat;
    }
    
    public int getMaxLat(){
        return maxLat;
    }
    
    public int getMaxLon(){
        return maxLon;
    }
    
    public int getMinLon(){
        return minLon;
    }

}
