package main;

import object.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font arial_20;
    Font arial_80B;
    BufferedImage swordImage, coinImage, heart_full, heart_half, heart_blank, potion;
    int coin_Y_Image, coin_X_String;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public static boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;

    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp)
    {
        this.gp = gp;

        arial_20 = new Font("Arial", Font.PLAIN, 20);
        arial_80B = new Font("Arial", Font.BOLD, 60);

        OBJ_Sword sword = new OBJ_Sword();
        swordImage = sword.image;

        OBJ_Coins coin= new OBJ_Coins();
        coinImage = coin.image;

        coin_Y_Image = gp.tileSize / 4 + 2;
        coin_X_String = 3 * gp.tileSize + gp.tileSize / 4;

        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

//        if(gameFinished == true) {
//            if(gp.player.life > 0) {
//                g2.setFont(arial_20);
//                g2.setColor(Color.white);
//
//                String text;
//                int textLength;
//                int x, y;
//
//                text = "AI TERMINAT!";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//                x = gp.screenWidth / 2 - textLength;
//                y = gp.screenHeight / 2 - (gp.tileSize * 3);
//                g2.drawString(text, x, y);
//
//                text = "Timpul tău este: " + dFormat.format(playTime) + "!";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//                x = gp.screenWidth / 2 - textLength;
//                y = gp.screenHeight / 2 + (gp.tileSize * 4);
//                g2.drawString(text, x, y);
//
//                g2.setFont(arial_80B);
//                g2.setColor(Color.yellow);
//                text = "FELICITĂRI!";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//                x = gp.screenWidth / 2 - textLength;
//                y = gp.screenHeight / 2 + (gp.tileSize * 2);
//                g2.drawString(text, x, y);
//
//                //gp.gameThread = null;
//            }
//            else if (gp.player.life == 0){
//                g2.setFont(arial_80B);
//                g2.setColor(Color.white);
//
//                String text;
//                int textLength;
//                int x, y;
//
//                text = "AI MURIT!";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//                x = gp.screenWidth / 2 - textLength;
//                y = gp.screenHeight / 2 - (gp.tileSize * 3);
//                g2.drawString(text, x, y);
//
//                text = "Timpul tău este: " + dFormat.format(playTime) + "!";
//                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//                x = gp.screenWidth / 2 - textLength;
//                y = gp.screenHeight / 2 + (gp.tileSize * 2);
//                g2.drawString(text, x, y);
//
//                gp.gameThread = null;
//            }
        //} else {
            g2.setFont(arial_20);
            g2.setColor(Color.white);
            g2.drawImage(swordImage, gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
            if (gp.player.hasSword == true) {
                g2.drawString(" x 1", 85, 35);
            } else {
                g2.drawString(" x 0", 85, 35);
            }
            g2.drawImage(coinImage, 125, coin_Y_Image, gp.tileSize / 2, gp.tileSize / 2, null);
            g2.drawString("x " + gp.player.hasCoins, coin_X_String, 35);

            // MESSAGE
            if(messageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(20F)); // 30F pentru ca accepta float ca parametru
                g2.drawString(message, gp.tileSize, gp.tileSize + gp.tileSize / 2);

                messageCounter++;

                if(messageCounter > 120)
                {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        //}

        if(gameFinished == true){
            if(gp.player.life <= 0){
                gp.gameState = gp.gameOverState;
            }
        }

        this.g2 = g2;
        g2.setFont(arial_20);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            playTime += (double)1/60;
            g2.drawString("Timp: " + dFormat.format(playTime), gp.tileSize * 23 + gp.tileSize/2, 35);

            drawPlayerLife();
        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }

        // OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }

        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }

    public void drawPlayerLife()
    {
        int x = gp.tileSize * 4 + gp.tileSize / 2;
        int y = 0;
        int i = 0;

        // DRAW MAX LIFE
        while(i < gp.player.maxLife / 2)
        {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize * 4 + gp.tileSize / 2;
        y = 0;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen()
    {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Lord of the Realm";
        int x = getXforCenteredText(text);
        int y  = gp.tileSize * 3;

        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // HERO IMAGE
        x = gp.screenWidth / 3 + gp.tileSize * 3;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize + gp.tileSize / 2, gp.tileSize + gp.tileSize / 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

        text = "NEW GAME";
        x = getXforCenteredText(text) - gp.tileSize / 2;
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text) - gp.tileSize / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXforCenteredText(text) - gp.tileSize / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen()
    {
        // WINDOW BOX
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 12;
        int width = gp.screenWidth - (gp.tileSize * 5);
        int height = gp.screenHeight - (gp.tileSize * 14);

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        x += gp.tileSize;
        y += gp.tileSize;

        g2.drawString(currentDialogue, x, y);

        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString("Press ENTER to exit ...", width - (gp.tileSize * 2), y + (gp.tileSize / 2));

    }

    public void drawCharacterScreen(){

        // CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 4;
        final int frameHeight = gp.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 40; // ac. valoare ca valoarea fontului de mai sus

        // NAMES
        g2.drawString("Nivel", textX, textY);
        textY += lineHeight;
        g2.drawString("Viață", textX, textY);
        textY += lineHeight;
        g2.drawString("Atac", textX, textY);
        textY += lineHeight;
        g2.drawString("Bănuți", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Armă", textX, textY);
        textY += lineHeight;

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // RESET textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.hasCoins);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        if(gp.player.hasSword == true) {
            g2.drawImage(swordImage, tailX - gp.tileSize + 15, frameHeight - gp.tileSize, null);
            textY += gp.tileSize;
        }

        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString("Press C to exit ...", frameX + gp.tileSize, frameHeight + gp.tileSize / 2);
    }

    public void drawInventory(){

        // FRAME
        int frameX = gp.tileSize * 19;
        int frameY = 0;
        int frameWidth = gp.tileSize * 5;
        int frameHeight = gp.tileSize * 3;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        // DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, null);

            slotX += gp.tileSize;

            if(i == 3){
                slotX = slotXstart;
                slotY = gp.tileSize + gp.tileSize / 2 - 5;
            }
        }

        // CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text= "Game Over";
        // SHADOW
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize * 5;
        g2.drawString(text, x, y);
        // MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - 40, y);
        }


        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionsScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // FRAME
        int frameX = gp.tileSize * 10;
        int frameY = gp.tileSize * 3;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 7;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_controls(frameX, frameY); break;
            case 2: options_endGameConfirmation(frameX, frameY); break;
        }
    }

    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text) - gp.tileSize / 2;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // CONTROLS
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 1;
                commandNum = 0;
            }
        }

        // END GAME
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }

        // BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
    }

    public void options_controls(int frameX, int frameY){
        int textX;
        int textY;
        g2.setFont(g2.getFont().deriveFont(25F));

        // TITLE
        String text = "Controls";
        textX = getXforCenteredText(text) - gp.tileSize / 2;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + 30;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Attack", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Options Menu", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Dialogue", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Spawn troops ", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("Character status", textX, textY); textY += gp.tileSize / 2;

        textX = frameX + gp.tileSize * 3 + gp.tileSize / 2;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("W,A,S,D", textX, textY); textY += gp.tileSize / 2;
        g2.drawString("F", textX + gp.tileSize + 33, textY); textY += gp.tileSize / 2;
        g2.drawString("P", textX + gp.tileSize + 33, textY); textY += gp.tileSize / 2;
        g2.drawString("ESC", textX + gp.tileSize + 10, textY); textY += gp.tileSize / 2;
        g2.drawString("ENTER", textX + gp.tileSize / 2 + 3, textY); textY += gp.tileSize / 2;
        g2.drawString("SPACE", textX + gp.tileSize / 3 + 10, textY); textY += gp.tileSize / 2;
        g2.drawString("C", textX + gp.tileSize + 33, textY); textY += gp.tileSize / 2;

        // BACK
        textX = frameX + 30;
        textY = frameY + gp.tileSize * 6;
        g2.drawString("Back", textX, textY);
        g2.drawString(">", textX - 15, textY);
        if(gp.keyH.enterPressed == true && commandNum == 0){
            subState = 0;
            commandNum = 0;
        }

        g2.setFont(g2.getFont().deriveFont(32F));
    }

    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
                playTime = 0;
                gp.restart();
            }
        }

        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 1;
            }
        }
    }

    public void drawSubWindow(int  x, int y, int width, int height)
    {
        Color c= new Color(0, 0, 0, 220); // 220 e transparenta
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // 5 = latimea acestui stroke
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
