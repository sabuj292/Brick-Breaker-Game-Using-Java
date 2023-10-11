
package brickBracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//For moving the Ball.
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//For detecting the arrow for moving the slidher
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

    private boolean play = false;
    private int score =0;
    private int totalBricks =21; //Total number of Bricks
    private Timer timer; //Setting the time of Ball How fast the ball move 
    private int delay=8; //Speed that gives the timer
    
//Properties of X axis and y axis of Ball and slider(Bar)
    
    private int playerX=310; //Starting position of Bar
    private int ballposX=120; //Starting position of Ball for X axis
    private int ballposY=350; //Starting position of ball for Y axis
    
    private int ballXdir =-1; //Set the  Direction of the ball in the X axis
   private int ballYdir=-2; //Set the direction of the ball in the y axis
   
   

//Defining a constructor
   //Defining a variable/object for The class MapGenerator
   private MapGenerator map;
   


   public Gameplay()
   {
       //Creating an object of MapGenerator
       
       map= new MapGenerator(3,7);  //3 is the row number and 7 is the coloum number
       
     addKeyListener(this);  
     setFocusable(true);
     setFocusTraversalKeysEnabled(false);
     timer = new Timer(delay,this);
     timer.start();
     
   }
   
   //Function
   
   public void paint(Graphics g)
   {
     //Adding Background
       g.setColor(Color.red);
       //if error found, import the pakage for the color
       
     //Rectangle for the background
       g.fillRect(1,1,692,592);
       //Properties of the rectangle in the following order ((WIDTH, delay)/Position, (WIDTH, HEIGHT)/Size)
       
     //drawing map
        //callng the draw function of the MapGenerator
            
        map.draw((Graphics2D)g);    //Passing the object (Graphics2D)g in the  draw function
       
       
     //Border Creating 
       g.setColor(Color.yellow);
       
            //Creating 3 rectangle for the Boarder
       g.fillRect(0,0,3,592);
         g.fillRect(0,0,692,3);
           g.fillRect(691,0,3,592);
//Scores
    
    g.setColor(Color.WHITE);
    //g.setFont(new Front("serif",Font.BOLD,25));
    g.drawString(""+ score,590,30);
    
     

//The paddle Creating
     
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);
        
     // Ball Creating 
     
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);
        
//For finishing the game

       if(totalBricks<=0)
       {
               play=false;
            ballXdir=0; //For stopping the ball
            ballYdir=0;
            
        //Showing the text game over and showing the text press Enter of restart the game
            
            g.setColor(Color.GREEN);
            //
            //g.setFont(new Front("serif",Font.BOLD,25));
           g.drawString("HEY!!! YOU are WON",260,300);
           g.drawString("Press Enter to Restart ",230,350);
          
       }
        
    //For showing Game over and Show the total score
    
        if(ballposY>570)
        {
            play=false;
            ballXdir=0; //For stopping the ball
            ballYdir=0;
            
        //Showing the text game over and showing the text press Enter of restart the game
            
            g.setColor(Color.GREEN);
            //
            //g.setFont(new Front("serif",Font.BOLD,25));
           g.drawString("Game Over,Scores: "+score,190,300);
           g.drawString("Press Enter to Restart ",230,350);
          
           
    
        }
        
        
        g.dispose();
        
     
   }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        timer.start();
        
    //Code for moving the ball
        
        if(play)//play variable will be true when we will move the paddle
            {
//Code for intersecting paddel with ball./detecting the intersection            
  //1.  First we need to create a rectangle around that ball for detecting the intersect of two objects
            
            
                if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
                {
                    ballYdir=-ballYdir;
                }
          
                
                A:  for(int i=0;i<map.map.length;i++) //A indicate the level
           {
            for(int j=0;j<map.map[0].length;j++)
            {
 //For intersecting the rectangle with ball
                if(map.map[i][j]>0)
                {
                    int brickX=j*map.brickWidth+80;
                    int brickY=i*map.brickHeight+50;
                    int brickWidth=map.brickWidth;
                    int brickHeight=map.brickHeight;
                    
                    
    //Creating Rectangle around the bricks
                    
                    Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
             //Creating the rectangle around the ball for intersect with the brick
                    
                    Rectangle ballRect= new Rectangle(ballposX,ballposY,20,20);
                    Rectangle brickRect=rect;
             
        //Checking if this intersect or not
                    
                    if(ballRect.intersects(brickRect))
                    {
                          map.setBrickValue(0, i, j);
                    
                    totalBricks--;
                    score+=5;
                    
                    if(ballposX+19<=brickRect.x || ballposX+1>=brickRect.x+brickRect.width)
                    {
                        //in this condition we need to move the ball in the opposite direction
                        ballXdir=-ballXdir;
                        
                    }
                    else{
                        // in this case we need to ball towards the top,right,bottom direction
                        ballYdir=-ballYdir;
                    }
    // Here we need to move this from the outer loop
    // For this here we create a break statement
    // And create a Level in the upper outer for loop
                    break A;
                    }
                  
                }
            }
               
           }


//For checking the ball touching the top,left,right or not
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0)//For Left Boarder
            {
                ballXdir=-ballXdir;
            }
            if(ballposY<0)//For Top boarder
            {
                ballYdir=-ballYdir;
            }
             if(ballposX>670)//For Right boarder
            {
                ballXdir=-ballXdir;
            }
        }
    
        
        
        
        repaint();
        //repaint() is assigned here because when we need to move the paddle,the paddle should be create again.
        //for this purpuse we need to call the  public void paint(Graphics g) method or function   
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //keyPressed(KeyEvent e) is used for moving any object using the any instructed key from KeyBoard
    
    @Override
    public void keyPressed(KeyEvent e) 
    //For moving the bar/slidher
    {
              
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                if(playerX>=600)//For checking the bar inside within the boarder
                {
                    playerX=600;//For keeping the bar within the boarder
                }
                 else 
                {
                    moveRight();//For moving the slighter in the right direction
                } 
               
             }
        
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                if(playerX<10)
                {
                    playerX=10;
                }
                else
                {
                    moveLeft();
                }
            }
         
 //By pressing Enter to restart the game
 
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play= true;
                ballposX=120;
                ballposY=350;
                ballXdir=-1;
                ballYdir=-2;
                playerX=310;
                score=0;
                 totalBricks=21;
                 map=new MapGenerator(3,7);
                 
                 repaint();
                  
            }
        }
         
    }
    //Creating Method moveRight()
    public void moveRight()
    {
        play=true;
        playerX+=20; // For moving the bar/Slighder 20 pixel right
    }
    
    //Creating Method MoveLeft()
    public void moveLeft()
    {
        play=true;
        playerX-=20;//For moving the bar 20 pixel left
    }

   
}
