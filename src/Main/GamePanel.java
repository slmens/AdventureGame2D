package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 24;
    final int maxScreenRow = 18;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeigth = tileSize * maxScreenRow;
    int FPS = 60;

    KeyHandler kH = new KeyHandler();
    Thread gameThread;

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
        while (gameThread != null){
            double drawInterval = (double) 1000000000 /FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;


            long currentTime = System.nanoTime();
            // Update the information
            update();
            // Redraw the screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if (kH.upPressed){
            playerY -= playerSpeed;
        } else if (kH.downPressed) {
            playerY += playerSpeed;
        }
        else if (kH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (kH.rightPressed) {
            playerX += playerSpeed;
        }


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
