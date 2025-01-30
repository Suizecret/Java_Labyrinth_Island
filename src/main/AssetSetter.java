package main;

import object.*;

import java.lang.reflect.InvocationTargetException;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        createObject(new OBJ_Key(),7,31);
        createObject(new OBJ_Key(),10,28);
        createObject(new OBJ_Door(),7,27);
        createObject(new OBJ_Door(),7,24);
        createObject(new OBJ_Chest(),7,22);
        createObject(new OBJ_Boots(),20,35);
    }

    private void createObject(SuperObject object,int xTile,int yTile) {
        for (int i = 0; i < gp.obj.length ; i++) {
            if (gp.obj[i] == null){
                gp.obj[i] = object;
                gp.obj[i].worldX = xTile * gp.tileSize;
                gp.obj[i].worldY = yTile * gp.tileSize;
                return;
            }
        }
    }

}
