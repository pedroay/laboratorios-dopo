public class Sailor implements SelfDestruction{

    private String name;
    private int rank;
    private boolean selfDestruction;
    private String reason;
    
    public void selfDestruction(String reason){
        selfDestruction = true;
        this.reason = reason;
    }
    
    public String getReason(){
        return reason;
    }
}
