package main;

import entity.NPC_1;
import entity.NPC_2;
import entity.NPC_3;
import monster.MON_lvl1;
import monster.MON_lvl2;
import monster.MON_lvl3;
import object.OBJ_Coins;
import object.OBJ_Potion;
import object.OBJ_Potion1;
import object.OBJ_Sword;

public class AssetSetter
{
    GamePanel gp;
    public int nrnpc1 = 0;
    public int nrmon1 = 0;

    public int nrnpc2 = 0;
    public int nrmon2 = 0;

    public int nrnpc3 = 0;
    public int nrmon3 = 0;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject() {

        // MAP 0
        int mapNum = 0;


        gp.obj[mapNum][0] = new OBJ_Sword();
        gp.obj[mapNum][0].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 2 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Coins();
        gp.obj[mapNum][1].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 5 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Coins();
        gp.obj[mapNum][2].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 9 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Coins();
        gp.obj[mapNum][3].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 11 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Potion();
        gp.obj[mapNum][4].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 12 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Potion();
        gp.obj[mapNum][5].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 12 * gp.tileSize;

        // MAP 1
        mapNum = 1;

        gp.obj[mapNum][0] = new OBJ_Coins();
        gp.obj[mapNum][0].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 5 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Coins();
        gp.obj[mapNum][1].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 10 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Coins();
        gp.obj[mapNum][2].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 2 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Coins();
        gp.obj[mapNum][3].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Potion1();
        gp.obj[mapNum][4].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 12 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Potion1();
        gp.obj[mapNum][5].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 12 * gp.tileSize;

        // MAP 2
        mapNum = 2;

        gp.obj[mapNum][0] = new OBJ_Coins();
        gp.obj[mapNum][0].worldX = 7 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 5 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_Coins();
        gp.obj[mapNum][1].worldX = gp.tileSize;
        gp.obj[mapNum][1].worldY = 10 * gp.tileSize;

        gp.obj[mapNum][2] = new OBJ_Coins();
        gp.obj[mapNum][2].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 2 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Coins();
        gp.obj[mapNum][3].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Coins();
        gp.obj[mapNum][4].worldX = 3 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 4 * gp.tileSize;

    }

    public void setNPC1(int number) {

        // MAP 0
        int mapNum = 0;

        if (number == 1 && gp.npc[mapNum][0] == null) {
            gp.npc[mapNum][0] = new NPC_1(gp);
            gp.npc[mapNum][0].x = gp.tileSize * 3;
            gp.npc[mapNum][0].y = gp.tileSize * 4;
            nrnpc1++;
        } else if (number == 2 && gp.npc[mapNum][1] == null) {
            gp.npc[mapNum][1] = new NPC_1(gp);
            gp.npc[mapNum][1].x = gp.tileSize * 3;
            gp.npc[mapNum][1].y = gp.tileSize * 7;
            nrnpc1++;
        } else if (number == 3 && gp.npc[mapNum][2] == null) {
            gp.npc[mapNum][2] = new NPC_1(gp);
            gp.npc[mapNum][2].x = gp.tileSize * 3;
            gp.npc[mapNum][2].y = gp.tileSize * 10;
            nrnpc1++;
        }


        // MAP 1
        mapNum = 1;


        if (number == 4 && gp.npc[mapNum][0] == null) {
            gp.npc[mapNum][0] = new NPC_2(gp);
            gp.npc[mapNum][0].x = gp.tileSize * 3;
            gp.npc[mapNum][0].y = gp.tileSize * 4;
            nrnpc2++;
        }
        if (number == 5 && gp.npc[mapNum][1] == null) {
            gp.npc[mapNum][1] = new NPC_2(gp);
            gp.npc[mapNum][1].x = gp.tileSize * 2;
            gp.npc[mapNum][1].y = gp.tileSize * 6;
            nrnpc2++;
        }
        if (number == 6 && gp.npc[mapNum][2] == null) {
            gp.npc[mapNum][2] = new NPC_2(gp);
            gp.npc[mapNum][2].x = gp.tileSize * 3;
            gp.npc[mapNum][2].y = gp.tileSize * 8;
            nrnpc2++;
        }
        if (number == 7 && gp.npc[mapNum][3] == null) {
            gp.npc[mapNum][3] = new NPC_2(gp);
            gp.npc[mapNum][3].x = gp.tileSize * 2;
            gp.npc[mapNum][3].y = gp.tileSize * 10;
            nrnpc2++;
        }

        // MAP 2
        mapNum = 2;

        if (number == 8 && gp.npc[mapNum][0] == null) {
            gp.npc[mapNum][0] = new NPC_2(gp);
            gp.npc[mapNum][0].x = gp.tileSize * 2;
            gp.npc[mapNum][0].y = gp.tileSize * 2;
            nrnpc2++;
        }
        if (number == 9 && gp.npc[mapNum][1] == null) {
            gp.npc[mapNum][1] = new NPC_2(gp);
            gp.npc[mapNum][1].x = gp.tileSize * 2;
            gp.npc[mapNum][1].y = gp.tileSize * 4;
            nrnpc2++;
        }
        if (number == 10 && gp.npc[mapNum][2] == null) {
            gp.npc[mapNum][2] = new NPC_2(gp);
            gp.npc[mapNum][2].x = gp.tileSize * 2;
            gp.npc[mapNum][2].y = gp.tileSize * 6;
            nrnpc2++;
        }
        if (number == 11 && gp.npc[mapNum][3] == null) {
            gp.npc[mapNum][3] = new NPC_2(gp);
            gp.npc[mapNum][3].x = gp.tileSize * 2;
            gp.npc[mapNum][3].y = gp.tileSize * 8;
            nrnpc2++;
        }
        if (number == 12 && gp.npc[mapNum][4] == null) {
            gp.npc[mapNum][4] = new NPC_2(gp);
            gp.npc[mapNum][4].x = gp.tileSize * 2;
            gp.npc[mapNum][4].y = gp.tileSize * 10;
            nrnpc2++;
        }

    }

    public void setMonster1(int number){

        // MAP 0
        int mapNum = 0;

        if (gp.npc[mapNum][0] != null && number == 1) {
            gp.monster[mapNum][0] = new MON_lvl1(gp);
            gp.monster[mapNum][0].x = gp.tileSize * 25;
            gp.monster[mapNum][0].y = gp.tileSize * 4;
            nrmon1++;
        }
        if (gp.npc[mapNum][1] != null && number == 2) {
            gp.monster[mapNum][1] = new MON_lvl1(gp);
            gp.monster[mapNum][1].x = gp.tileSize * 25;
            gp.monster[mapNum][1].y = gp.tileSize * 7;
            nrmon1++;
        }
        if (gp.npc[mapNum][2] != null && number == 3) {
            gp.monster[mapNum][2] = new MON_lvl1(gp);
            gp.monster[mapNum][2].x = gp.tileSize * 25;
            gp.monster[mapNum][2].y = gp.tileSize * 10;
            nrmon1++;
        }

        // MAP 1
        mapNum = 1;

        if(gp.npc[mapNum][0] != null && number == 4){
            gp.monster[mapNum][0] = new MON_lvl2(gp);
            gp.monster[mapNum][0].x = gp.tileSize * 25;
            gp.monster[mapNum][0].y = gp.tileSize * 4;
            nrmon2++;
        }
        if(gp.npc[mapNum][1] != null && number == 5){
            gp.monster[mapNum][1] = new MON_lvl2(gp);
            gp.monster[mapNum][1].x = gp.tileSize * 25;
            gp.monster[mapNum][1].y = gp.tileSize * 6;
            nrmon2++;
        }
        if(gp.npc[mapNum][2] != null && number == 6){
            gp.monster[mapNum][2] = new MON_lvl2(gp);
            gp.monster[mapNum][2].x = gp.tileSize * 25;
            gp.monster[mapNum][2].y = gp.tileSize * 8;
            nrmon2++;
        }
        if(gp.npc[mapNum][3] != null && number == 7){
            gp.monster[mapNum][3] = new MON_lvl2(gp);
            gp.monster[mapNum][3].x = gp.tileSize * 25;
            gp.monster[mapNum][3].y = gp.tileSize * 10;
            nrmon2++;
        }

        // MAP 2
        mapNum = 2;

        if(gp.npc[mapNum][0] != null && number == 8){
            gp.monster[mapNum][0] = new MON_lvl3(gp);
            gp.monster[mapNum][0].x = gp.tileSize * 25;
            gp.monster[mapNum][0].y = gp.tileSize * 2;
            nrmon2++;
        }
        if(gp.npc[mapNum][1] != null && number == 9){
            gp.monster[mapNum][1] = new MON_lvl3(gp);
            gp.monster[mapNum][1].x = gp.tileSize * 25;
            gp.monster[mapNum][1].y = gp.tileSize * 4;
            nrmon2++;
        }
        if(gp.npc[mapNum][2] != null && number == 10){
            gp.monster[mapNum][2] = new MON_lvl3(gp);
            gp.monster[mapNum][2].x = gp.tileSize * 25;
            gp.monster[mapNum][2].y = gp.tileSize * 6;
            nrmon2++;
        }
        if(gp.npc[mapNum][3] != null && number == 11){
            gp.monster[mapNum][3] = new MON_lvl3(gp);
            gp.monster[mapNum][3].x = gp.tileSize * 25;
            gp.monster[mapNum][3].y = gp.tileSize * 8;
            nrmon2++;
        }
        if(gp.npc[mapNum][4] != null && number == 12){
            gp.monster[mapNum][4] = new MON_lvl3(gp);
            gp.monster[mapNum][4].x = gp.tileSize * 25;
            gp.monster[mapNum][4].y = gp.tileSize * 10;
            nrmon2++;
        }


    }

//    public void setBoss1(){
//
//        // MAP 0
//        int mapNum = 0;
//
//        if(gp.monstersDead == 183) {
//            gp.boss1[mapNum][0] = new BOSS_lvl1(gp);
//            gp.boss1[mapNum][0].x = gp.tileSize * 5;
//            gp.boss1[mapNum][0].y = gp.tileSize * 6;
//        }
//
//        // MAP 1
//        // mapNum = 1;
//    }

}
