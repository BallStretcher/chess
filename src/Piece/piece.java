package Piece;

import Main.Borda;
import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class piece
{
    public BufferedImage image;
    public int x,y,col,row,colBefore,rowBefore,color;
    public piece Colliding;

    public piece(int color,int col, int row)
    {
        this.col = col;
        this.color = color;
        this.row = row;
        x = getX(col);
        y = getY(row);
        colBefore = col;
        rowBefore = row;

    }
    public BufferedImage getImage(String imagePath)
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col)
    {
        return col* Borda.SQUARE_SIZE;
    }

    public int getY(int row)
    {
        return row*Borda.SQUARE_SIZE;
    }
    public int getRow(int y)
    {
        return ((y+Borda.HALF_SQUARE_SIZE)/Borda.SQUARE_SIZE);
    }

    public int getCol(int x)
    {
        return ((x+Borda.HALF_SQUARE_SIZE)/Borda.SQUARE_SIZE);
    }


    public void draw(Graphics2D g2)
    {
        g2.drawImage(image,x,y,Borda.SQUARE_SIZE,Borda.SQUARE_SIZE,null);
    }
    public void updPos()
    {
        x = getX(col);
        y = getY(row);
        colBefore = getCol(x);
        rowBefore = getRow(y);
    }
    public void reset()
    {
        col = colBefore;
        row = rowBefore;
        x = getX(col);
        y = getY(row);

    }
    public boolean canMove(int targetCol,int targetRow)
    {
        return false;
    }
    public boolean outside(int targetCol,int targetRow)
    {
        return targetCol < 0 || targetRow < 0 || targetCol > 7 || targetRow > 7;
    }
    public piece getCollision(int targetCol, int targetRow)
    {
        for(piece P: Panel.simPieces)
        {
            if(P.col==targetCol && P.row==targetRow && P!=this) return P;
        }
        return null;
    }

    public int getIndex()
    { for(int i=0;i<Panel.simPieces.size();i++)
        if(Panel.simPieces.get(i)==this)
            return i;
        return 0;
    }

    public boolean isValidSqr(int targetCol,int targetRow)
    {
        Colliding = getCollision(targetCol,targetRow);
        if(Colliding==null)
            return true;
        else
        {

            if(Colliding.color!=this.color)
            {System.out.println("[eq");
                return true;
            }
            else Colliding=null;
        }
        return false;
    }
    public boolean sameSqr(int targetCol,int targetRow)
    {
        return (targetCol==colBefore&&targetRow==rowBefore);


    }

}
