package Piece;

import Main.Panel;

public class Peshka extends piece {

    public Peshka(int color, int col, int row) {
        super(color, col, row);
        if (color == Panel.WHITE) image = getImage("w-pawn");
        else image = getImage("b-pawn");

    }


    public boolean canMove(int targetCol, int targetRow)
    {
        if (!outside(targetCol, targetRow)&&!sameSqr(targetCol,targetRow))
        {
            int step;
            if(color==Panel.WHITE)
                step=-1;
            else step=1;

            Colliding = getCollision(targetCol,targetRow);
            if(targetCol==colBefore&&targetRow==rowBefore+step && Colliding==null)
                return true;

            if(targetCol==colBefore&&targetRow==rowBefore+step*2&&Colliding==null&&moved==false)
                return true;

            if(Math.abs(targetCol-colBefore)==1&&targetRow==rowBefore+step && Colliding!=null &&Colliding.color!=color)
                return true;


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
