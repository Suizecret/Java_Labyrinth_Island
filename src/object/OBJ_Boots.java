package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObject{

    public OBJ_Boots() {

        name =  "Boots";
        collision = true;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/graphics/objects/boots.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
