package Main;
import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;
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
        pieces.add(new King(WHITE,4,7));
    }
    private void copyPiece(ArrayList<piece> src,ArrayList<piece>tgt){
        tgt.clear();
        tgt.addAll(src);
    }
    public static final int WHITE=0;
    public static final int BLACK=1;
    int currColor = 0;



    public Panel()
    {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setPieces();
        copyPiece(pieces,simPieces);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
    }


    private void turn()
    {
        if(currColor==1)
            currColor--;
        else currColor++;
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
                            a.row== mouse.y/Borda.SQUARE_SIZE)
                    {
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
                    turn();

                }
                else
                {
                   copyPiece(pieces,simPieces);
                    activeP.reset();
                }
                activeP=null;
            }
        }
    }




    private void simulate(){
        copyPiece(simPieces,pieces);
        copyPiece(simPieces,pieces);
    activeP.x = mouse.x-Borda.HALF_SQUARE_SIZE;
    activeP.y = mouse.y-Borda.HALF_SQUARE_SIZE;
    activeP.row = activeP.getRow(activeP.y);
    activeP.col = activeP.getCol(activeP.x);


    if(activeP.canMove(activeP.col,activeP.row))
    {
        canMove=true;
        if(activeP.Colliding!=null)
        {
            if(!mouse.pressed)
            {
                simPieces.remove(activeP.Colliding.getIndex());
                if(activeP.Colliding instanceof King)
                {
                    if(currColor==1)
                        currColor=2;

                    if(currColor==0)
                        currColor=3;
                }
            }
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
            if(canMove)
            {
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.col * Borda.SQUARE_SIZE, activeP.row * Borda.SQUARE_SIZE, Borda.SQUARE_SIZE, Borda.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                activeP.draw(g2);

            }
            //activeP.draw(g2);

        }
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Comic Sans",Font.PLAIN,50));
        g2.setColor(Color.white);

        if(currColor==0)
            g2.drawString("Ходят белые",250,850);
        else if(currColor==1)
            g2.drawString("Ходят чёрные",250,850);
        else if(currColor==2)
            g2.drawString("Чёрные победили!",200,850);
        else g2.drawString("Белые победили!",200,850);

    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000/fps;
        double delta = 0.6;
        long lastTime = System.nanoTime();
        long curTime;
        while (gameThread!=null)
        {
            curTime = System.nanoTime();
            delta+=(curTime-lastTime)/drawInterval;
            if(delta>=1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }

}
