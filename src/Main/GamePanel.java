package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16 tile
    final int scale = 3; // 16x3

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 24;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeigth = tileSize * maxScreenRow;


    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxScreenRow;
    int FPS = 60;

    KeyHandler kH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,kH);
    TileManager tileM = new TileManager(this);


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

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
