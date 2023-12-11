
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class SnakeGame extends JFrame{

    public SnakeGame(String title) throws HeadlessException {
        super(title);
    }
   
    public static void main(String[] args){
        
        SnakeGame ekran = new SnakeGame("Snake Oyunu"); 
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(1100,800);
        //ekran.setSize(900,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun(); 
        oyun.requestFocus(); 
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setVisible(true);
    }    

}