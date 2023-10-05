package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coins extends SuperObject
{
    public OBJ_Coins()
    {
        name = "Coin";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
