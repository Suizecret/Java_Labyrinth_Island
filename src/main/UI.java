package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean gameFinished = false;

    public UI(GamePanel gp){

        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN,40);
        arial_80B = new Font("Arial", Font.BOLD,80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

    }
    public void draw(Graphics2D g2){

        if (gameFinished){

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text ="You found the Treasure!";
            textLength =  (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);


            text ="Congratulation!";
            textLength =  (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(text,x,y);

            gp.gameThread = null;
        }else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x " + gp.player.keys, 74,65);
        }

    }
}
