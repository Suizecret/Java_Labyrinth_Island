package entity.player;

import entity.Entity;
import main.Direction;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int keys = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY= solidArea.y;
        solidArea.width =32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gp.tileSize*20;
        worldY = gp.tileSize*37;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/up3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/up4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/down3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/down4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/left4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/right3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/graphics/player/normal/right4.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed ){
            if (keyH.upPressed){
                direction = Direction.UP;
            } else if (keyH.downPressed) {
                direction = Direction.DOWN;
            } else if (keyH.leftPressed) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this,true);
            if (objIndex != -1){
                interactObject(objIndex);
            }

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn){
                switch (direction){
                    case Direction.UP: worldY -= speed; break;
                    case Direction.DOWN: worldY += speed; break;
                    case Direction.LEFT:worldX -= speed;break;
                    case Direction.RIGHT: worldX += speed;break;
                }
            }

            spriteCounter ++;
            if (spriteCounter > 8){
                spriteNum++;
                if (spriteNum  > 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactObject(int objIndex){
        switch (gp.obj[objIndex].name){
            case "Key"->{
                keys++;
                gp.obj[objIndex] = null;
                gp.playSE(1);
            }
            case "Door"->{
                if(keys > 0){
                    keys--;
                    gp.obj[objIndex] = null;
                    gp.playSE(2);
                }
            }
            case "Boots"->{
                gp.obj[objIndex] = null;
                speed +=2;
                gp.playSE(1);
            }
            case "Chest"->{
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(3);
                gp.obj[objIndex] = null;
            }
            default->
                throw new IllegalStateException("Unexpected value: " + gp.obj[objIndex].name);
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case Direction.UP -> {
                switch (spriteNum) {
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                    case 3 -> image = up3;
                    case 4 -> image = up4;
                }
            }
            case Direction.DOWN -> {
                switch (spriteNum){
                    case 1 -> image = down1;
                    case 2 -> image = down2;
                    case 3 -> image = down3;
                    case 4 -> image = down4;
                }
            }
            case Direction.LEFT -> {
                switch (spriteNum){
                    case 1 -> image = left1;
                    case 2 -> image = left2;
                    case 3 -> image = left3;
                    case 4 -> image = left4;
                }
            }
            case Direction.RIGHT -> {
                switch (spriteNum) {
                    case 1 -> image = right1;
                    case 2 -> image = right2;
                    case 3 -> image = right3;
                    case 4 -> image = right4;
                }
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);

    }
}
