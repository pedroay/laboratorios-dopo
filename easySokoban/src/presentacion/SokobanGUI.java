package src.presentacion;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JButton;
import dominio.*;

public class SokobanGUI extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuWindow menuWindow;
    private Menu2Window menu2Window;
    private JFileChooser fileChooser;
    private JPanel boardPanel;
    private JPanel gridPanel;
    private Color colorPiezas = Color.RED;
    private Sokoban juego;
    
    private SokobanGUI(){
        juego = new Sokoban();
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
        menu2Window = new Menu2Window(this);
        prepareElementsBoard();
        mainPanel.add(menuWindow, "MENU");
        mainPanel.add(menu2Window, "MENU2");
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
        
        // Listener para el movimiento
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
        setFocusable(true);
    }
    
    private void handleKeyPress(KeyEvent e) {
        int code = e.getKeyCode();
        boolean movio = false;
        
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            movio = juego.mover(Sokoban.ARRIBA);
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            movio = juego.mover(Sokoban.ABAJO);
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            movio = juego.mover(Sokoban.IZQUIERDA);
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            movio = juego.mover(Sokoban.DERECHA);
        }
        
        if (movio) {
            renderBoard();
            if (juego.nivelCompletado()) {
                JOptionPane.showMessageDialog(this, "¡Nivel Completado!");
                irAlMenu();
            }
        }
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
    
    
    private void irAlMenu() {
        cardLayout.show(mainPanel, "MENU");
    }
    
    public void irAMenu2() {
        cardLayout.show(mainPanel, "MENU2");
    }
    
    public void continuarJuego() {
        cardLayout.show(mainPanel, "BOARD");
        requestFocusInWindow();
    }
    
    public void reiniciarJuego() {
        juego.reiniciar();
        renderBoard();
        continuarJuego();
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
        boardPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel();
        
        JButton pauseButton = new JButton("Menú");
        pauseButton.addActionListener(e -> irAMenu2());
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(pauseButton);
        
        boardPanel.add(topPanel, BorderLayout.NORTH);
        boardPanel.add(gridPanel, BorderLayout.CENTER);
        
        renderBoard();
    }

    private void renderBoard() {
        gridPanel.removeAll();
        int filas = juego.getFilas();
        int columnas = juego.getColumnas();
        gridPanel.setLayout(new GridLayout(filas, columnas));
        
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                char simbolo = juego.getSimboloEn(f, c);
                JPanel celda;
                
                if (simbolo == '@') {
                    celda = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D) g;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2d.setColor(Color.RED);
                            int margin = 5;
                            g2d.fillOval(margin, margin, getWidth() - 2 * margin, getHeight() - 2 * margin);
                        }
                    };
                    celda.setBackground(Color.LIGHT_GRAY);
                } else {
                    celda = new JPanel();
                    switch (simbolo) {
                        case '#': celda.setBackground(Color.GRAY); break;
                        case '$': celda.setBackground(Color.ORANGE); break;
                        case '.': celda.setBackground(Color.GREEN); break;
                        case '*': celda.setBackground(Color.YELLOW); break;
                        default: celda.setBackground(Color.LIGHT_GRAY); break;
                    }
                }
                
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                gridPanel.add(celda);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    
    public void refresh() {
        repaint(); 
    }
    
    public void irAlTablero() {
        renderBoard();
        cardLayout.show(mainPanel, "BOARD");
        requestFocusInWindow();
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
        protected SokobanGUI gui;
        protected JButton newButton;
        protected JButton saveButton;
        protected JButton cancelButton;
        protected JButton loadButton;
        
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
        
        protected void prepareActionsMenuWindow() {
            
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
    class Menu2Window extends MenuWindow {
        private JButton resumeButton;
        
        public Menu2Window(SokobanGUI app) {
        	super(app);
            setLayout(new GridLayout(5, 1, 10, 10));
        }
        @Override
        public void prepareElementsMenuWindow(){
            super.prepareElementsMenuWindow();
            resumeButton = new JButton("Resume");
            add(resumeButton);
        }
        @Override
        protected void prepareActionsMenuWindow() {
        	super.prepareActionsMenuWindow();
            resumeButton.addActionListener(e -> gui.continuarJuego());
            
            // Reemplazamos la acción de 'New' para que reinicie en este menú
            for (ActionListener al : newButton.getActionListeners()) {
                newButton.removeActionListener(al);
            }
            newButton.addActionListener(e -> gui.reiniciarJuego());
        }
    }
    
}
