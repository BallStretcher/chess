package Piece;

import Main.Panel;

public class Peshka extends piece{
    int moveCount=1;
    public Peshka(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-pawn");
        else  image=getImage("b-pawn");

    }


    public boolean canMove(int targetCol,int targetRow)
    {


        if(!outside(targetCol,targetRow))
        {


            if (moveCount == 1)
            {
                if ((Math.abs(targetCol - colBefore) + Math.abs(targetRow - rowBefore) <= 3)) {
                    moveCount++;
                    return true;
                }
            }
            else
            {
                if ((Math.abs(targetCol - colBefore) + Math.abs(targetRow - rowBefore) <= 1) && (Math.abs(targetCol - colBefore) * Math.abs(targetRow - rowBefore) == 0)) {
                return true;
            }

            }
        }
        return false;
    }

}

//*
// else
//                if(Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)==1||(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==1))
//                {
//                    return true;
//                }