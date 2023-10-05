package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// clasa abstracta pentru ca nu are instanta, o instantiem ca player, npc etc.
public class Entity
{
    public GamePanel gp;

    // WALKING
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8, up9;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8, down9;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8, right9;


    // ATTACKING
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5, attackUp6, attackUp7, attackUp8;
    public BufferedImage attackDown1, attackDown2, attackDown3, attackDown4, attackDown5, attackDown6, attackDown7, attackDown8;
    public BufferedImage attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6, attackLeft7, attackLeft8;
    public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4, attackRight5, attackRight6, attackRight7, attackRight8;


    // DYING
    public BufferedImage dead1, dead2, dead3, dead4, dead5, dead6;
    public BufferedImage npc1_dead1, npc1_dead2, npc1_dead3, npc1_dead4, npc1_dead5, npc1_dead6;
    public BufferedImage npc2_dead1, npc2_dead2, npc2_dead3, npc2_dead4, npc2_dead5, npc2_dead6;
    public BufferedImage npc3_dead1, npc3_dead2, npc3_dead3, npc3_dead4, npc3_dead5, npc3_dead6;
    public BufferedImage mon_lvl1_dead1, mon_lvl1_dead2, mon_lvl1_dead3, mon_lvl1_dead4, mon_lvl1_dead5, mon_lvl1_dead6;
    public BufferedImage mon_lvl2_dead1, mon_lvl2_dead2, mon_lvl2_dead3, mon_lvl2_dead4, mon_lvl2_dead5, mon_lvl2_dead6;
    public BufferedImage mon_lvl3_dead1, mon_lvl3_dead2, mon_lvl3_dead3, mon_lvl3_dead4, mon_lvl3_dead5, mon_lvl3_dead6;



    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String dialogues[] = new String[50];


    // STATE
    public int x, y;
    public String direction;
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public static boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;


    // COUNTERS
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int cnt = 0;
    int cnt1 = 0;


    // CHARACTER ATTRIBUTES
    public int type; // 0 - player, 1 = npc, 2 = monster_lvl1
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int coin;

    // ITEM ATTRIBUTES
    public int attack;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){}

    public void speak()
    {
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        // mutam pozitia npc-ului in asa fel incat atunci cand vorbim cu el sa se uite inspre noi
        switch(gp.player.direction)
        {
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update()
    {
        setAction(); // se executa cea din NPC_1, are prioritate

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        int mon_npc = gp.cChecker.checkEntity(this, gp.npc);
        int npc_mon = gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        if(this.type == 1 && npc_mon != 999){
            cnt++;
            if(cnt == 1){
                gp.monster[gp.currentMap][npc_mon].life -= 1;
                if(gp.monster[gp.currentMap][npc_mon].life <= 0){
                    gp.monster[gp.currentMap][npc_mon].dying = true;
                }
            }
            if(cnt > 20){
                cnt = 0;
            }
        }

        if(this.type == 2 && mon_npc != 999){
            cnt++;
            if(cnt == 1){
                gp.npc[gp.currentMap][mon_npc].life -= 1;
                if(gp.npc[gp.currentMap][mon_npc].life <= 0){
                    gp.npc[gp.currentMap][mon_npc].dying = true;
                }
            }
            if(cnt > 20){
                cnt = 0;
            }
        }

        if(collisionOn == false)
        {
            switch(direction)
            {
                case "left": x -= speed; break;
                case "right": x += speed; break;
                case "up": y -= speed; break;
                case "down": y += speed; break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10) {
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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if(spriteNum == 1) { image = up1; }
                if(spriteNum == 2) { image = up2; }
                if(spriteNum == 3) { image = up3; }
                if(spriteNum == 4) { image = up4; }
                if(spriteNum == 5) { image = up5; }
                if(spriteNum == 6) { image = up6; }
                if(spriteNum == 7) { image = up7; }
                if(spriteNum == 8) { image = up8; }
                if(spriteNum == 9) { image = up9; }
                break;
            case "down":
                if(spriteNum == 1) { image = down1; }
                if(spriteNum == 2) { image = down2; }
                if(spriteNum == 3) { image = down3; }
                if(spriteNum == 4) { image = down4; }
                if(spriteNum == 5) { image = down5; }
                if(spriteNum == 6) { image = down6; }
                if(spriteNum == 7) { image = down7; }
                if(spriteNum == 8) { image = down8; }
                if(spriteNum == 9) { image = down9; }
                break;
            case "left":
                if(spriteNum == 1) { image = left1; }
                if(spriteNum == 2) { image = left2; }
                if(spriteNum == 3) { image = left3; }
                if(spriteNum == 4) { image = left4; }
                if(spriteNum == 5) { image = left5; }
                if(spriteNum == 6) { image = left6; }
                if(spriteNum == 7) { image = left7; }
                if(spriteNum == 8) { image = left8; }
                if(spriteNum == 9) { image = left9; }
                break;
            case "right":
                if(spriteNum == 1) { image = right1; }
                if(spriteNum == 2) { image = right2; }
                if(spriteNum == 3) { image = right3; }
                if(spriteNum == 4) { image = right4; }
                if(spriteNum == 5) { image = right5; }
                if(spriteNum == 6) { image = right6; }
                if(spriteNum == 7) { image = right7; }
                if(spriteNum == 8) { image = right8; }
                if(spriteNum == 9) { image = right9; }
                break;
        }

        // NPC HP BAR
        //if(type == 1 && hpBarOn == true) {
        if(type == 1){
            double oneScale = (double) gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x - 1, y - 16, gp.tileSize + 2, 12);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(x, y - 15, (int) hpBarValue, 10);

            hpBarCounter++;

            if(hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        // MONSTER HP BAR
        //if(type == 2 && hpBarOn == true) {
        if(type == 2){
            double oneScale = (double) gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x - 1, y - 16, gp.tileSize + 2, 12);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(x, y - 15, (int)hpBarValue, 10);

            hpBarCounter++;

            if(hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }


        if(invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        if(dying == true && type == 1){
            dyingCounter++;
            int i = 10;
            if(dyingCounter <= i){ image = npc1_dead1; }
            if(dyingCounter > i && dyingCounter <= i * 2){ image = npc1_dead2; }
            if(dyingCounter > i * 2 && dyingCounter <= i * 3){ image = npc1_dead3; }
            if(dyingCounter > i * 3 && dyingCounter <= i * 4){ image = npc1_dead4; }
            if(dyingCounter > i * 4 && dyingCounter <= i * 5){ image = npc1_dead5; }
            if(dyingCounter > i * 5 && dyingCounter <= i * 6){ image = npc1_dead6; }
            if(dyingCounter > i * 6){
                dying = false;
                alive = false;
            }
        }

        if(dying == true && type == 2){
            dyingCounter++;
            int i = 10;
            if(dyingCounter <= i){ image = mon_lvl1_dead1; }
            if(dyingCounter > i && dyingCounter <= i * 2){ image = mon_lvl1_dead2; }
            if(dyingCounter > i * 2 && dyingCounter <= i * 3){ image = mon_lvl1_dead3; }
            if(dyingCounter > i * 3 && dyingCounter <= i * 4){ image = mon_lvl1_dead4; }
            if(dyingCounter > i * 4 && dyingCounter <= i * 5){ image = mon_lvl1_dead5; }
            if(dyingCounter > i * 5 && dyingCounter <= i * 6){ image = mon_lvl1_dead6; }
            if(dyingCounter > i * 6){
                dying = false;
                alive = false;
            }
            gp.monstersDead++;
            System.out.println(gp.monstersDead); // verific monstrii omorati
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void searchPath(int goalCol, int goalRow){

        int startCol = (x + solidArea.x) / gp.tileSize;
        int startRow = (y + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.seach() == true){

            // next x & y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // zonele solide ale entitatii
            int enLeftX = x + solidArea.x;
            int enRightX = x + solidArea.x + solidArea.width;
            int enTopY = y + solidArea.y;
            int enBottomY = y + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // stg sau dr
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                // sus sau stg
                direction = "up";

                collisionOn = false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, false);
                gp.cChecker.checkEntity(this, gp.npc);
                gp.cChecker.checkEntity(this, gp.monster);

                boolean contactPlayer = gp.cChecker.checkPlayer(this);

                if(this.type == 2 && contactPlayer == true){
                    if(gp.player.invincible == false){
                        gp.player.life -= 1;
                        gp.player.invincible = true;
                    }
                }

                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                // sus sau dr
                direction = "up";

                collisionOn = false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, false);
                gp.cChecker.checkEntity(this, gp.npc);
                gp.cChecker.checkEntity(this, gp.monster);

                boolean contactPlayer = gp.cChecker.checkPlayer(this);

                if(this.type == 2 && contactPlayer == true){
                    if(gp.player.invincible == false){
                        gp.player.life -= 1;
                        gp.player.invincible = true;
                    }
                }

                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                // jos sau stg
                direction = "down";

                collisionOn = false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, false);
                gp.cChecker.checkEntity(this, gp.npc);
                gp.cChecker.checkEntity(this, gp.monster);

                boolean contactPlayer = gp.cChecker.checkPlayer(this);

                if(this.type == 2 && contactPlayer == true){
                    if(gp.player.invincible == false){
                        gp.player.life -= 1;
                        gp.player.invincible = true;
                    }
                }

                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                // jos sau dr
                direction = "down";

                collisionOn = false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, false);
                gp.cChecker.checkEntity(this, gp.npc);
                gp.cChecker.checkEntity(this, gp.monster);

                boolean contactPlayer = gp.cChecker.checkPlayer(this);

                if(this.type == 2 && contactPlayer == true){
                    if(gp.player.invincible == false){
                        gp.player.life -= 1;
                        gp.player.invincible = true;
                    }
                }

                if(collisionOn == true){
                    direction = "right";
                }
            }

            // daca ajunge la destinatie se termina cautarea
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
        }
    }
}
