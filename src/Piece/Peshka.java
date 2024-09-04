package Piece;

import Main.Panel;

public class Peshka extends piece{


    public Peshka(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-pawn");
        else  image=getImage("b-pawn");

    }

}