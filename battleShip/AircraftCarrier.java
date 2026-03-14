import java.util.ArrayList;
import java.util.Collection;

public class AircraftCarrier extends Ship {

    private int number;
    private int capacity;
    private ArrayList<Plane> airPlanes;
    /**
     * revisa si el portaviones es una week machine con la condcion de que si los pilotos principlales
     * de su aviones no existe es una weekmachine o si tiene
     * menos de 5 navegantes
     * @return is, booleano que nos dice si es una week machine o no
     */
    @Override
    public boolean weekMachine(){
        int a = super.getSailors().size();
        if(a <5)return true;
        for(Plane p:airPlanes){
            boolean w = p.weekMachine();
            if(w) return true;
        }
        return false;
    }
}
