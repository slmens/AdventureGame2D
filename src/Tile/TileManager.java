package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[18];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap("/maps/mapMain.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spr_grass_tileset_34.png"));
            tile[0].width = gp.tileSize;
            tile[0].heigth = gp.tileSize;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spr_grass_tileset_275.png"));
            tile[1].collision = true;
            tile[1].width = gp.tileSize;
            tile[1].heigth = gp.tileSize;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/019.png"));
            tile[2].collision = true;
            tile[2].width = gp.tileSize;
            tile[2].heigth = gp.tileSize;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[3].collision = true;
            tile[3].width = gp.tileSize;
            tile[3].heigth = gp.tileSize;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
            tile[4].width = gp.tileSize;
            tile[4].heigth = gp.tileSize;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road02.png"));
            tile[5].width = gp.tileSize;
            tile[5].heigth = gp.tileSize;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road07.png"));
            tile[6].width = gp.tileSize;
            tile[6].heigth = gp.tileSize;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road04 - Kopya (2).png"));
            tile[7].width = gp.tileSize;
            tile[7].heigth = gp.tileSize;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road04 - Kopya (3).png"));
            tile[8].width = gp.tileSize;
            tile[8].heigth = gp.tileSize;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road08.png"));
            tile[9].width = gp.tileSize;
            tile[9].heigth = gp.tileSize;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road06.png"));
            tile[10].width = gp.tileSize;
            tile[10].heigth = gp.tileSize;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road01.png"));
            tile[11].width = gp.tileSize;
            tile[11].heigth = gp.tileSize;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road03.png"));
            tile[12].width = gp.tileSize;
            tile[12].heigth = gp.tileSize;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sagNormalCimSuKenari.png"));
            tile[13].width = gp.tileSize;
            tile[13].heigth = gp.tileSize;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sagYapraklıCimSuKenari.png"));
            tile[14].width = gp.tileSize;
            tile[14].heigth = gp.tileSize;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/solNormalCimSuKenari.png"));
            tile[15].width = gp.tileSize;
            tile[15].heigth = gp.tileSize;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/solYapraklıCimSuKenari.png"));
            tile[16].width = gp.tileSize;
            tile[16].heigth = gp.tileSize;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[17].collision = true;
            tile[17].width = gp.tileSize;
            tile[17].heigth = gp.tileSize;



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int loadMapCol = 0,loadMapRow = 0;
            while (loadMapCol < gp.maxWorldCol && loadMapRow < gp.maxWorldRow){
                String line = br.readLine();

                while (loadMapCol < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[loadMapCol]);

                    mapTileNum[loadMapRow][loadMapCol] = num;
                    loadMapCol++;
                }
                if (loadMapCol == gp.maxWorldCol){
                    loadMapCol = 0;
                    loadMapRow++;
                }
            }

            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0,worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldRow][worldCol];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX -gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }


            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
