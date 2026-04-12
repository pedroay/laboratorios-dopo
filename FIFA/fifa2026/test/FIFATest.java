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
public class FIFATest
{
    /**
     * Default constructor for test class FIFATest
     */
    public FIFATest()
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
    public void shouldListAddedPlayer(){
        Fifa fifa = new Fifa();
        fifa.addPlayer("MESSI", "1420", "D", "15000000", "Inter");
        String result = fifa.toString();
        assertTrue(result.contains("MESSI"));
        assertTrue(result.startsWith("7 elementos"));
    }
    
    @Test
    public void shouldListAddedTeam(){
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
            fifa.addPlayer("MESSI", "abc", "D", "15000000", "Inter");
            fail("Should have thrown an exception");
        } catch (NumberFormatException e){
            String result = fifa.toString();
            assertFalse(result.contains("MESSI"));
            assertEquals(before, fifa.numberParticipants());
        }
    }
    
    
}