package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gP;
    KeyHandler kH;
    public final int screenX,screenY;

    public Player(GamePanel gP, KeyHandler kH) {
        this.gP = gP;
        this.kH = kH;

        screenX = gP.screenWidth / 2 - (gP.tileSize/2);
        screenY = gP.screenHeigth / 2 - (gP.tileSize/2);

        solidArea = new Rectangle(8,16,32,32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gP.tileSize * 32;
        worldY = gP.tileSize * 30;
        speed = 4;
        direction = "down";
        lastDirection = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/up2walking.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/up3walking.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/up1standing.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/down2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/down4.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/down1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/left4.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/left1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/right4.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/playerLizard/right1.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if (kH.upPressed || kH.rightPressed ||  kH.leftPressed || kH.downPressed){
            if (lastSpriteNum == 1){
                spriteNum = 2;
            } else if (lastSpriteNum == 2) {
                spriteNum = 1;
            }
            if (kH.upPressed){
                direction = "up";
                lastDirection = "up";
                worldY -= speed;
            } else if (kH.downPressed) {
                direction = "down";
                lastDirection = "down";
                worldY += speed;
            }
            else if (kH.leftPressed) {
                direction = "left";
                lastDirection = "left";
                worldX -= speed;
            } else if (kH.rightPressed) {
                direction = "right";
                lastDirection = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                    lastSpriteNum = 1;

                } else if (spriteNum == 2) {
                    spriteNum = 1;
                    lastSpriteNum = 2;
                }
                spriteCounter = 0;
            }
        }else if(kH.upPressed == false && kH.rightPressed == false &&  kH.leftPressed == false && kH.downPressed == false) {
            if (Objects.equals(lastDirection, "up") || Objects.equals(lastDirection, "down") || Objects.equals(lastDirection, "right") || Objects.equals(lastDirection, "left")){
                spriteNum = 3;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else if (Objects.equals(lastDirection, "up") && spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }else if (Objects.equals(lastDirection, "down") && spriteNum == 3) {
                    image = down3;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }else if (Objects.equals(lastDirection, "right") && spriteNum == 3) {
                    image = right3;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }else if (spriteNum == 2) {
                    image = left2;
                }else if (Objects.equals(lastDirection, "left") && spriteNum == 3) {
                    image = left3;
                }
                break;
        }

        g2.drawImage(image,screenX,screenY, gP.tileSize,gP.tileSize,null);
    }


}
