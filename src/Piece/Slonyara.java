package Piece;

import Main.Panel;

public class Slonyara extends piece
{
    public Slonyara(int type, int color, int col, int row)
    {
        super(color, col, row);
        if (type == 1)
        {
        if(color== Panel.WHITE) image=getImage("w-slon");
        else  image=getImage("b-slon");
        }
        else
        {
            if(color== Panel.WHITE) image=getImage("w-slon2");
            else  image=getImage("b-slon2");
        }
    }

    public boolean canMove(int targetCol,int targetRow)
    {
        if(!outside(targetCol,targetRow)&&!sameSqr(targetCol,targetRow))
        {
            if((Math.abs(targetCol-colBefore)==Math.abs(targetRow-rowBefore)))
            {
                //двигается только диагонально
                if(isValidSqr(targetCol, targetRow))return true;
            }
        }
        return false;
    }
}
