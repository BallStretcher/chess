package Main;

import java.awt.*;

public class Borda {
    final int RYAD = 8;
    final int COLN = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;
    public void draw(Graphics2D g2){
        int c=0;
        for(int row = 0;row<RYAD;row++){
            if(c==0){
                g2.setColor(Color.ORANGE);
                c=1;
            }
            else{
                g2.setColor(Color.YELLOW);
                c=0;
            }
            for(int coln=0;coln<COLN;coln++){
                if(c==0){
                    g2.setColor(Color.ORANGE);
                    c=1;
                }
                else{
                    g2.setColor(Color.YELLOW);
                    c=0;
                }
                g2.fillRect(coln*SQUARE_SIZE,row*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
            }
        }
    }

}
