package test;

import domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FIFATest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FIFATESTT
{
    /**
     * Default constructor for test class FIFATest
     */
    public FIFATESTT()
    {
        
    }
    @Test
    public void shouldListAllParticipants(){
        Fifa fifa = new Fifa();
        String result = fifa.toString();
        assertNotNull(result);
        assertTrue(result.contains("L.DIAZ"));
        assertTrue(result.contains("COLOMBIA"));
        assertTrue(result.startsWith("6 elementos"));
    }
    
    @Test
    public void shouldListAddedPlayer() throws FifaException{
        Fifa fifa = new Fifa();
        fifa.addPlayer("MESSI", "1420", "D", "15000000", "Inter");
        String result = fifa.toString();
        assertTrue(result.contains("MESSI"));
        assertTrue(result.startsWith("7 elementos"));
    }
    
    @Test
    public void shouldListAddedTeam() throws FifaException{
        Fifa fifa = new Fifa();
        fifa.addTeam("ARGENTINA", "1620", "J", "Scaloni", "Azul-blanco", "L.DIAZ");
        String result = fifa.toString();
        assertTrue(result.contains("ARGENTINA"));
        assertTrue(result.startsWith("7 elementos"));
    }
    
    @Test
    public void shouldNotListPlayerWithBadData(){
        Fifa fifa = new Fifa();
        int before = fifa.numberParticipants();
        try {
            // Caso (ii): minutos no numéricos -> debe lanzar FifaException
            fifa.addPlayer("MESSI", "abc", "D", "15000000", "Inter");
            fail("Should have thrown an exception");
        } catch (FifaException e){
            // El dominio convierte NumberFormatException en FifaException
            String result = fifa.toString();
            assertFalse(result.contains("MESSI"));
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
    @Test
    public void shouldNotAddPlayerWithDuplicateName(){
        // Caso (i): nombre ya existe
        Fifa fifa = new Fifa();
        int before = fifa.numberParticipants();
        try {
            fifa.addPlayer("L.DIAZ", "500", "A", "1000000", "Inter");
            fail("Should have thrown FifaException.ALREADY_EXISTS");
        } catch (FifaException e){
            assertEquals(FifaException.ALREADY_EXISTS, e.getMessage());
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
    @Test
    public void shouldNotAddPlayerWithNegativeMinutes(){
        // Caso (iii): minutos negativos
        Fifa fifa = new Fifa();
        int before = fifa.numberParticipants();
        try {
            fifa.addPlayer("RONALDO", "-100", "A", "5000000", "Al-Nassr");
            fail("Should have thrown FifaException.WRONG_VALUE");
        } catch (FifaException e){
            assertTrue(e.getMessage().contains("rango") || e.getMessage().contains("cero"));
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
    @Test
    public void shouldNotAddPlayerWithEmptyName(){
        // Caso (iv): campo vacío
        Fifa fifa = new Fifa();
        int before = fifa.numberParticipants();
        try {
            fifa.addPlayer("", "500", "A", "1000000", "Inter");
            fail("Should have thrown FifaException.EMPTY_FIELD");
        } catch (FifaException e){
            assertEquals(FifaException.EMPTY_FIELD, e.getMessage());
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
    @Test
    public void shouldNotAddTeamWithNonNumericMinutes(){
        // Caso (ii): minutos no numéricos en equipo
        Fifa fifa = new Fifa();
        int before = fifa.numberParticipants();
        try {
            fifa.addTeam("BRASIL", "abc", "J", "Dorival", "Amarillo-Verde", "L.DIAZ");
            fail("Should have thrown FifaException");
        } catch (FifaException e){
            assertFalse(fifa.toString().contains("BRASIL"));
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
}