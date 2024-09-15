package Piece;

import Main.Panel;

public class Peshka extends piece{


    public Peshka(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-pawn");
        else  image=getImage("b-pawn");

    }


    public boolean canMove(int targetCol,int targetRow)
    {
        int count=1;
        int initCol = col;
        int initRow = row;

        if(!outside(targetCol,targetRow))
        {
            if(initRow==row&&initCol==col)
            {
                if(Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)==1||(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==1)){
                    return true;
                }
            }

            else
            if(Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)==1||(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==1)){
                return true;
            }
        }
        return false;
    }

}