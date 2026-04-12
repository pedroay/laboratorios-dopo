package domain;  
 
import java.util.ArrayList;

public class Team extends Participant{
   
    private String manager;
    private String uniform;
    
    private ArrayList<Player> players;
    
    /**
     * Constructs a new Team
     * @param name
     * @param type
     */
    public Team(String name, int minutes, char position, String manager, String uniform){
        super(name, minutes, position);
        this.manager=manager;
        this.uniform=uniform;
        players= new ArrayList<Player>();
    }
    
    
     /**
     * Add a new Player
     * @param c
     */   
    public void addPlayer(Player c){
        players.add(c);
    }
       
    /**
    * Return de value of the team based in the sum of the market value of his players
    * @return value: return all the vale of a team
    */
    public int marketValue() throws FifaException{ 
        double value = 0;
        double totalMinutes = 0;
        for(Player p:players){
            totalMinutes += p.minutes();
        }
        if(totalMinutes == 0)throw new FifaException(FifaException.IMPOSSIBLE);
        for(Player p:players){
            double v = p.marketValue();
            double m = p.minutes();
            value += m*(v/totalMinutes);     
        }
        return (int)value;
    }
    
    /**
     * Returns the expectet Market Value 
     * @return
     * @throws FifaException, if any marker value or minutes is unknown
     */
    //If more than half of the players have no recorded minutes, the total number of players is used to average. 
    //Otherwise, the average minutes played by known players is used for those whose minutes are unknown.
    public int expectedMarketValue() throws FifaException {
        double value = 0;
        double totalMinutes = 0;
        int numNoMinutes = 0;
        for (Player p : players) {
            try {
                totalMinutes += p.minutes();//suma puntos
            } catch (FifaException e) {
                numNoMinutes++;//suma los que no tienen minutos
            }
        }
        int corrects = players.size() - numNoMinutes;
        double imputedMinutes;
        // Calcular minutos que se asignarán
        if (numNoMinutes > players.size() / 2) {
            imputedMinutes = totalMinutes / (double) players.size();
        } else {
            imputedMinutes = (corrects > 0) ? totalMinutes / (double) corrects : 0; //si los correcto=0, entonces imputed minutes tambien
        }
        //ajusta la cantidad de minutos totales(minutos contados+minutos asignados)
        double adjustedTotal = totalMinutes + (numNoMinutes * imputedMinutes);
        if (adjustedTotal == 0) throw new FifaException(FifaException.IMPOSSIBLE);
        // Calcula el valor del equipo
        for (Player p : players) {
            double v = p.marketValue();
            double m;
            try {
                m = p.minutes();
            } catch (FifaException e) {
                m = imputedMinutes; 
            }
            value += m * (v / adjustedTotal);
        }
        return (int) value;
    }
 
    
    /**
     * Returns the Marked Value using default values 
     * @return
     * @throws FifaException, if the resistance cannot be calculate
     */
    //If a player's market value or minutes played are unknown, default values ​​are used.
    public int defaultMarkedValue(int defaultMarketValue, int defaultMinutes) throws FifaException {
        double value = 0;
        double totalMinutes = 0;
        for (Player p : players) {
            try {
                totalMinutes += p.minutes();
            } catch (FifaException e) {
                totalMinutes += defaultMinutes;
            }
        }
        if (totalMinutes == 0) throw new FifaException(FifaException.IMPOSSIBLE);
        for (Player p : players) {
            double v;
            double m;
            try {
                v = p.marketValue();
            } catch (FifaException e) {
                v = defaultMarketValue;
            }
            try {
                m = p.minutes();
            } catch (FifaException e) {
                m = defaultMinutes;
            }
            value += m * (v / totalMinutes);
        }    
        return (int) value;
    }
    
    /**
     * Returns the best possible Market Value of the team.
     * @return weighted market value using the best known values for unknown data
     * @throws FifaException IMPOSSIBLE if total minutes is zero
     */
    // If a player's market value is unknown, the highest known market value is used.
    // If a player's minutes are unknown, the highest known minutes are used.
    public int bestMarkedValue() throws FifaException {
        double value = 0;
        double totalMinutes = 0;
        int bestMinutes = 0;
        int bestMarketValue = 0;
        for (Player p : players) {
            try {
                if (p.minutes() > bestMinutes) bestMinutes = p.minutes();
            } catch (FifaException e) { }
            try {
                if (p.marketValue() > bestMarketValue) bestMarketValue = p.marketValue();
            } catch (FifaException e) { }
        }
        for (Player p : players) {
            try {
                totalMinutes += p.minutes();
            } catch (FifaException e) {
                totalMinutes += bestMinutes;
            }
        }
        if (totalMinutes == 0) throw new FifaException(FifaException.IMPOSSIBLE);
        for (Player p : players) {
            double v;
            double m;
            try {
                v = p.marketValue();
            } catch (FifaException e) {
                v = bestMarketValue;
            }
            try {
                m = p.minutes();
            } catch (FifaException e) {
                m = bestMinutes;
            }
            value += m * (v / totalMinutes);
        }
    
        return (int) value;
    }
    
    @Override
    public String data() throws FifaException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+".\t Grupo: "+position+".\t Valor Promedio:" +marketValue());
        for(Player p: players) {
            answer.append("\n\t"+p.data());
        }
        return answer.toString();
    } 
    
    
    }
