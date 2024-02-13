


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

class Path {
    private int x; 
    private int y; 
    private int val; 
    private ArrayList<Path> parent; 
    // private Path parent; // Add this line
    
    public Path(int x, int y, int val, ArrayList<Path> parent) { // Modify constructor
        this.x = x;
        this.y = y;
        this.val = val;
        this.parent = parent;
    }

    public Path(int x, int y, int val) { // Modify constructor
        this.x = x;
        this.y = y;
        this.val = val;
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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    public ArrayList<Path> getParent() {
        return parent;
    }
    public ArrayList<Path> setParent(ArrayList<Path> parent){

        return this.parent=parent;

    }
    
}

public class Oyun extends JPanel implements KeyListener,ActionListener{
    int oyun_hizi=130; 
    Timer timer = new Timer(oyun_hizi,this);
    private ArrayList<Kuyruk> kuyruklar = new ArrayList<Kuyruk>();
    int gecen_sure = 0 ;
    int tail = 0 ;
    int sizeX_s = 100;
    int sizeY_s = 100;
    int sizeX_f = 300;
    int sizeY_f = 300;
    int with = 20 ;
    int HeadX = 200;
    int HeadY = 300;
    int HeadDirX = 20;
    int HeadDirY = 0;
    int paint = 0 ;
    int[] temp ;
    int temp_y = 0; 
    int temp_x=0 ; 
    private boolean isKeyPressed = false;
    Random random = new Random();
    boolean Bait_exist ; 
    int pathcon= 0 ; 
    ArrayList<Path> path1  = new ArrayList<Path>(); 
    public boolean  kontrolEt (){
        if(sizeX_s<=HeadX && HeadX<sizeX_f+2*sizeX_s && HeadY>sizeY_s-with && HeadY<sizeY_f+sizeY_s*2){
            return false;
        }else{
            return true;
        }
    }
    public Oyun(){        
        setBackground(Color.BLACK);
        for (temp_x = 0  ; temp_x<(sizeX_f+sizeX_s) ; temp_x += with ){
            for(temp_y = 0 ; temp_y<(sizeY_f+sizeY_s); temp_y += with ){
                JPanel grid_layer = new JPanel();
                setLayout(null);
//                System.out.println("temp_x" + temp_x);
                grid_layer.setBackground(Color.darkGray);  
                grid_layer.setBounds(temp_x+sizeX_s+1,temp_y+sizeY_s+1, with-1, with-1);
                grid_layer.setOpaque(true);
//                System.out.println("1="+temp_x+"\n2="+temp_y);
//                System.out.println("\3="+sizeX_f+"\n4="+sizeX_s+"\n5="+sizeY_f+"\n6="+sizeY_s+"\n");
                add(grid_layer);
            }
        }
        timer.start();
        
    }

    public Oyun(int[] temp, boolean Bait_exist) {
        this.temp = temp;
        this.Bait_exist = Bait_exist;
        if(gecen_sure%300 == 0 ){
            oyun_hizi -=50;
            System.out.println("here");
        }
        
    }
    
    public void yazi(int i ){
        System.out.println(i);
    }
    
    public int[] Bait(ArrayList<Kuyruk> kuyruklar){
        int[] Rand =new int[2]; 
        Rand[0] = 120+(random.nextInt((sizeX_f+sizeX_s-40)/20)*20);
        Rand[1] = 120+(random.nextInt((sizeY_f+sizeY_s-40)/20)*20);
        for (int i = 0; i < kuyruklar.size(); i++) {
            if (Rand[0] == kuyruklar.get(i).getX() && Rand[1] == kuyruklar.get(i).getY()) {
                System.out.println("Bait X " + Rand[0] + " Y " + Rand[1]);
                System.out.println("kuyruk X " + kuyruklar.get(i).getX() + " Y " + kuyruklar.get(i).getY());
                return Bait(kuyruklar); // Recursive call to reposition the bait
            }
        }
    // If loop completes without finding any overlapping, return the position
    return Rand;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(Color.GRAY);
        g.drawRect(sizeX_s,sizeY_s,sizeX_s+sizeX_f,sizeY_s+sizeY_f);
        gecen_sure += 100 ;     

        for(int i = 0 ; i< kuyruklar.size(); i++){
            g.setColor(Color.orange);
            g.fillRect(HeadX+1,HeadY+1, with-1, with-1);
            g.setColor(Color.red);
            g.fillOval(kuyruklar.get(i).getX(),kuyruklar.get(i).getY(), with-1, with-1);
//            g.setColor(Color.GREEN);
//            g.fillRect(HeadX,HeadY,10,10);
            g.setColor(Color.BLUE);
            g.drawOval(kuyruklar.get(i).getX(),kuyruklar.get(i).getY(), with, with);
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
            g.fillRect(temp[0]+1,temp[1]+1, with-1, with-1);
            //System.out.println(temp[0] +"bait"+ temp[1]);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif",Font.BOLD,20));
        g.drawString("X:"+HeadX +"Y:" + HeadY , sizeX_f+sizeX_s, sizeY_s);
        g.drawString("\n"+gecen_sure/1000.0 , sizeX_f+sizeX_s+105, 240);
        g.drawString("\n"+kuyruklar.size(), sizeX_f+sizeX_s+105, 260);
        try {
            g.drawString("\n"+temp[0]+"  "+temp[1], sizeX_f+sizeX_s+105, 280);
        } catch (Exception e) {
        }
        
        // g.create();
        // for(int i = 0 ; i< path1.size(); i++){
        //     g.setColor(Color.yellow);
        //     g.fillRect(path1.get(i).getX(), path1.get(i).getY(),  with-1, with-1);
        // }
//            g.setColor(Color.GREEN);
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
            if(HeadX>sizeX_s && HeadDirX!=with && isKeyPressed ==false){
                HeadDirX = -with ;
                HeadDirY = 0 ;
                isKeyPressed =true;
            }
        }if (c==KeyEvent.VK_UP && HeadDirY!=with && isKeyPressed ==false) {
            if(HeadY>sizeY_s){
                HeadDirY = -with ;
                HeadDirX = 0 ; 
                isKeyPressed =true;
            }    
        }if (c==KeyEvent.VK_DOWN && HeadDirY!=-with && isKeyPressed ==false) {
            if(HeadY<=sizeY_f+sizeY_s*2){
                HeadDirY = with ;
                HeadDirX= 0; 
                isKeyPressed =true;
            }
        }if (c==KeyEvent.VK_RIGHT && HeadDirX!=-with && isKeyPressed ==false) {
            if(HeadX<=sizeX_f+sizeX_s*2){
                HeadDirX = with ;
                HeadDirY = 0;
                isKeyPressed =true;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            if(Bait_exist==false){
                temp= Bait(kuyruklar);

                //System.out.println("here");
                Bait_exist = true ; 
            }else{
                Bait_exist = false ; 
            }            
            if(Bait_exist){
                //System.out.println("here");
                kuyruklar.add(new Kuyruk(0, 0));
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

                    if(temp[0]==HeadX && temp[1]==HeadY){
                        Bait_exist = false;
           
                        
                    }else{
                        Bait_exist = true; 
                    }
                }
                //System.out.println("i = "+i+"\n"+kuyruklar.get(i).getX()+"\t"+kuyruklar.get(i).getY());
            }
            //System.out.println(kuyruklar.get(0).getX()+"\t"+kuyruklar.get(0).getY());
            ArrayList<Path> path1  = new ArrayList<Path>();
            if (kuyruklar.size()>2 && Bait_exist==true){
                path1 = pathFinding(HeadX, HeadY, temp, kuyruklar,path1,pathcon);
                pathcon++ ; 
            }
            HeadX += HeadDirX;
            HeadY += HeadDirY;
        } catch (Exception a) {
            
        } 
         
                
        
        repaint();
             
    }

    private ArrayList<Path> pathadd( ArrayList<Path> Temp, ArrayList<Path> checPaths1,int[] array){
        ArrayList<Path> Templist  = new ArrayList<Path>();
        for(int i= 0 ;i<3;i++ ){
            if(array[i]>0){
                Templist.add(Temp.get(i));
                Templist.get(i).setParent(checPaths1);
                checPaths1.add(Templist.get(i));
                System.out.println(checPaths1.size());
            }
        }
        return checPaths1;  
    } 



    private ArrayList<Path> pathFinding(int headx, int heady, int[] bait, ArrayList<Kuyruk> kuyruklar,ArrayList<Path> path1,int pathcon) {
        ArrayList<Path> checkedpath  = new ArrayList<Path>(); 
        checkedpath = path1;
        Path TOP =new Path(headx,heady+20,0);
        Path BOTTOM =new Path(headx,heady-20,0);
        Path LEFT =new Path(headx+20,heady,0);
        Path RIGHT =new Path(headx-20,heady,0);
        ArrayList<Path> Temp  = new ArrayList<Path>();
        Temp.add(TOP);
        Temp.add(BOTTOM);
        Temp.add(LEFT);
        Temp.add(RIGHT);
        System.out.println("fuuuuc"+pathcon);
        boolean findway = false;
        int Roamx = headx;
        int Roamy = heady; 
        int iter = 0 ; 
        Path newPath = new  Path(headx,heady,iter,checkedpath);
        checkedpath.add(newPath);
        int k = 0 ; 
        int iteration = 0 ; 
        while(findway!= true){
            int[] add = {1,1,1,1};
            Roamx = checkedpath.get(k).getX();
            Roamy = checkedpath.get(k).getY();
            // System.out.println("Here"+iteration);
            // System.out.println(checkedpath.get(iter).getX());
            // System.out.println(checkedpath.get(iter).getY());
            // System.out.println(checkedpath.get(iter).getVal());         
            // System.out.println(k);
            // System.out.println("asdasdasdasda"+checkedpath.size());
            // System.out.println(Roamx+"//"+Roamy);
            int[] top =new int[2];
            top[0]= Roamx;
            top[1]= Roamy+20;
            int[] bottom =new int[2];
            bottom[0]= Roamx;
            bottom[1]= Roamy-20;
            int[] left =new int[2];
            left[0]= Roamx+20;
            left[1]= Roamy;
            int[] right =new int[2];
            right[0]= Roamx-20;
            right[1]= Roamy;
            if(top[0]==bait[0]&&top[1]==bait[1]||bottom[0]==bait[0]&&bottom[1]==bait[1]||
                left[0]==bait[0]&&left[1]==bait[1]||right[0]==bait[0]&&right[1]==bait[1]){
                    findway=true ; 
                    System.out.println("Bait is found");
            }else{
                
                for(int i=0 ; i <checkedpath.size();i++){
                    // System.out.println("Fuck this shit"+ i);
                    System.out.println(checkedpath.size());
                    System.out.println("TopX"+checkedpath.get(i).getX()+"TopY"+checkedpath.get(i).getY());
                    // System.out.println(checkedpath.get(i).getX());
                    // System.out.println(checkedpath.get(i).getY());
                    // System.out.println(top[0]);
                    // System.out.println(top[1]);
                    if(checkedpath.get(i).getX()!=top[0] && checkedpath.get(i).getY()!=top[1]){
                        // System.out.println("TopX"+checkedpath.get(i).getX()+"TopY"+checkedpath.get(i).getY());
                        Temp.get(0).setX(top[0]);
                        Temp.get(0).setY(top[1]);
                        Temp.get(0).setVal(iter);
                        iter++;
                        add[0]= add[0]*1 ;
                    }else{
                        // add[0]= add[0]*-1 ;
                    }
                    if(checkedpath.get(i).getX()!=bottom[0]&&checkedpath.get(i).getY()!=bottom[1]){
                        // System.out.println("BottomX"+checkedpath.get(i).getX()+"BottomY"+checkedpath.get(i).getY());
                        Temp.get(1).setX(bottom[0]);
                        Temp.get(1).setY(bottom[1]);
                        Temp.get(1).setVal(iter);
                        iter++;
                        add[1]= add[1]*1 ;
                    }else{
                        add[1]= add[1]*-1 ;
                    }
                    if(checkedpath.get(i).getX()!=left[0]&&checkedpath.get(i).getY()!=left[1]){
                        // System.out.println("LeftX"+checkedpath.get(i).getX()+"LeftY"+checkedpath.get(i).getY());
                        Temp.get(2).setX(left[0]);
                        Temp.get(2).setY(left[1]);
                        Temp.get(2).setVal(iter);
                        iter++;
                        add[2]= add[2]*1 ;
                    }else{
                        add[2]= add[2]*-1 ;
                    }
                    if(checkedpath.get(i).getX()!=right[0]&&checkedpath.get(i).getY()!=right[1]){
                        // System.out.println("RightX"+checkedpath.get(i).getX()+"RightY"+checkedpath.get(i).getY());
                        Temp.get(3).setX(right[0]);
                        Temp.get(3).setY(right[1]);
                        Temp.get(3).setVal(iter);
                        iter++;
                        add[3]= add[3]*1 ;
                    }else{
                        add[3]= add[3]*-1 ;
                    }
                    

                }
                System.out.println("FUUUFUFUFUUFUFUUFUBFUFUFUYU");
                checkedpath =pathadd(Temp,checkedpath,add);
              
            }
                    
            k ++ ; 
            iteration++ ;
        }
        pathcon++;
        return checkedpath ; 
    }
        //     // Initialize visited array
    //     boolean[][] visited = new boolean[sizeX_f/20][sizeY_f/20]; // Assuming grid size based on sizeX_f and sizeY_f

    //     // Initialize queue for BFS
    //     Queue<Path> queue = new LinkedList<>();
    //     queue.add(new Path(headx, heady, 0));  // Starting position of the snake's head

    //     while (!queue.isEmpty()) {
    //         Path current = queue.poll();

    //         // Convert grid position to array index
    //         int indexX = (current.getX() + sizeX_s) / 20;
    //         int indexY = (current.getY() + sizeY_s) / 20;

    //         // Check if reached bait
    //         if (current.getX() == bait[0] && current.getY() == bait[1]) {
    //             // Found the bait, reconstruct the path if necessary or return result
    //             return reconstructPath(current); // Implement this method based on how you plan to use the path
    //         }

    //         // Mark as visited
    //         if (indexX < 0 || indexX >= visited.length || indexY < 0 || indexY >= visited[0].length) continue; // Ensure within bounds
    //         if (visited[indexX][indexY]) continue; // Already visited this cell
    //         visited[indexX][indexY] = true;

    //         // Add all possible moves (up, down, left, right)
    //         int[][] directions = {{20, 0}, {-20, 0}, {0, 20}, {0, -20}};
    //         for (int[] dir : directions) {
    //             int newX = current.getX() + dir[0];
    //             int newY = current.getY() + dir[1];
    //             if (isValidMove(newX, newY, kuyruklar, visited)) { // Implement this based on your grid size and snake body
    //                 queue.add(new Path(newX, newY, current.getVal() + 1, current)); // Notice the 'current' at the end
    //             }
    //         }
    //     }
    //     return new ArrayList<>(); // Return empty path if no path found
    // }

    // // Update isValidMove method to check against the visited array
    // private boolean isValidMove(int x, int y, ArrayList<Kuyruk> kuyruklar, boolean[][] visited) {
    //     int indexX = (x - sizeX_s) / 20;
    //     int indexY = (y - sizeY_s) / 20;
    //     if (indexX < 0 || indexX >= visited.length || indexY < 0 || indexY >= visited[0].length) return false; // Check bounds

    //     // Check if the move is valid (not hitting the snake itself and not visited)
    //     for (Kuyruk segment : kuyruklar) {
    //         if (segment.getX() == x && segment.getY() == y) {
    //             return false; // Collision with snake
    //         }
    //     }
    //     return !visited[indexX][indexY]; // Return true if not visited
    // }

    
}
     




