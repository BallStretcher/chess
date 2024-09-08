package Piece;

import Main.Panel;

public class Slonyara extends piece{
    public Slonyara(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-slon");
        else  image=getImage("b-slon");
    }
}
