import java.util.ArrayList;

/**
 * Write a description of class Capsule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Capsule extends Machine implements Nodriza,SelfDestruction
{
    // instance variables - replace the example below with your own
    private Nodriza nodriza;
    private ArrayList<Capsule> subordinados;
    
    @Override
    public boolean weakMachine(){
        return false;
    }
    
    @Override
    public boolean willBeDestroyed(int lon,int lat){
        return false;
    }
    
    public void setNodriza(Nodriza nodriza){
        this.nodriza = nodriza;
    }
    
    public void beANodriza(){
        isANodriza = true;
        subordinados = new ArrayList<Capsule>();
    }
    
    public void getInstruction1(){
        nodriza.shareInstruction1();
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
    
    public void instruction1(){
        
    }
    
    public void instruction2(){
        
    }
    
    public void instruction3(){
        
    }
    
    public void selfDestruction(String reason){
        if(nodriza.getDestructed()){
            selfDestructed = true;
            destructed = true;
            if(isANodriza){
                for(Capsule s:subordinados){
                    s.selfDestruction(reason);
                }
            }
        }
    }
    
    public String getReason(){
        return reason;
    }
    
        public boolean getDestructed(){
            return destructed;
    }
}