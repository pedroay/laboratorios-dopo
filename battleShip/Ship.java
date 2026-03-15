import java.util.Collection;
import java.util.ArrayList;

public class Ship extends Machine implements Nodriza,SelfDestruction
{

    private Collection<Sailor> sailors;
    private int number;
    
    public Collection<Sailor> getSailors(){
        return sailors;
    }
    
    @Override
    /**
     * retorna si es una weekMachine con la condicion de que tenga menos de 5 marineros
     * @return is, dice si es una weekMachine
     * 
     */
    public boolean weakMachine(){
        boolean is = false;
        int a = sailors.size();
        if(a <5)is = true;
        return is;
        
    }
    
    public void beANodriza(){
        isANodriza = true;
        subordinados = new ArrayList<Capsule>();
    }
    
    public void selfDestruction(String reason){
        
    }
    
    public void shareInstruction1(){
        if(isANodriza){
            for(Capsule s:subordinados)s.instruction1();
        }
    }
    
    public void shareInstruction2(){
        if(isANodriza){
                for(Capsule s:subordinados)s.instruction2();
            }
    }
    
    public void shareInstruction3(){
        if(isANodriza){
                for(Capsule s:subordinados)s.instruction3();
            }
    }
    
    public void selfDestruction(String reason){
        selfDestructed = true;
    }
    
}
