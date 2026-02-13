import java.util.List;
import java.util.ArrayList;
import java.awt.*;

/**
 * The Snake class represents a snake in a classic Snake game.
 * It manages both the logical position of the snake (row and column)
 * and its graphical representation using Rectangle objects.
 * 
 * The snake can move, grow, become visible or invisible, and report
 * information about its head, tail, and size.
 * 
 */
public class Snake
{
    /**
     * List that stores the logical positions of the snake.
     * Each position is represented as an int array:
     * [row, column].
     */
    private List<int []> position;  

    /**
     * Indicates whether the snake is currently visible on the canvas.
     */
    private boolean visible;

    /**
     * List of Rectangle objects that graphically represent the snake's body.
     * Each rectangle corresponds to one position in the position list.
     */
    private List<Rectangle> body;

    /**
     * Color of the snake.
     */
    private String color;

    /**
     * Indicates whether the snake is in a valid state.
     * If false, the snake cannot move or grow.
     */
    private boolean isOk;

    /**
     * Stores the current length of the snake.
     */
    private int lenghtSnake;

    /**
     * Constructs a Snake object with an initial position and color.
     * The snake starts with a single segment (the head).
     *
     * @param row initial row position of the snake's head
     * @param column initial column position of the snake's head
     * @param color color of the snake
     */
    public Snake(int row, int column, String color) {
        position = new ArrayList<>();
        body = new ArrayList<>();
        position.add(new int[] { row, column }); 
        visible = false;
        isOk = true;
        this.color = color;
        
        Rectangle head = new Rectangle();
        head.changeSize(20,20);
        head.setYP(row * 20);
        head.setXP(column * 20);
        head.changeColor(color);
        body.add(head);
        makeVisible();
        lenghtSnake = body.size();
    }
    
    /**
     * Returns the current size (length) of the snake.
     *
     * @return the number of segments in the snake
     */
    public int snakeSize(){
        return lenghtSnake;
    }
    
    /**
     * Makes the snake visible on the canvas.
     * All rectangles representing the snake body are shown.
     */
    public void makeVisible(){
        visible = true;
        for (int i = body.size() - 1; i >= 0; i--){
            Rectangle currRect = body.get(i);
            currRect.makeVisible();
        }
    }
    
    /**
     * Makes the snake invisible on the canvas.
     * All rectangles representing the snake body are hidden.
     */
    public void makeInvisible(){
        visible = false;
        for (int i = body.size() - 1; i >= 0; i--){
            Rectangle currRect = body.get(i);
            currRect.makeInvisible();
        }
    }
    
    /**
     * Returns the logical position of the snake's head.
     *
     * @return an int array containing [row, column] of the head
     */
    public int[] head (){
        return position.get(0);
    }
    
    /**
     * Returns the logical position of the snake's tail.
     *
     * @return an int array containing [row, column] of the tail
     */
    public int[] tail(){
        return position.get(position.size() - 1);
    }
    
    /**
     * Moves the snake one position in the given direction.
     * The snake keeps the same length; the tail is removed.
     *
     * @param direction the direction of movement:
     *        'U' (up), 'D' (down), 'L' (left), 'R' (right)
     */
    public void move(char direction){
        if (!isOk) return;
        
        // Move the body segments to follow the previous segment
        for (int i = body.size() - 1; i > 0; i--){
            int[] prevPos = position.get(i - 1);
            int[] currPos = position.get(i);
    
            Rectangle currRect = body.get(i);
    
            if (prevPos[0] < currPos[0]) currRect.moveUp();
            if (prevPos[0] > currPos[0]) currRect.moveDown();
            if (prevPos[1] < currPos[1]) currRect.moveLeft();
            if (prevPos[1] > currPos[1]) currRect.moveRight();
        }

        // Move the head
        Rectangle headRect = body.get(0);
        int[] headPos = position.get(0);
        int newRow = headPos[0];
        int newCol = headPos[1];
    
        switch (direction) {
            case 'U':
                headRect.moveUp();
                newRow--;
                break;
            case 'D':
                headRect.moveDown();
                newRow++;
                break;
            case 'R':
                headRect.moveRight();
                newCol++;
                break;
            case 'L':
                headRect.moveLeft();
                newCol--;
                break;
            default:
                isOk = false;
                return;
        }

        // Update logical positions
        position.add(0, new int[] {newRow, newCol});
        position.remove(position.size() - 1);
        
        if (visible){
            makeVisible();
        }
    }
    
    /**
     * Grows the snake one position in the given direction.
     * The snake increases its length by one segment.
     *
     * @param direction the direction of growth:
     *        'U' (up), 'D' (down), 'L' (left), 'R' (right)
     */
    public void grow(char direction){
        if (!isOk) return;
        
        // Move the body segments
        for (int i = body.size() - 1; i > 0; i--){
            int[] prevPos = position.get(i - 1);
            int[] currPos = position.get(i);
    
            Rectangle currRect = body.get(i);
    
            if (prevPos[0] < currPos[0]) currRect.moveUp();
            if (prevPos[0] > currPos[0]) currRect.moveDown();
            if (prevPos[1] < currPos[1]) currRect.moveLeft();
            if (prevPos[1] > currPos[1]) currRect.moveRight();
        }

        // Move the head
        Rectangle headRect = body.get(0);
        int[] headPos = position.get(0);
        int newRow = headPos[0];
        int newCol = headPos[1];
    
        switch (direction) {
            case 'U':
                headRect.moveUp();
                newRow--;
                break;
            case 'D':
                headRect.moveDown();
                newRow++;
                break;
            case 'R':
                headRect.moveRight();
                newCol++;
                break;
            case 'L':
                headRect.moveLeft();
                newCol--;
                break;
            default:
                isOk = false;
                return;
        }

        // Update logical positions and add a new body segment
        position.add(0, new int[] {newRow, newCol});
        int[] tail = position.get(position.size() - 1);
        Rectangle newBody = new Rectangle();
        newBody.changeSize(20,20);
        newBody.setXP(tail[1] * 20);
        newBody.setYP(tail[0] * 20);
        newBody.changeColor(color);
        body.add(newBody);
        
        if (visible){
            makeVisible();
        }
    }
}
