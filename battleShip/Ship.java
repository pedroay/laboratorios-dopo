import java.util.Collection;

public class Ship extends Machine implements Nodriza,SelfDestruction
{

    private Collection<Sailor> sailors;
    private int number;
    
    public Collection<Sailor> getSailors(){
        return sailors;
    }
    
    /**
     * retorna si es una weekMachine con la condicion de que tenga menos de 5 marineros
     * @return is, dice si es una weekMachine
     * 
     */
    public boolean weekMachine(){
        boolean is = false;
        int a = sailors.size();
        if(a <5)is = true;
        return is;
        
    }
}
