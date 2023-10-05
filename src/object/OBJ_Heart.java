package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject
{
    public OBJ_Heart(GamePanel gp)
    {
        name = "Heart";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
