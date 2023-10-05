package monster;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class MON_lvl3 extends Entity {

    public MON_lvl3(GamePanel gp){
        super(gp);

        type = 2;

        direction = "left";
        speed = 2;

        // MON_lvl1 STATUS
        maxLife = 5;
        life = maxLife;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMON_lvl3Image();
    }

    public void getMON_lvl3Image()
    {
        try
        {
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_left_9.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_8.png"));
            up9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_up_9.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_8.png"));
            down9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_down_9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_right_9.png"));

            mon_lvl3_dead1 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_1.png"));
            mon_lvl3_dead2 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_2.png"));
            mon_lvl3_dead3 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_3.png"));
            mon_lvl3_dead4 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_4.png"));
            mon_lvl3_dead5 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_5.png"));
            mon_lvl3_dead6 = ImageIO.read(getClass().getResourceAsStream("/monster_lvl3/monster_lvl3_dying_6.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

//    public void update(){
//        super.update();
//
//        int xDistance = Math.abs(x - gp.player.x);
//        int yDistance = Math.abs(y - gp.player.y);
//        int tileDistance = (xDistance + yDistance) / gp.tileSize;
//
//        if(onPath == false && tileDistance < 10){
//            int i = new Random().nextInt(100) + 1;
//            if(i > 50){
//                onPath = true;
//            }
//        }
//    }

    public void setAction(){

        int xDistance = Math.abs(x - gp.player.x);
        int yDistance = Math.abs(y - gp.player.y);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if(onPath == false && tileDistance < 10){
            int i = new Random().nextInt(100) + 1;
            if(i > 50){
                onPath = true;
            }
        }

        if(onPath == true){
            int goalCol;
            int goalRow;
            goalCol = (gp.player.x + gp.player.solidArea.x) / gp.tileSize;
            goalRow = (gp.player.y + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // alegem un nr 1-100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }
}
