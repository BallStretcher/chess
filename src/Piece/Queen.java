package Piece;

import Main.Panel;

public class Queen extends piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-queen");
        else  image=getImage("b-queen");
    }
}
