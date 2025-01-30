import entity.player.Player;
import main.GamePanel;
import object.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    GamePanel gamePanel = new GamePanel();
    Player player = gamePanel.player;

    SuperObject key = new OBJ_Key();
    SuperObject boots = new OBJ_Boots();
    SuperObject door = new OBJ_Door();
    SuperObject chest = new OBJ_Chest();

    @BeforeEach
    public void testSetup(){
        gamePanel = new GamePanel();
        gamePanel.setupGame();
        gamePanel.obj = new SuperObject[10];
        player = gamePanel.player;

        gamePanel.obj[0] = key;
        gamePanel.obj[1] = key;
        gamePanel.obj[2] = door;
        gamePanel.obj[3] = door;
        gamePanel.obj[4] = boots;
        gamePanel.obj[5] = chest;
    }

    @Test
    public void testKeyIncrease(){
        SuperObject key = new OBJ_Key();
        gamePanel.obj[0] = key;
        assertEquals(0, player.keys);
        player.interactObject(0);
        assertEquals(1, player.keys);
        player.keys = 99;
        gamePanel.obj[0] = key;
        player.interactObject(0);
        assertEquals(100, player.keys);
    }

    @Test
    public void testKeyUse(){
        player.keys = 1;
        player.interactObject(2);
        assertEquals(0,player.keys);
        assertNull(gamePanel.obj[2]);
        player.interactObject(3);
        assertNotNull(gamePanel.obj[3]);
        assertEquals(0,player.keys);
        player.keys = 10;
        assertEquals(10,player.keys);
        player.interactObject(3);
        assertNull(gamePanel.obj[3]);
        assertEquals(9,player.keys);

    }

    @Test
    public void testUseBoots(){
        assertEquals(4,player.speed);
        player.interactObject(4);
        assertEquals(6,player.speed);
    }

    @Test
    public void testWin(){
        assertFalse(gamePanel.ui.gameFinished);
        player.interactObject(5);
        assertTrue(gamePanel.ui.gameFinished);
    }
}
