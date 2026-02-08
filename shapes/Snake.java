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
    private boolean isOk;
    private int lenghtSnake;

    /**
     * Constructor for objects of class Snake
     */
     public Snake(int row, int column, String color) {
        position = new ArrayList<>();
        body =new ArrayList<>();
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
        lenghtSnake=body.size();
    }
    
    public int snakeSize(){
        return lenghtSnake;
    }
    
    public void makeVisible(){
        visible=true;
        for (int i =body.size() -1;i>= 0;i--){
            Rectangle currRect = body.get(i);
            currRect.makeVisible();
        }
    }
    
    public void makeInvisible(){
        visible=false;
        for (int i =body.size() -1;i>= 0;i--){
            Rectangle currRect = body.get(i);
            currRect.makeInvisible();
            }
    }
    
    /**
     * Returns the position of the snake's head
     */
    public int[] head (){
        return position.get(0);
    }
    
    /**
     * Returns the position of the snake's tail
     */
    public int[] tail(){
        return position.get(position.size()-1);
    }
    
    /**
     *  Moves the snake one position in the given direction.
     */
    public void move(char direction){
        if (!isOk) return;
        
        for (int i =body.size() -1;i> 0;i--){
            int[] prevPos = position.get(i - 1);
            int[] currPos = position.get(i);
    
            Rectangle currRect = body.get(i);
    
            if (prevPos[0] < currPos[0]) currRect.moveUp();
            if (prevPos[0] > currPos[0]) currRect.moveDown();
            if (prevPos[1] < currPos[1]) currRect.moveLeft();
            if (prevPos[1] > currPos[1]) currRect.moveRight();
            }
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

        position.add(0, new int[] {newRow, newCol});
        position.remove(position.size() - 1);
        
        if(visible){
            makeVisible();
        }
    }
    
    public void grow(char direction){
        if (!isOk) return;
        
        for (int i =body.size() -1;i> 0;i--){
            int[] prevPos = position.get(i - 1);
            int[] currPos = position.get(i);
    
            Rectangle currRect = body.get(i);
    
            if (prevPos[0] < currPos[0]) currRect.moveUp();
            if (prevPos[0] > currPos[0]) currRect.moveDown();
            if (prevPos[1] < currPos[1]) currRect.moveLeft();
            if (prevPos[1] > currPos[1]) currRect.moveRight();
            }
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

        position.add(0, new int[] {newRow, newCol});
        int[] tail = position.get(position.size() - 1);
        Rectangle newBody = new Rectangle();
        newBody.changeSize(20,20);
        newBody.setXP(tail[1] * 20);
        newBody.setYP(tail[0] * 20);
        newBody.changeColor(color);
        body.add(newBody);
        
        if(visible){
            makeVisible();
        }
    }
        
    
}


    
