package Piece;

import Main.Panel;

public class KoHb extends piece{
    public KoHb(int color, int col, int row) {
        super(color, col, row);
        if(color== Panel.WHITE) image=getImage("w-horse");
        else  image=getImage("b-horse");
    }
}
