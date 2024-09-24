package Piece;

import Main.Panel;

public class Rook extends piece
{


    public Rook(int color, int col, int row)
    {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-rook");
        else  image=getImage("b-rook");

    }

    public boolean canMove(int targetCol,int targetRow)
    {
        if(!outside(targetCol,targetRow))
        {
            if((Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)<=7)&&(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==0)&&!sameSqr(targetCol,targetRow))
            {
                //двигается только по вертикально и горизонтально
                if(isValidSqr(targetCol, targetRow)) return true;
            }
        }
        return false;
    }

}