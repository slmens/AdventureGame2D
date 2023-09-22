package Main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 32; // 16x16 tile
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 24;
    final int maxScreenRow = 18;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeigth = tileSize * maxScreenRow;
    int FPS = 60;

    KeyHandler kH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,kH);

    int playerX = 100,playerY = 100,playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if ( delta >= 1){
                // Update the information
                update();
                // Redraw the screen
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }
}
