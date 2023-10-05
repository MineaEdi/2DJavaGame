package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sword extends SuperObject
{
    public OBJ_Sword()
    {
        name = "Sword";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boy_sword_lvl1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
