package xiongtu.battle;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BattleClient extends Frame {

    public static final int GAME_WIDTH=1600;
    public static final int GAME_HEIGTH=1600;

    int x=50,y=50;

    Image image=null;

    public void update(Graphics g){
        if(image==null){
            image=this.createImage(GAME_WIDTH,GAME_HEIGTH);
        }
        Graphics graphics=image.getGraphics();
//        Color color=graphics.getColor();
        graphics.setColor(Color.blue);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGTH);
        paint(graphics);
        g.drawImage(image,0,0,null);
    }

    public void paint(Graphics graphics){
//        Color color=graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(x,y,100,100);
//        graphics.setColor(color);
        new Thread(new PaintThread()).start();
    }

    public void lauchFrame(){
        this.setLocation(400,300);
        this.setSize(GAME_WIDTH,GAME_HEIGTH);
        this.setTitle("BattleClient");
        this.setBackground(Color.blue);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyMonitor());
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String [] args){
        BattleClient battleClient=new BattleClient();
        battleClient.lauchFrame();
    }

    private class PaintThread implements Runnable{

        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_RIGHT :
                    x+=5;
                    break;
                case KeyEvent.VK_UP :
                    y-=5;
                    break;
                case KeyEvent.VK_LEFT :
                    x-=5;
                    break;
                case KeyEvent.VK_DOWN :
                    y+=5;
                    break;
            }
        }
    }
}
