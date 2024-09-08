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
    public static ArrayList<piece> pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();

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
        pieces.add(new King(WHITE,4,7));
    }
    private void copyPiece(ArrayList<piece> src,ArrayList<piece>tgt){
        tgt.clear();
        for(int i=0;i< src.size();i++){
            tgt.add(src.get(i));
        }
    }
    public static final int WHITE=0;
    public static final int BLACK=1;
    int currColor = WHITE;



    public Panel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setPieces();
        copyPiece(pieces,simPieces);
    }

    private void update(){

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
