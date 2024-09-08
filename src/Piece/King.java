package Piece;

import Main.Panel;

public class King extends piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-king");
        else  image=getImage("b-king");
    }
}
