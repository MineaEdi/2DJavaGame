package main;

import java.awt.Rectangle;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        if(hit(0,24, 2, "right") == true || hit(0,24, 2, "up") == true || hit(0,24, 1, "right") == true || hit(0,25, 2, "up") == true){
            damagePit(gp.dialogueState);
            if(gp.player.life == 0){
                UI.gameFinished = true;
            }
        } else if(hit(0,25, 12, "right") == true || hit(0,25, 12, "down") == true){
            teleport(gp.dialogueState);
        } //else if(hit(0, 12, 12, "any") == true || hit(0, 13, 12, "any") == true){
            //change_map(1, 2, 3); // DUPA CE AM BATUT MONSTRII SI BOSII
        //} //else if(hit(1,25, 12, "right") == true || hit(1,25, 12, "down") == true){
//            change_map(0, 2, 3);
//        }

        if(gp.currentMap == 0){
            if(gp.monstersDead == 183) {
                if (hit(0, 12, 12, "any") == true || hit(0, 13, 12, "any") == true) {
                    change_map(1, 2, 3);
                    gp.currentMap = 1;
                    gp.player.attack++;
                    gp.monstersDead = 0;
                }
            }
        }

        if(gp.currentMap == 1){
            if(gp.monstersDead == 244) {
                if (hit(1, 12, 12, "any") == true || hit(1, 13, 12, "any") == true) {
                    change_map(2, 2, 3);
                    gp.currentMap = 2;
                    gp.player.attack++;
                    gp.player.speed--;
                    gp.monstersDead = 0;
                }
            }
        }

        if(gp.currentMap == 2){
            if(gp.monstersDead == 305) {
                if (hit(2, 12, 12, "any") == true || hit(2, 13, 12, "any") == true) {
                    gp.gameState = gp.titleState;
                    gp.restart();
                }
            }
        }
    }

    public boolean hit(int map, int eventCol, int eventRow, String regDirection){
        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
            eventRect.x = eventCol * gp.tileSize + eventRect.x;
            eventRect.y = eventRow * gp.tileSize + eventRect.y;

            if (gp.player.solidArea.intersects(eventRect)) {
                if (gp.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
                    hit = true;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect.x = eventRectDefaultX;
            eventRect.y = eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "AUCH!";
        gp.player.life -= 1;
    }

    //public void healingPool(int gameState){
    //    if(gp.keyH.enterPressed == true){
    //        gp.gameState = gameState;
    //        gp.ui.currentDialogue = "Cred ca potiunea asta imi creste viata...";
    //        gp.player.life = gp.player.maxLife;
    //    }
    //}

    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.x = 100;
        gp.player.y = 100;
    }

    public void change_map(int map, int col, int row){
        gp.currentMap = map;
        gp.player.x = gp.tileSize * col;
        gp.player.y = gp.tileSize * row;
    }
}
