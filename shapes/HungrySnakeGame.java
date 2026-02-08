import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Class HungrySnakeGame.
 * This class represents the main game logic for the Snake game.
 * It is responsible for creating the board, the snake, the apple,
 * and controlling movement, collisions, and game state.
 * 
 */
public class HungrySnakeGame
{
    /** List of rectangles that represent the game board cells */
    private List<Rectangle> cells;

    /** Number of fruits in the game (currently only one is used) */
    private int cantFrutas = 1;

    /** Snake object that represents the player */
    private Snake snake;

    /** Rectangle that represents the apple */
    private Rectangle manzana;

    /** Indicates whether the game state is valid */
    private boolean isOk;

    /**
     * Constructor for objects of class HungrySnakeGame.
     * Initializes the game board, the apple, and the snake.
     */
    public HungrySnakeGame()
    {
        cells = new ArrayList<>();

        /* Create an 8x8 board using Rectangle objects */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle cell;
                cell = new Rectangle();
                cell.changeSize(20, 20);
                cell.setXP(0);
                cell.setYP(0);
                cell.moveHorizontal(20 * i);
                cell.moveVertical(20 * j);
                cell.changeColor("green");
                cell.makeVisible();
                cells.add(cell);
            }
        }

        /* Initialize the apple */
        manzana = new Rectangle();
        manzana.changeSize(20, 20);
        manzana.changeColor("red");
        manzana.setYP(60);
        manzana.setXP(120);
        manzana.makeVisible();

        /* Initialize the snake */
        snake = new Snake(3, 1, "blue");
    }

    /**
     * Moves the snake in the given direction.
     * This method checks:
     * - If the snake eats the apple
     * - If the snake hits the borders
     * - Otherwise, performs a normal movement
     *
     * @param referencia the movement direction ('U', 'D', 'L', 'R')
     */
    public void moveSnake(char referencia)
    {
        /* Get the current head position of the snake */
        int[] posSnake = snake.head();
        int posSnakeY = posSnake[0];
        int posSnakeX = posSnake[1];

        /* Displacement values in pixels */
        int sumax = 0;
        int sumay = 0;

        /* Determine displacement based on direction */
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

        /* Check if the snake is going to eat the apple */
        if (manzana.getYP() == (posSnakeY * 20) + sumay &&
            manzana.getXP() == (posSnakeX * 20) + sumax) {

            snake.grow(referencia);

            /* Generate a new random position for the apple */
            Random rand = new Random();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);
            manzana.setXP(x * 20);
            manzana.setYP(y * 20);
            manzana.makeVisible();

        }
        /* Check if the snake hits the board boundaries */
        else if (posSnakeX + (sumax / 20) < 0 ||
                 posSnakeY + (sumay / 20) < 0 ||
                 posSnakeX + (sumax / 20) >= 8 ||
                 posSnakeY + (sumay / 20) >= 8) {

            mensajeConsolacion();

        }
        /* Normal snake movement */
        else {
            snake.move(referencia);
        }
    }

    /**
     * Displays a message indicating that the player has lost the game.
     */
    private void mensajeConsolacion()
    {
        JOptionPane.showMessageDialog(
            null,
            "Perdiste, suerte para la proxima",
            "Fin del Juego",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Prints the current game state to the console,
     * specifically the current length of the snake.
     */
    public void state()
    {
        System.out.println(
            "La longitud actual de la serpiente es:" + snake.snakeSize()
        );
    }

    /**
     * Restarts the game:
     * - Hides the current snake
     * - Resets the apple position
     * - Creates a new snake
     */
    public void reiniciar()
    {
        snake.makeInvisible();
        manzana.setYP(60);
        manzana.setXP(120);
        manzana.makeVisible();
        snake = new Snake(3, 1, "blue");
    }
}
