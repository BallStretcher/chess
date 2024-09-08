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

    }
