package Piece;

import Main.Panel;

public class Queen extends piece
{
    public Queen(int color, int col, int row)
    {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-queen");
        else  image=getImage("b-queen");
    }

    public boolean canMove(int targetCol,int targetRow)
    {
        if(!outside(targetCol,targetRow)&&!sameSqr(targetCol,targetRow))
        {
            if(Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)<=7||(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)<=7))
            {
                return true;
            }
        }
        return false;
    }
}
