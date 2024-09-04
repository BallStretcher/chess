package Piece;

import Main.Panel;

public class Rook extends piece{


    public Rook(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-rook");
        else  image=getImage("b-rook");

    }

}