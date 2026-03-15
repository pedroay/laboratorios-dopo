import java.util.ArrayList;

/**
 * Write a description of class Capsule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Capsule extends Machine implements Nodriza ,SelfDestruction
{
    // instance variables - replace the example below with your own
    private Nodriza nodriza;
    private ArrayList<Capsule> subordinados;
    
    public boolean weakMachine(){
        return false;
    }
    
    @Override
    public boolean willBeDestroyed(int lon,int lat){
        return false;
    }
    
    public void setNodriza(Ship ship){
        nodriza = ship;
    }
    
    public void setNodriza(Capsule capsule){
        nodriza = capsule   ;
    }
    
    public void beANodriza(){
        isANodriza = true;
    }
}