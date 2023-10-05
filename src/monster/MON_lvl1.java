package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class MON_lvl1 extends Entity {

    public MON_lvl1(GamePanel gp){
        super(gp);

        type = 2;

        direction = "left";
        speed = 2;

        // MON_lvl1 STATUS
        maxLife = 3;
        life = maxLife;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMON_lvl1Image();
    }

    public void getMON_lvl1Image()
    {
        try
        {
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_left9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_right9.png"));

            mon_lvl1_dead1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying1.png"));
            mon_lvl1_dead2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying2.png"));
            mon_lvl1_dead3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying3.png"));
            mon_lvl1_dead4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying4.png"));
            mon_lvl1_dead5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying5.png"));
            mon_lvl1_dead6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl1/monster_lvl1_dying6.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // alegem un nr 1-100

            if(i <= 50){
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
