


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import jdk.nashorn.internal.objects.Global;

class Kuyruk {
    private int x;
    private int y;

    public Kuyruk(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    
       
    
}

public class Oyun extends JPanel implements KeyListener,ActionListener{
    Timer timer = new Timer(100,this);
    private ArrayList<Kuyruk> kuyruklar = new ArrayList<Kuyruk>();
    int gecen_sure = 0 ;
    int tail = 0 ;
    int sizeX_s = 100;
    int sizeY_s = 100;
    int sizeX_f = 800;
    int sizeY_f = 500;
    int with = 20 ;
    int HeadX = 400;
    int HeadY = 300;
    int HeadDirX = 20;
    int HeadDirY = 0;
    int paint = 0 ;
    int[] temp ;
    int temp_y = 0; 
    int temp_x=0 ; 
    
    Random random = new Random();
    boolean Bait_exist ; 
    public boolean  kontrolEt (){
        if(sizeX_s-with*2<HeadX && HeadX<=sizeX_f+2*sizeX_s && HeadY+2>=sizeY_s && HeadY<=sizeY_f+sizeY_s*2){
            return false;
        }else{
            return true;
        }
    }
    public Oyun(){        
        setBackground(Color.BLACK);
        timer.start();
    }
    
    public void yazi(int i ){
        System.out.println(i);
    }
    
    public int[] Bait(){
        int[] Rand =new int[2]; 
        Rand[0] = 100+(random.nextInt(33)*20);
        Rand[1] = 100+(random.nextInt(23)*20);
        for(int i = kuyruklar.size()-1 ; i > 0 ; i-- ){
            //System.out.println(i);
            if(Rand[0]==kuyruklar.get(i).getX() && Rand[1]==kuyruklar.get(i).getY()){
                //System.out.println("2" + Rand[0] + " " +Rand[1]);
                Bait();
                //System.out.println("1" + Rand[0] +" "+Rand[1]);
            }else{
                
            } 
        }
       
        return Rand;
    } 

    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(Color.GRAY);
        g.drawRect(sizeX_s,sizeY_s,sizeX_s+sizeX_f,sizeY_s+sizeY_f);
        gecen_sure += 100 ;
        
        g.create(); 
        for (temp_x = 0  ; temp_x<(sizeX_f+sizeX_s) ; temp_x += with ){
            for(temp_y = 0 ; temp_y<(sizeY_f+sizeY_s); temp_y += with ){
                //System.out.println("temp_x" + temp_x);
                g.setColor(Color.GRAY);
                g.fillRect(sizeX_s+temp_x,sizeY_s+temp_y+1, with-1, with-1);
                System.out.println("1="+temp_x+"\n2="+temp_y);
                System.out.println("\n3="+sizeX_f+"\n4="+sizeX_s+"\n5="+sizeY_f+"\n6="+sizeY_s);
        
            }
        }     
        
        for(int i = 0 ; i< kuyruklar.size(); i++){
            g.setColor(Color.red);
            g.fillRect(kuyruklar.get(i).getX(),kuyruklar.get(i).getY(), with, with);
            g.setColor(Color.BLUE);
            g.drawRect(kuyruklar.get(i).getX(),kuyruklar.get(i).getY(), with, with);
        }
        g.create();    
        if(kontrolEt()){
            timer.stop();
            String message = "Kazandiniz......\n";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);    
        }
        if(Bait_exist){
            
            g.setColor(Color.CYAN);
            g.fillRect(temp[0],temp[1], with, with);
            //System.out.println(temp[0] +"bait"+ temp[1]);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif",Font.BOLD,20));
        g.drawString("X:"+HeadX +"Y:" + HeadY , sizeX_f+sizeX_s*3/2, sizeY_s);
        g.drawString("\n"+gecen_sure/1000.0 , sizeX_f+sizeX_s*3/2, 240);
        g.drawString("\n"+kuyruklar.size(), sizeX_f+sizeX_s*3/2, 260);
        try {
            g.drawString("\n"+temp[0]+"  "+temp[1], sizeX_f+sizeX_s*3/2, 280);
        } catch (Exception e) {
        }
        
    }

    @Override
    public void repaint() {
        super.repaint(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c==KeyEvent.VK_LEFT){
            if(HeadX>sizeX_s && HeadDirX!=with){
                HeadDirX = -with ;
                HeadDirY = 0 ;
            }
        }else if (c==KeyEvent.VK_UP && HeadDirY!=with) {
            if(HeadY>sizeY_s){
                HeadDirY = -with ;
                HeadDirX = 0 ; 
            }    
        }else if (c==KeyEvent.VK_DOWN && HeadDirY!=-with) {
            if(HeadY<sizeY_f+sizeY_s*2){
                HeadDirY = with ;
                HeadDirX= 0; 
            }
        }else if (c==KeyEvent.VK_RIGHT && HeadDirX!=-with) {
            if(HeadX<sizeX_f+sizeX_s*2){
                HeadDirX = with ;
                HeadDirY = 0;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            if(!Bait_exist){
                temp= Bait();
                //System.out.println("here");
                Bait_exist = true ; 
            }else{
                Bait_exist = false ; 
            }
            if(Bait_exist){
                //System.out.println("here");
                kuyruklar.add(new Kuyruk(-10, -10));
                //kuyruklar.add(new Kuyruk(10, 20));
                tail++;
            }
            
            for (int i = kuyruklar.size()-1 ; i>0;i--){
                if(HeadX==kuyruklar.get(i).getX() && HeadY==kuyruklar.get(i).getY()&& gecen_sure>300){
                    timer.stop();
                    //System.exit(0);
                }else{
                    kuyruklar.get(0).setX(HeadX);
                    kuyruklar.get(0).setY(HeadY);
                    int tempX = kuyruklar.get(i-1).getX();
                    int tempY = kuyruklar.get(i-1).getY();
                    kuyruklar.get(i).setX(tempX);
                    kuyruklar.get(i).setY(tempY);

                    if(temp[0]==kuyruklar.get(i).getX() && temp[1]==kuyruklar.get(i).getY()){
                        Bait_exist = false; 
                    }else{
                        Bait_exist = true; 
                    }
                }
                //System.out.println("i = "+i+"\n"+kuyruklar.get(i).getX()+"\t"+kuyruklar.get(i).getY());
            }
            //System.out.println(kuyruklar.get(0).getX()+"\t"+kuyruklar.get(0).getY());
            HeadX += HeadDirX;
            HeadY += HeadDirY;
            
            
            
        } catch (Exception a) {
            
        }
        repaint();
        
        
   
    }
      
}
    
    
       
    
