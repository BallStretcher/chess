package Piece;

import Main.Panel;

public class King extends piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-king");
        else  image=getImage("b-king");


    }
    public boolean canMove(int targetCol,int targetRow){
        if(!outside(targetCol,targetRow)){
            if(Math.abs(targetCol-colBefore)+Math.abs(targetRow-rowBefore)==1||(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==1)){
               // if(Math.abs(targetCol-colBefore)*Math.abs(targetRow-rowBefore)==1) {
                    return true;
                //}

                }
                    //если расстояние больше одного для короля


            }
         return false;
    }
}
