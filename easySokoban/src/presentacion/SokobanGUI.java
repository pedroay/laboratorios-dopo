package src.presentacion;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SokobanGUI extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuWindow menuWindow;
    private JFileChooser fileChooser;
    private JPanel boardPanel;
    private Color colorPiezas = Color.RED;
    
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
        setTitle("EasySokoban");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.getWidth()/2);
        int height = (int) (screen.getHeight()/2);
        setSize(width,height);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        boardPanel = new JPanel();
        menuWindow = new MenuWindow(this);
        prepareElementsBoard();
        mainPanel.add(menuWindow, "MENU");
        mainPanel.add(boardPanel, "BOARD");
        add(mainPanel);
        fileChooser = new JFileChooser(".");
        
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
    
    
    private void elementosMenuPrincipal() {
        menuWindow = new MenuWindow(this);
        add(menuWindow, BorderLayout.CENTER);
    }
    
    public void abrir() {
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, 
                "Función de APERTURA en construcción.\nArchivo: " + archivo.getName(), 
                "En desarrollo", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void salvar() {
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, 
                "Función de GUARDADO en construcción.\nArchivo: " + archivo.getName(), 
                "En desarrollo", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void prepareElementsBoard() {
        JButton color = new JButton();
        boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel("Aquí irá el tablero de Sokoban", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        boardPanel.add(label, BorderLayout.CENTER);
    }
    
    public void refresh() {
        repaint(); 
    }
    
    public void irAlTablero() {
        cardLayout.show(mainPanel, "BOARD");
    }
    
    public void cambiarColorPiezas() {
        Color nuevoColor = JColorChooser.showDialog(this, "Seleccione el color de las piezas", colorPiezas);
        if (nuevoColor != null) {
            colorPiezas = nuevoColor;
            refresh();
            JOptionPane.showMessageDialog(this, "Color actualizado correctamente.");
        }
    }
    class MenuWindow extends JPanel{
        private SokobanGUI gui;
        private JButton newButton;
        private JButton saveButton;
        private JButton cancelButton;
        private JButton loadButton;
        
        public void prepareElementsMenuWindow(){
            newButton = new JButton("New");
            saveButton = new JButton("Save");
            loadButton = new JButton("Load");
            cancelButton = new JButton("Cancel");
                
            add(newButton);
            add(saveButton);
            add(loadButton);
            add(cancelButton);
                    }
        
        public MenuWindow(SokobanGUI app){
            this.gui = app;
            setLayout(new GridLayout(4,1,10,10));
            prepareElementsMenuWindow();
            prepareActionsMenuWindow();
        }
        
        private void prepareActionsMenuWindow() {
            
            cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.exit(); 
                }
            });
            
            newButton.addActionListener(e -> gui.irAlTablero());
            saveButton.addActionListener(e -> gui.salvar());
            cancelButton.addActionListener(e -> gui.exit());
            loadButton.addActionListener(e -> gui.abrir());
        }
        
    }
    
    

}
