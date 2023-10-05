package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class MON_lvl2 extends Entity {

    public MON_lvl2(GamePanel gp){
        super(gp);

        type = 2;

        direction = "left";
        speed = 3;

        // MON_lvl1 STATUS
        maxLife = 4;
        life = maxLife;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMON_lvl2Image();
    }

    public void getMON_lvl2Image()
    {
        try
        {
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_left_9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_right_9.png"));

            mon_lvl2_dead1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_1.png"));
            mon_lvl2_dead2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_2.png"));
            mon_lvl2_dead3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_3.png"));
            mon_lvl2_dead4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_4.png"));
            mon_lvl2_dead5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_5.png"));
            mon_lvl2_dead6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl2/monster_lvl2_dying_6.png"));

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
