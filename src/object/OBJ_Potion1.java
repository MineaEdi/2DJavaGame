package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion1 extends SuperObject
{
    public OBJ_Potion1()
    {
        name = "Potion1";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_on_lava.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
