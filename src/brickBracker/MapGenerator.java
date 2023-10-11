
//The purpose of the class is construct the brick
package brickBracker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class MapGenerator 
{
    public int map[][]; //This array for constructing the brick
    public int brickWidth;
    public int brickHeight;
    public MapGenerator(int row,int col)
    {
        map = new int [row][col];
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                map[i][j]=1;
                //this 1 will detect that this particular brick willnot intersect will ball
                //because this has to  be shown on the panel
                //if i don't want to draw any particular brick on the panel then
                //we hava to make map[i][j]=0;
            }
        }
        brickWidth=540/col;
        brickHeight=150/row;
    }
    //This function for drawing thos particular brick
    //when this function will be called this function will draw brick in the particular position where map[i][j]=1
    public void draw(Graphics2D g)
    {
          for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                    g.setColor(Color.white);    //color of the brick
                    g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                    
                    
             //Creating boarder for individual map
             g.setStroke(new BasicStroke(3));
             g.setColor(Color.black);
             //here the color is red because the Backgroud color is red
             g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
             
             
             
                }
            }
        }
    }
    public void setBrickValue(int value,int row,int col)
    {
     map[row][col]=value;   
    }
}
    

