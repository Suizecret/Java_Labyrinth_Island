package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case Direction.UP:{
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            }
            case Direction.DOWN:{
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            }
            case Direction.LEFT:{
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            }
            case Direction.RIGHT:{
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            }
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = -1;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                SuperObject object = gp.obj[i];
                if (object != null) {
                    entity.solidArea.x += entity.worldX;
                    entity.solidArea.y += entity.worldY;

                    object.solidArea.x += object.worldX;
                    object.solidArea.y += object.worldY;

                    switch (entity.direction) {
                        case UP -> {
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(object.solidArea)) {
                                if (object.collision) {
                                    entity.collisionOn = true;
                                }
                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        case DOWN -> {
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(object.solidArea)) {
                                if (object.collision) {
                                    entity.collisionOn = true;
                                }
                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        case LEFT -> {
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(object.solidArea)) {
                                if (object.collision) {
                                    entity.collisionOn = true;
                                }
                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        case RIGHT -> {
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(object.solidArea)) {
                                if (object.collision) {
                                    entity.collisionOn = true;
                                }
                                if (player) {
                                    index = i;
                                }
                            }
                        }
                    }
                    entity.resetSolidArea();
                    object.resetSolidArea();
                }
            }
        }
        return index;
    }
}
