package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{


    public OBJ_Chest() {

        name =  "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/graphics/objects/chest_silver.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
