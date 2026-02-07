import java.util.List;
import java.util.ArrayList;
import java.awt.*;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    private List<int []> position;  
    private boolean visible;
    private List<Rectangle> body;
    private String color;
    

    /**
     * Constructor for objects of class Snake
     */
     public Snake(int row, int column) {
        position = new ArrayList<>();
        body =new ArrayList<>();
        position.add(new int[] { row, column }); // cabeza
        Rectangle head = new Rectangle();
        head.setXP(row);
        head.setYP(column);
        body.add(head);
        visible = false;
        color = "green"; // o el que quieras
    }
}

    
