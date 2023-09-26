package Main;

import Entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void canMoveHere(Entity entity,float x,float y,int width,int heigth){

        switch (entity.direction){
            case "up":
                if (isSolid(x,y) == false){
                    if (isSolid(x + gp.tileSize,y) == false){
                        entity.collisionOn = false;
                    }else {
                        entity.collisionOn = true;
                    }
                }else {
                    entity.collisionOn = true;
                }
            case "down":
                if (isSolid(x,y + gp.tileSize) == false){
                    if (isSolid(x + gp.tileSize,y+gp.tileSize) == false){
                        entity.collisionOn = false;
                    }else {
                        entity.collisionOn = true;
                    }
                }else {
                    entity.collisionOn = true;
                }
            case "right":
                if (isSolid(x + gp.tileSize,y) == false){
                    if (isSolid(x + gp.tileSize,y+gp.tileSize) == false){
                        entity.collisionOn = false;
                    }else {
                        entity.collisionOn = true;
                    }
                }else {
                    entity.collisionOn = true;
                }
            case "left":
                if (isSolid(x,y) == false){
                    if (isSolid(x,y+gp.tileSize) == false){
                        entity.collisionOn = false;
                    }else {
                        entity.collisionOn = true;
                    }
                }else {
                    entity.collisionOn = true;
                }

        }
    }

    public boolean isSolid(float x, float y){
        int pseudoX = (int) (x / gp.tileSize);
        int pseudoY = (int) (y / gp.tileSize);
        int tileNum1 = gp.tileM.mapTileNum[pseudoX][pseudoY];
        boolean value = gp.tileM.tile[tileNum1].collision;

        /*if (x < 0 || x > gp.worldWidth || y < 0 || y > gp.worldHeight){
            System.out.println("oha");
            return true;
        }*/

        if (value){
            System.out.println("Bu kayadır");
            return true;
        }else {
            return false;
        }
/*
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRigthWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRigthCol = entityRigthWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1,tileNum2;

        if (entity.intersects(gp.tileM.tile[0]) || entity.solidArea.intersects(gp.tileM.tile[2]) || entity.solidArea.intersects(gp.tileM.tile[3]) || entity.solidArea.intersects(gp.tileM.tile[17])){
            entity.collisionOn = true;
            System.out.println("intersex");
        }

        /*switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityTopRow];
                if (gp.player.solidArea.intersects(gp.tileM.tile[tileNum1].collision)  || gp.tileM.tile[tileNum2].collision ){
                    entity.collisionOn = true;
                }
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
            case "right":
                entityRigthCol = (entityRigthWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRigthCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

        }*/
    }
}
