package domain;
 
 
/**

* Write a description of class FifaException here.

* 

* @author (your name) 

* @version (a version number or a date)

*/

public class FifaException extends Exception

{

    public static final String NO_PLAYER="no hay jugador";

    public static final String MINUTES_UNKNOWN="no se conocen los minutos";

    public static final String VALUE_UNKNOWN="valor desconocido";
    
    public static final String IMPOSSIBLE="imposible calcular el valor";
    
    public static final String ALREADY_EXISTS = "el participante ya existe";
    
    public FifaException(String message){
         super(message);
    }

}
 