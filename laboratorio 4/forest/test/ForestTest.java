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
    public void debeTenerElColor(){
        Forest f1 = new Forest();
        Tree   t1 = new Tree(f1,10,10);
        Tree   t2 = new Tree(f1,15,10);
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        f1.ticTac();
        assertEquals(Color.ORANGE,t1.getColor());
        assertEquals(Color.ORANGE,t2.getColor());
    }

}