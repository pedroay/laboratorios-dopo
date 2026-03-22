package test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import domain.*;

/**
 * The test class ForestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ForestTest
{   
    @Test
    public void debeTenerElColorRosado(){
        Forest f1 = new Forest();
        Tree   t1 = new Tree(f1,10,10);
        Tree   t2 = new Tree(f1,15,10);
        assertEquals(Color.PINK,t1.getColor());
        assertEquals(Color.PINK,t2.getColor());
    }
    @Test
    public void debeTenerElColorVerde(){
        Forest f1 = new Forest();
        Tree   t1 = new Tree(f1,10,10);
        Tree   t2 = new Tree(f1,15,10);
        f1.ticTac();
        assertEquals(Color.GREEN,t1.getColor());
        assertEquals(Color.GREEN,t2.getColor());
    }
    @Test
    public void debeTenerElColorNaranja(){
        Forest f1 = new Forest();
        Tree   t1 = new Tree(f1,10,10);
        Tree   t2 = new Tree(f1,15,10);
        f1.ticTac();
        f1.ticTac();
        assertEquals(Color.ORANGE,t1.getColor());
        assertEquals(Color.ORANGE,t2.getColor());
    }

    @Test
    public void despuesDe10Años(){
        Forest f1 = new Forest();
        Squirrel sq = new Squirrel(f1,10,10); 
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        assertEquals(sq.getYear(),10);
    }
    
    @Test
    public void debeCambiarColorConLaEdad() {
        Forest f1 = new Forest();
        Squirrel sq = new Squirrel(f1, 10, 10);
        assertEquals(new Color(139, 69, 19), sq.getColor());
        for(int i = 0; i < 41   ; i++) {
            f1.ticTac();
        }
        assertEquals(new Color(242, 234, 2), sq.getColor());
    }
    
    @Test
    public void debeReproducirseConCeldaIntermedia() {
        Forest f1 = new Forest();
        Squirrel s1 = new Squirrel(f1, 10, 10);
        Squirrel s2 = new Squirrel(f1, 10, 12);
        f1.ticTac();
        Thing bebe = f1.getThing(10, 11);
        assertTrue(bebe instanceof Squirrel, "Debería haber nacido una ardilla en la mitad");
    }
    


    @Test
    public void testShadowMovesNorth() {
        Forest f = new Forest();
        Shadow s = new Shadow(f, 10);
        s.ticTac();
        assertEquals(9, s.getRow());
    }

    @Test
    public void testShadowWrapsAround() {
        Forest f = new Forest();
        Shadow s = new Shadow(f, 0);
        s.ticTac();
        assertEquals(24, s.getRow());
    }


    @Test
    public void testShadowIsRegisteredInForest() {
        Forest f = new Forest();
        int initialSize = f.getShadows().size();
        Shadow s = new Shadow(f, 15);
        assertEquals(initialSize + 1, f.getShadows().size());
    }

    @Test
    public void testShadowDoesNotAffectSquirrel() {
        Forest f = new Forest();
        Shadow s = new Shadow(f, 10);
        Squirrel sq = new Squirrel(f, 10, 5);
        for (int i = 0; i < 4; i++) {
            s.ticTac();
            sq.ticTac();
        }
        assertNotNull(f.getThing(sq.getRow(), sq.getColumn()));
    }



    @Test
    public void testLakeOccupies2x2() {
        Forest f = new Forest();
        Lake l = new Lake(f, 5, 5);
        for (int r = 5; r < 7; r++) {
            for (int c = 5; c < 7; c++) {
                assertNotNull(f.getThing(r, c));
            }
        }
    }

    @Test
    public void testLakeDoesNotDie() {
        Forest f = new Forest();
        Lake l = new Lake(f, 5, 5);
        for (int i = 0; i < 100; i++) l.ticTac();
        for (int r = 5; r < 7; r++) {
            for (int c = 5; c < 7; c++) {
                assertNotNull(f.getThing(r, c));
            }
        }
    }

    @Test
    public void testLakeColorChangesEachTicTac() {
        Forest f = new Forest();
        Lake l = new Lake(f, 5, 5);
        Color initial = l.getColor();
        l.ticTac();
        assertNotEquals(initial, l.getColor());
    }


    @Test
    public void testLakeBlocksSquirrel() {
        Forest f = new Forest();
        Lake l = new Lake(f, 10, 10);
        Squirrel s = new Squirrel(f, 10, 9);
        for (int i = 0; i < 20; i++) {
            s.ticTac();
            l.ticTac();
        }
        for (int r = 10; r < 12; r++) {
            for (int c = 10; c < 12; c++) {
                assertNotNull(f.getThing(r, c));
            }
        }
    }
}

