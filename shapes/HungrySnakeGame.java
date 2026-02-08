import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JOptionPane;

/**
 * Clase HungrySnakeGame, es el juego donde va a permitir jugar el snake
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HungrySnakeGame
{
    private List<Rectangle> cells;
    private int cantFrutas = 1;
    private Snake snake;
    private Rectangle manzana;
    private boolean isOk;
    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame()
    {
        cells = new ArrayList<>();
        for (int i = 0;i<8;i++){
            for (int j = 0;j<8;j++){
                Rectangle cell;
                cell = new Rectangle();
                cell.changeSize(20,20);
                cell.setXP(0);
                cell.setYP(0);
                cell.moveHorizontal(20*i);
                cell.moveVertical(20*j);
                cell.changeColor("green");
                cell.makeVisible();
                cells.add(cell);
            }
        }
        manzana=new Rectangle();
        manzana.changeSize(20,20);
        manzana.changeColor("red");
        manzana.setYP(60);
        manzana.setXP(120);
        manzana.makeVisible();
        snake = new Snake(3,1,"blue");
    }
    
    public void moveSnake(char referencia){
        int[] posSnake=snake.head();
        int posSnakeY=posSnake[0];
        int posSnakeX=posSnake[1];
        int sumax = 0;
        int sumay = 0;
        switch (referencia) {
            case 'U':
                sumax = 0;
                sumay = -20;
                break;
            case 'D':
                sumax = 0;
                sumay = 20;
                break;
            case 'R':
                sumax = 20;
                sumay = 0;
                break;
            case 'L':
                sumax = -20;
                sumay = 0;
                break;
            default:
                isOk = false;
                return;
        }
        if(manzana.getYP() == (posSnakeY*20)+sumay && manzana.getXP() == (posSnakeX*20)+sumax){
            snake.grow(referencia);
            manzana.setXP(0);
            manzana.setYP(0);
            manzana.makeVisible();
        }else if(posSnakeX+(sumax/20)<0 || posSnakeY+(sumay/20)<0 || posSnakeX+(sumax/20)>8 || posSnakeY+(sumay/20)>8){
            mensajeConsolacion();
        }else{
            snake.move(referencia);
        }
        
    }
    
    private void mensajeConsolacion(){
        JOptionPane.showMessageDialog(null, "Perdiste, suerte para la proxima", "Fin del Juego",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void state(){
        System.out.println("La longitud actual de la serpiente es:" + snake.snakeSize());
    }
    
    public void reiniciar(){
        snake.makeInvisible();
        manzana.setYP(60);
        manzana.setXP(120);
        manzana.makeVisible();
        snake = new Snake(3,1,"blue");
    }
}