package Piece;

import Main.Panel;

public class Peshka extends piece{
    public int turn=1;

    public Peshka(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-pawn");
        else  image=getImage("b-pawn");

    }


    public boolean canMove(int targetCol,int targetRow)
    {
        if(!outside(targetCol,targetRow))
        {
            {
                if(turn==1)
                {
                if ((Math.abs(targetCol - colBefore)==0 &&(targetRow - rowBefore)>=-2)&&(targetRow - rowBefore)<=0)
                {
                    return true;
                }
                }
                else if((Math.abs(targetCol - colBefore)==0 &&(targetRow - rowBefore)>=-1)&&(targetRow - rowBefore)<=0)
                    return true;
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
//
//
//
//
//
//                else
//            {
//                if ((Math.abs(targetCol - colBefore) + Math.abs(targetRow - rowBefore) <= 1) && (Math.abs(targetCol - colBefore) * Math.abs(targetRow - rowBefore) == 0)) {
//                return true;
//            }
//
//            }
