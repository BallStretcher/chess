package Piece;

import Main.Panel;

public class KoHb extends piece{
    public KoHb(int type, int color, int col, int row) {
        super(color, col, row);
        if (type == 1) {
            if (color == Panel.WHITE) image = getImage("w-horse");
            else image = getImage("b-horse");
        }
        else
        if (color == Panel.WHITE) image = getImage("w-horse2");
        else image = getImage("b-horse2");
    }


    public boolean canMove(int targetCol,int targetRow){
        if(!outside(targetCol,targetRow)){
            if(Math.abs(targetCol-colBefore)==2&&Math.abs(targetRow-rowBefore)==1){
                //двигается только буквой "Г"
                return true;
            }
            if(Math.abs(targetCol-colBefore)==1&&Math.abs(targetRow-rowBefore)==2){
                //двигается только буквой "Г"
                return true;
            }
        }
        return false;
    }

    }
