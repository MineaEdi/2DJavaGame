//package monster;
//
//import entity.Entity;
//import main.GamePanel;
//import main.UtilityTool;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Random;
//
//public class BOSS_lvl1 extends Entity {
//
//    public BOSS_lvl1(GamePanel gp){
//        super(gp);
//
//        type = 3;
//
//        direction = "left";
//        speed = 2;
//
//        // MON_lvl1 STATUS
//        maxLife = 3;
//        life = maxLife;
//
//        solidArea = new Rectangle();
//        solidArea.x = 8;
//        solidArea.y = 16;
//        solidArea.width = 32;
//        solidArea.height = 32;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
//
//        getBOSS_lvl1Image();
//        getBOSS_lvl1AttackImage();
//        getBOSS_lvl1DyingImage();
//    }
//
//    public void getBOSS_lvl1Image(){
//        up1 = setup("boss1_up_1");
//        up2 = setup("boss1_up_2");
//        up3 = setup("boss1_up_3");
//        up4 = setup("boss1_up_4");
//        up5 = setup("boss1_up_5");
//        up6 = setup("boss1_up_6");
//        up7 = setup("boss1_up_7");
//        up8 = setup("boss1_up_8");
//        up9 = setup("boss1_up_9");
//
//        down1 = setup("boss1_down_1");
//        down2 = setup("boss1_down_2");
//        down3 = setup("boss1_down_3");
//        down4 = setup("boss1_down_4");
//        down5 = setup("boss1_down_5");
//        down6 = setup("boss1_down_6");
//        down7 = setup("boss1_down_7");
//        down8 = setup("boss1_down_8");
//        down9 = setup("boss1_down_9");
//
//        left1 = setup("boss1_left_1");
//        left2 = setup("boss1_left_2");
//        left3 = setup("boss1_left_3");
//        left4 = setup("boss1_left_4");
//        left5 = setup("boss1_left_5");
//        left6 = setup("boss1_left_6");
//        left7 = setup("boss1_left_7");
//        left8 = setup("boss1_left_8");
//        left9 = setup("boss1_left_9");
//
//        right1 = setup("boss1_right_1");
//        right2 = setup("boss1_right_2");
//        right3 = setup("boss1_right_3");
//        right4 = setup("boss1_right_4");
//        right5 = setup("boss1_right_5");
//        right6 = setup("boss1_right_6");
//        right7 = setup("boss1_right_7");
//        right8 = setup("boss1_right_8");
//        right9 = setup("boss1_right_9");
//    }
//
//    public void getBOSS_lvl1AttackImage(){
//        attackUp1 = setup("boss1_attack_up_1");
//        attackUp2 = setup("boss1_attack_up_2");
//        attackUp3 = setup("boss1_attack_up_3");
//        attackUp4 = setup("boss1_attack_up_4");
//        attackUp5 = setup("boss1_attack_up_5");
//        attackUp6 = setup("boss1_attack_up_6");
//        attackUp7 = setup("boss1_attack_up_7");
//        attackUp8 = setup("boss1_attack_up_8");
//
//        attackDown1 = setup("boss1_attack_down_1");
//        attackDown2 = setup("boss1_attack_down_2");
//        attackDown3 = setup("boss1_attack_down_3");
//        attackDown4 = setup("boss1_attack_down_4");
//        attackDown5 = setup("boss1_attack_down_5");
//        attackDown6 = setup("boss1_attack_down_6");
//        attackDown7 = setup("boss1_attack_down_7");
//        attackDown8 = setup("boss1_attack_down_8");
//
//        attackLeft1 = setup("boss1_attack_left_1");
//        attackLeft2 = setup("boss1_attack_left_2");
//        attackLeft3 = setup("boss1_attack_left_3");
//        attackLeft4 = setup("boss1_attack_left_4");
//        attackLeft5 = setup("boss1_attack_left_5");
//        attackLeft6 = setup("boss1_attack_left_6");
//        attackLeft7 = setup("boss1_attack_left_7");
//        attackLeft8 = setup("boss1_attack_left_8");
//
//        attackRight1 = setup("boss1_attack_right_1");
//        attackRight2 = setup("boss1_attack_right_2");
//        attackRight3 = setup("boss1_attack_right_3");
//        attackRight4 = setup("boss1_attack_right_4");
//        attackRight5 = setup("boss1_attack_right_5");
//        attackRight6 = setup("boss1_attack_right_6");
//        attackRight7 = setup("boss1_attack_right_7");
//        attackRight8 = setup("boss1_attack_right_8");
//    }
//
//    public void getBOSS_lvl1DyingImage(){
//        dead1 = setup("boss1_dying_1");
//        dead2 = setup("boss1_dying_2");
//        dead3 = setup("boss1_dying_3");
//        dead4 = setup("boss1_dying_4");
//        dead5 = setup("boss1_dying_5");
//        dead6 = setup("boss1_dying_6");
//    }
//
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
//
//    public void setAction(){
//        if(onPath == true){
//            int goalCol;
//            int goalRow;
//            goalCol = (gp.player.x + gp.player.solidArea.x) / gp.tileSize;
//            goalRow = (gp.player.y + gp.player.solidArea.y) / gp.tileSize;
//
//            searchPath(goalCol, goalRow);
//        }
//        else{
//            actionLockCounter++;
//
//            if (actionLockCounter == 120) {
//                Random random = new Random();
//                int i = random.nextInt(100) + 1; // alegem un nr 1-100
//
//                if (i <= 25) {
//                    direction = "up";
//                }
//                if (i > 25 && i <= 50) {
//                    direction = "down";
//                }
//                if (i > 50 && i <= 75) {
//                    direction = "left";
//                }
//                if (i > 75 && i <= 100) {
//                    direction = "right";
//                }
//                actionLockCounter = 0;
//            }
//        }
//    }
//
//    public BufferedImage setup(String imageName){
//        UtilityTool uTool=new UtilityTool();
//        BufferedImage Image=null;
//
//        try{
//            Image= ImageIO.read(new FileInputStream("res/TheJailer_lvl1/"+imageName+".png"));
//            Image=uTool.scaleImage(Image,55,55);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return Image;
//    }
//}
