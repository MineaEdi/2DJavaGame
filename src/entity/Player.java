package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UI;
import object.OBJ_Coins;
import object.OBJ_Sword;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Player extends Entity
{
    KeyHandler keyH;
    public boolean hasSword = false;
    public int hasCoins = 0;
    public int numberNpc = 0;
    public ArrayList<SuperObject> inventory = new ArrayList<>();
    public final int maxInventorySize = 8;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp); // apelam constructorul din Entity

        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerDyingImage();
        setItems();
    }
    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
        type = 0;

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        level = 1;
        attack = 0;
        coin = getCoin();
    }

    public void setDefaultPositions(){
        // cand apasam retry din GAME OVER STATE
        x = 100;
        y = 100;
        direction = "right";
    }

    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }

    public void setItems(){
        inventory.clear();  // avem nevoie de clear in cazul in care resetam jocul
                            // pt a nu avea itemele din jocul anterior
        inventory.add(new OBJ_Sword());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
        inventory.add(new OBJ_Coins());
    }

    public int getCoin(){
        return coin = hasCoins;
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_8.png"));
            up9 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_9.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_8.png"));
            down9 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_9.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_9.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getPlayerAttackImage(){
        try {
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_2.png"));
            attackUp3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_3.png"));
            attackUp4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_4.png"));
            attackUp5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_5.png"));
            attackUp6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_6.png"));

            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_2.png"));
            attackDown3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_3.png"));
            attackDown4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_4.png"));
            attackDown5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_5.png"));
            attackDown6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_6.png"));

            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_2.png"));
            attackLeft3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_3.png"));
            attackLeft4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_4.png"));
            attackLeft5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_5.png"));
            attackLeft6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_6.png"));

            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_2.png"));
            attackRight3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_3.png"));
            attackRight4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_4.png"));
            attackRight5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_5.png"));
            attackRight6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_6.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getPlayerDyingImage(){
        try {
            dead1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_1.png"));
            dead2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_2.png"));
            dead3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_3.png"));
            dead4 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_4.png"));
            dead5 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_5.png"));
            dead6 = ImageIO.read(getClass().getResourceAsStream("/player/boy_dying_6.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(gp.currentMap == 0) {
            if (keyH.spacePressed == true && hasCoins >= 25 && numberNpc < 3) {
                if (keyH.spaceRel == false) {
                    numberNpc++;
                    gp.aSetter.setNPC1(numberNpc);
                    hasCoins -= 25;
                    keyH.spaceRel = true;
                    gp.aSetter.setMonster1(numberNpc);
                }
            }
        }

        if(gp.currentMap == 1) {
            if (keyH.spacePressed == true && hasCoins >= 25 && numberNpc < 7) {
                if (keyH.spaceRel == false) {
                    numberNpc++;
                    gp.aSetter.setNPC1(numberNpc);
                    hasCoins -= 25;
                    keyH.spaceRel = true;
                    gp.aSetter.setMonster1(numberNpc);
                }
            }
        }

        if(gp.currentMap == 2) {
            if (keyH.spacePressed == true && hasCoins >= 25 && numberNpc < 12) {
                if (keyH.spaceRel == false) {
                    numberNpc++;
                    gp.aSetter.setNPC1(numberNpc);
                    hasCoins -= 25;
                    keyH.spaceRel = true;
                    gp.aSetter.setMonster1(numberNpc);
                }
            }
        }

        //if(gp.monstersDead == 183) {
            //gp.aSetter.setBoss1();
        //}

        if(attacking == true) {
            attacking();
        }

        if(dying == true) {
            dying();
        }

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC1 COLLISION
            int npc1Index = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC1(npc1Index);

            // CHECK MONSTERlvl1 COLLISION
            int monster1Index = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monster1Index);
            if(gp.player.life == 0){
                dying();
                UI.gameFinished = true;
            }

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            // IF COLLISION IS FALSE , PLAYER CAN MOVE
            if(collisionOn == false)
            {
                switch(direction)
                {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10)
            {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 7;
                } else if (spriteNum == 7) {
                    spriteNum = 8;
                } else if (spriteNum == 8) {
                    spriteNum = 9;
                } else if (spriteNum == 9) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 120) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
        }

    }

    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 10){
            spriteNum = 1;

            // Salvam x, y si zona solida
            int currentWorldX = x;
            int currentWorldY = y;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Ajustam x si y pentru zona de atac
            switch (direction){
                case "up": y -= attackArea.height; break;
                case "down": y += attackArea.height; break;
                case "left": x -= attackArea.width; break;
                case "right": x += attackArea.width; break;
            }

            // zona de atac devine zona solida
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // verificam coliziunea cu monstri cu x, y si zona solida updatate
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

//            if(gp.boss1[gp.currentMap][0] != null) {
//                int bossIndex = gp.cChecker.checkEntity(this, gp.monster1);
//                damageBoss(bossIndex);
//            }

            // dupa ce verificam coliziunea, restauram valorile
            x = currentWorldX;
            y = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 10 && spriteCounter <= 20){
            spriteNum = 2;
        }
        if(spriteCounter > 20 && spriteCounter <= 30){
            spriteNum = 3;
        }
        if(spriteCounter > 30 && spriteCounter <= 40){
            spriteNum = 4;
        }
        if(spriteCounter > 40 && spriteCounter <= 50){
            spriteNum = 5;
        }
        if(spriteCounter > 50){
            spriteNum = 6;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void dying(){
        spriteCounter++;

        if(spriteCounter <= 10){
            spriteNum = 1;
        }
        if(spriteCounter > 10 && spriteCounter <= 20){
            spriteNum = 2;
        }
        if(spriteCounter > 20 && spriteCounter <= 30){
            spriteNum = 3;
        }
        if(spriteCounter > 30 && spriteCounter <= 40){
            spriteNum = 4;
        }
        if(spriteCounter > 40 && spriteCounter <= 50){
            spriteNum = 5;
        }
        if(spriteCounter > 50){
            spriteNum = 6;
            spriteCounter = 0;
            dying = false;
        }
    }

    public void pickUpObject(int i)
    {
        // 999 e o valoare de referinta
        // daca i!=999 inseamna ca am atins un obiect
        // daca i =999 inseamna ca nu am atins vreun obiect
        if(i != 999) {
            String objectName = gp.obj[gp.currentMap][i].name;

            switch (objectName)
            {
                case "Sword":
                    hasSword = true;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Ai ridicat toporul!");
                    attack = 1;
                    break;
                case "Coin":
                    hasCoins += 25;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("+ 25 GOLD");
                    try {
                        keyH.AddLife_CoinsDB(gp.player.life, gp.player.hasCoins, gp.ui.playTime);
                    } catch ( SQLException ae ) {
                        System.out.println("Error adding life + coins");
                    }
                    break;
                case "Potion":
                    life += 2;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("+ 2 HP");
                    try {
                        keyH.AddLife_CoinsDB(gp.player.life, gp.player.hasCoins, gp.ui.playTime);
                    } catch ( SQLException ae ) {
                        System.out.println("Error adding life + coins");
                    }
                    break;
                case "Potion1":
                    life += 1;
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("+ 1 HP");
                    try {
                        keyH.AddLife_CoinsDB(gp.player.life, gp.player.hasCoins, gp.ui.playTime);
                    } catch ( SQLException ae ) {
                        System.out.println("Error adding life + coins");
                    }
                    break;
            }
        }
    }

    public void interactNPC1(int i)
    {
        if(i != 999)
        {
            if(gp.keyH.enterPressed == true)
            {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
            System.out.println("intri in npc");
        }
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false) {
                life -= 1;
                invincible = true;
            }
            if(life <= 0){
                dying = true;
            }
        }
    }

    public void damageMonster(int i){
        if(i != 999) {
            if(gp.monster[gp.currentMap][i].invincible == false && hasSword == true){
                gp.monster[gp.currentMap][i].life -= 1;
                gp.monster[gp.currentMap][i].invincible = true;

                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                }
            }
        }
    }

//    public void damageBoss(int i){
//        if(i != 999) {
//            if(gp.boss1[gp.currentMap][i].invincible == false && hasSword == true){
//                gp.boss1[gp.currentMap][i].life -= 1;
//                gp.boss1[gp.currentMap][i].invincible = true;
//
//                if(gp.boss1[gp.currentMap][i].life <= 0){
//                    gp.boss1[gp.currentMap][i].dying = true;
//                }
//            }
//        }
//    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if((attacking == false && hasSword == false) || (attacking == false && hasSword == true) || (attacking == true && hasSword == false)){
                    if(spriteNum == 1) { image = up1; }
                    if(spriteNum == 2) { image = up2; }
                    if(spriteNum == 3) { image = up3; }
                    if(spriteNum == 4) { image = up4; }
                    if(spriteNum == 5) { image = up5; }
                    if(spriteNum == 6) { image = up6; }
                    if(spriteNum == 7) { image = up7; }
                    if(spriteNum == 8) { image = up8; }
                    if(spriteNum == 9) { image = up9; }
                }
                if(attacking == true && hasSword == true){
                    if(spriteNum == 1) { image = attackUp1; }
                    if(spriteNum == 2) { image = attackUp2; }
                    if(spriteNum == 3) { image = attackUp3; }
                    if(spriteNum == 4) { image = attackUp4; }
                    if(spriteNum == 5) { image = attackUp5; }
                    if(spriteNum == 6) { image = attackUp6; }
                }
                if(dying == true){
                    if(spriteNum == 1) { image = dead1; }
                    if(spriteNum == 2) { image = dead2; }
                    if(spriteNum == 3) { image = dead3; }
                    if(spriteNum == 4) { image = dead4; }
                    if(spriteNum == 5) { image = dead5; }
                    if(spriteNum == 6) { image = dead6; }
                }
                break;
            case "down":
                if((attacking == false && hasSword == false) || (attacking == false && hasSword == true) || (attacking == true && hasSword == false)){
                    if(spriteNum == 1) { image = down1; }
                    if(spriteNum == 2) { image = down2; }
                    if(spriteNum == 3) { image = down3; }
                    if(spriteNum == 4) { image = down4; }
                    if(spriteNum == 5) { image = down5; }
                    if(spriteNum == 6) { image = down6; }
                    if(spriteNum == 7) { image = down7; }
                    if(spriteNum == 8) { image = down8; }
                    if(spriteNum == 9) { image = down9; }
                }
                if(attacking == true && hasSword == true){
                    if(spriteNum == 1) { image = attackDown1; }
                    if(spriteNum == 2) { image = attackDown2; }
                    if(spriteNum == 3) { image = attackDown3; }
                    if(spriteNum == 4) { image = attackDown4; }
                    if(spriteNum == 5) { image = attackDown5; }
                    if(spriteNum == 6) { image = attackDown6; }
                }
                if(dying == true){
                    if(spriteNum == 1) { image = dead1; }
                    if(spriteNum == 2) { image = dead2; }
                    if(spriteNum == 3) { image = dead3; }
                    if(spriteNum == 4) { image = dead4; }
                    if(spriteNum == 5) { image = dead5; }
                    if(spriteNum == 6) { image = dead6; }
                }
                break;
            case "left":
                if((attacking == false && hasSword == false) || (attacking == false && hasSword == true) || (attacking == true && hasSword == false)){
                    if(spriteNum == 1) { image = left1; }
                    if(spriteNum == 2) { image = left2; }
                    if(spriteNum == 3) { image = left3; }
                    if(spriteNum == 4) { image = left4; }
                    if(spriteNum == 5) { image = left5; }
                    if(spriteNum == 6) { image = left6; }
                    if(spriteNum == 7) { image = left7; }
                    if(spriteNum == 8) { image = left8; }
                    if(spriteNum == 9) { image = left9; }
                }
                if(attacking == true && hasSword == true){
                    if(spriteNum == 1) { image = attackLeft1; }
                    if(spriteNum == 2) { image = attackLeft2; }
                    if(spriteNum == 3) { image = attackLeft3; }
                    if(spriteNum == 4) { image = attackLeft4; }
                    if(spriteNum == 5) { image = attackLeft5; }
                    if(spriteNum == 6) { image = attackLeft6; }
                }
                if(dying == true){
                    if(spriteNum == 1) { image = dead1; }
                    if(spriteNum == 2) { image = dead2; }
                    if(spriteNum == 3) { image = dead3; }
                    if(spriteNum == 4) { image = dead4; }
                    if(spriteNum == 5) { image = dead5; }
                    if(spriteNum == 6) { image = dead6; }
                }
                break;
            case "right":
                if((attacking == false && hasSword == false) || (attacking == false && hasSword == true) || (attacking == true && hasSword == false)){
                    if(spriteNum == 1) { image = right1; }
                    if(spriteNum == 2) { image = right2; }
                    if(spriteNum == 3) { image = right3; }
                    if(spriteNum == 4) { image = right4; }
                    if(spriteNum == 5) { image = right5; }
                    if(spriteNum == 6) { image = right6; }
                    if(spriteNum == 7) { image = right7; }
                    if(spriteNum == 8) { image = right8; }
                    if(spriteNum == 9) { image = right9; }
                }
                if(attacking == true && hasSword == true){
                    if(spriteNum == 1) { image = attackRight1; }
                    if(spriteNum == 2) { image = attackRight2; }
                    if(spriteNum == 3) { image = attackRight3; }
                    if(spriteNum == 4) { image = attackRight4; }
                    if(spriteNum == 5) { image = attackRight5; }
                    if(spriteNum == 6) { image = attackRight6; }
                }
                if(dying == true){
                    if(spriteNum == 1) { image = dead1; }
                    if(spriteNum == 2) { image = dead2; }
                    if(spriteNum == 3) { image = dead3; }
                    if(spriteNum == 4) { image = dead4; }
                    if(spriteNum == 5) { image = dead5; }
                    if(spriteNum == 6) { image = dead6; }
                }
                break;
        }
        g2.drawImage(image, x, y, 55, 55, null);
    }
}
