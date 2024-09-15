package Main;
import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int fps = 60;
    Thread gameThread;
    Borda borda = new Borda();
    Maus mouse = new Maus();
    boolean canMove;
    boolean validSquare;



    public static ArrayList<piece> pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();
    piece activeP;

    public void setPieces(){
        for(int i=0;i<8;i++)
            pieces.add(new Peshka(WHITE,i,6));
        pieces.add(new Rook(WHITE,0,7));
        pieces.add(new Rook(WHITE,7,7));
        for(int i=0;i<8;i++)
            pieces.add(new Peshka(BLACK,i,1));
        pieces.add(new Rook(BLACK,0,0));
        pieces.add(new Rook(BLACK,7,0));
        pieces.add(new KoHb(1,WHITE,1,7));
        pieces.add(new KoHb(2,WHITE,6,7));
        pieces.add(new KoHb(1,BLACK,1,0));
        pieces.add(new KoHb(2,BLACK,6,0));
        pieces.add(new Slonyara(1,WHITE,5,7));
        pieces.add(new Slonyara(1,BLACK,5,0));
        pieces.add(new Slonyara(2,WHITE,2,7));
        pieces.add(new Slonyara(2,BLACK,2,0));
        pieces.add(new Queen(BLACK,3,0));
        pieces.add(new Queen(WHITE,3,7));
        pieces.add(new King(BLACK,4,0));
        pieces.add(new King(WHITE,4,4));
    }
    private void copyPiece(ArrayList<piece> src,ArrayList<piece>tgt){
        tgt.clear();
        for(int i=0;i< src.size();i++){
            tgt.add(src.get(i));
        }
    }
    public static final int WHITE=0;
    public static final int BLACK=1;
    int currColor = 0;



    public Panel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setPieces();
        copyPiece(pieces,simPieces);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
    }

    private void update()
    {
        if(mouse.pressed)
        {
            if(activeP==null)
            {
                for(piece a:simPieces)
                {
                    if(a.color==currColor &&
                            a.col== mouse.x/Borda.SQUARE_SIZE &&
                            a.row== mouse.y/Borda.SQUARE_SIZE){
                        activeP=a;

                    }
                }
            }
            else simulate();
        }
        if(!mouse.pressed)
        {
            if(activeP!=null)
            {
                if(validSquare)
                {
                   copyPiece(simPieces,pieces);
                    activeP.updPos();
                }
                else
                {
                   copyPiece(pieces,simPieces);
                    activeP.reset();
                    activeP=null;
                }
            }
        }
    }




    private void simulate(){

    activeP.x = mouse.x-Borda.HALF_SQUARE_SIZE;
    activeP.y = mouse.y-Borda.HALF_SQUARE_SIZE;
    activeP.row = activeP.getRow(activeP.y);
    activeP.col = activeP.getCol(activeP.x);


    if(activeP.canMove(activeP.col,activeP.row))
    {
        canMove=true;
        if(activeP.Colliding!=null)
        {
            simPieces.remove(activeP.Colliding.getIndex());
        }
        validSquare=true;
    }
    else
    {
        canMove = false;
        validSquare=false;
    }
    }



    public void launch(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        borda.draw(g2);
        for(piece p : simPieces){
            p.draw(g2);
        }
        if(activeP!=null)
        {
            if(canMove) {
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.col * Borda.SQUARE_SIZE, activeP.row * Borda.SQUARE_SIZE, Borda.SQUARE_SIZE, Borda.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            }
            activeP.draw(g2);
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long curTime;
        while (gameThread!=null) {
            curTime = System.nanoTime();
            delta+=(curTime-lastTime)/drawInterval;
            if(delta>=1){
                update();
                repaint();
                delta--;
            }
        }
    }
}
