package main;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class KeyHandler implements KeyListener
{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, attackPressed;
    // DEBUG
    boolean checkDrawTime = false;
    public boolean spaceRel = true;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        // CHARACTER STATE
        else if (gp.gameState == gp.characterState){
            characterState(code);
        }
        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState){
            optionsState(code);
        }
        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
    }

    public void titleState(int code){
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
//                try {
//                    getCoins();
//                } catch ( SQLException ae ) {
//                    System.out.println("Error gettind time!");
//                }
            }
            if(gp.ui.commandNum == 1){
                // pe mai tarziu(load game)
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if(spaceRel == true) {
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
                spaceRel = false;
            }
        }

        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }

        if(code == KeyEvent.VK_F){
            attackPressed = true;
            Entity.attacking = true;
        }

        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }

        // DEBUG
        if(code == KeyEvent.VK_T) {
            if(checkDrawTime == false){
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap) {
                case 0: gp.tileM.loadMap("/maps/map01.txt", 0); break;
                case 1: gp.tileM.loadMap("/maps/map02.txt", 1); break;
                //case 2: gp.tileM.loadMap("/maps/map03.txt", 2); break;
            }
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_UP){
            if(gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.ui.slotRow != 1) {
                gp.ui.slotRow++;
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.ui.slotCol != 3) {
                gp.ui.slotCol++;
            }
        }
    }

    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0: maxCommandNum = 2; break;
            case 1: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
    }

    public void gameOverState(int code){
        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
                gp.ui.commandNum = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) { upPressed = false; }
        if(code == KeyEvent.VK_S) { downPressed = false; }
        if(code == KeyEvent.VK_A) { leftPressed = false; }
        if(code == KeyEvent.VK_D) { rightPressed = false; }
        if(spaceRel == false){
            if(code == KeyEvent.VK_SPACE){
                spacePressed = false;
                spaceRel = true;
            }
        }
        if(code == KeyEvent.VK_ENTER) { enterPressed = false; }
    }

    public static void AddLife_CoinsDB(int lf, int cns, double time) throws SQLException{
        Connection c = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Lord_of_the_Realm.db");
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Lord_of_the_Realm" + "(COINS)";
            //sql = "ALTER TABLE Lord_of_the_Realm ADD COLUMN LIFE int";
            //sql = "ALTER TABLE Lord_of_the_Realm ADD COLUMN TIME double";
            stmt.executeUpdate(sql);
            c.setAutoCommit(false);

            sql = "INSERT INTO Lord_of_the_Realm (COINS, TIME, LIFE) " +
                    "VALUES (0, 0, 0);";
            stmt.executeUpdate(sql);

            String sql1 = "UPDATE Lord_of_the_Realm set COINS=" + cns + " WHERE rowid=" + (1) + ";";
            stmt.executeUpdate(sql1);

            sql1 = "UPDATE Lord_of_the_Realm set TIME=" + time + " WHERE rowid=" + (1) + ";";
            stmt.executeUpdate(sql1);

            sql1 = "UPDATE Lord_of_the_Realm set LIFE=" + lf + " WHERE rowid=" + (1) + ";";
            stmt.executeUpdate(sql1);

            stmt.close();
            c.commit();
            c.close();
        }catch( Exception e ){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void getTime() throws SQLException{
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Lord_of_the_Realm.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);

            ResultSet rs = stmt.executeQuery("SELECT * FROM Lord_of_the_Realm;");
            double T = rs.getDouble("TIME");
            System.out.println("Last session was " + T + " seconds long!");
            rs.close();

            stmt.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
