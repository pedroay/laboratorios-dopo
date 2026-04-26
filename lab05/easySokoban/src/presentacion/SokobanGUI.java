package src.presentacion;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
public class SokobanGUI extends JFrame{
    private MenuWindow menuWindow;
    
    private SokobanGUI(){
        prepareElements();
        prepareActions();
        setTitle("EasySokoban");  
    }

    public static void main(String[] args){
        SokobanGUI gui = new SokobanGUI();
            gui.setVisible(true);

    }
    
    public void prepareElements(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.getWidth()/2);
        int height = (int) (screen.getHeight()/2);
        setSize(width,height);
        setLocationRelativeTo(null);
    }
    
    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
     });
    }

    private void exit() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Desea cerrar la aplicación EasySokoban?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == 0) {
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }
    
    class MenuWindow extends JPanel{
        public void prepareElementsMenuWindow(SokobanGUI app){
            
        }
        
        public MenuWindow(){
            setLayout(new GridLayout(3,1,10,10));
            
        }
    }
    
    

}
