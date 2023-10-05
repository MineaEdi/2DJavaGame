package main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 27;//16
    public final int maxScreenRow = 16;//12
    public final int screenWidth = tileSize * maxScreenCol; //1248 pixels
    public final int screenHeight = tileSize * maxScreenRow; //720 pixels

    public final int maxMap = 5; // pana la 5 mape
    public int currentMap = 0; // mapa curenta
    public int monstersDead = 0;

    // FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);

    Thread gameThread;

    // ENTITY & OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[][] = new SuperObject[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][10];


    // GAME STATE
    public int gameState;
    public final int titleState = 0; // cazul in care suntem in ecranul de start
    public final int playState = 1; // cazul in care suntem in timpul jocului
    public final int pauseState = 2; // cazul in care suntem in pauza
    public final int dialogueState = 3; // cazul in care suntem in dialog
    public final int characterState = 4; // afisam atributiile jucatorului
    public final int optionsState = 5; // optiuni
    public final int gameOverState = 6; // ai murit!


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth - 48, screenHeight - 48));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // acest GamePanel este "focusat" sa primeasca key input
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = titleState;
    }

    public void retry(){
        player.setDefaultPositions();
        player.restoreLife();
    }

    public void restart(){
        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        player.hasCoins = 0;
        player.hasSword = false;
        ui.playTime = 0;
        player.numberNpc = 0;
        currentMap = 0;
        monstersDead = 0;

        for(int i = 0; i < npc[1].length; i++) {
            if(npc[0][i] != null) {
                npc[0][i] = null;
            }
        }
        for(int i = 0; i < monster[1].length; i++) {
            if(monster[0][i] != null) {
                monster[0][i] = null;
            }
        }

        for(int i = 0; i < npc[1].length; i++) {
            if(npc[1][i] != null) {
                npc[1][i] = null;
            }
        }
        for(int i = 0; i < monster[1].length; i++) {
            if(monster[1][i] != null) {
                monster[1][i] = null;
            }
        }

        for(int i = 0; i < npc[1].length; i++) {
            if(npc[2][i] != null) {
                npc[2][i] = null;
            }
        }
        for(int i = 0; i < monster[1].length; i++) {
            if(monster[2][i] != null) {
                monster[2][i] = null;
            }
        }




//        for(int i = 0; i < boss1[1].length; i++) {
//            if(boss1[0][i] != null) {
//                boss1[0][i] = null;
//            }
//        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        try {
            keyH.getTime();
        } catch ( SQLException ae ) {
            System.out.println("Error getting time!");
        }
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // GAME LOOP
        while(gameThread != null) {

            //long currentTime = System.nanoTime(); // 1.000.000.000 ns = 1 s ESTE MAI PRECIS
            //long currentTime2 = System.currentTimeMillis(); // 1.000 ms = 1 s
            //System.out.println("Current Time:"+currentTime);

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                // 1 UPDATE: updatam informatii precum pozitia jucatorului
                update();
                // 2 DRAW: afiseaza pe ecran informatiile updatate
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)
            {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update()
    {
        // in JAVA colt stg sus are coordonatele (X;Y) = (0;0)
        // X creste spre dreapta
        // Y creste in jos

        if(gameState == playState) {
            // PLAYER
            player.update();

            // NPC1
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    if(npc[currentMap][i].alive == true && npc[currentMap][i].dying == false){
                        npc[currentMap][i].update();
                    }
                    if(npc[currentMap][i].alive == false){
                        npc[currentMap][i] = null;
                    }
                }
            }

            // MONSTERlvl1
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i] = null;
                    }
                }
            }

//            // BOSSlvl1
//            for(int i = 0; i < boss1[1].length; i++) {
//                if(boss1[currentMap][i] != null) {
//                    if(boss1[currentMap][i].alive == true && boss1[currentMap][i].dying == false){
//                        boss1[currentMap][i].update();
//                    }
//                    if(boss1[currentMap][i].alive == false){
//                        boss1[currentMap][i] = null;
//                    }
//                }
//            }
        }

        if(gameState == pauseState){
            // nimic momentan
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            //OBJECT
            for(int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }

            // NPC
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }

            // MONSTER
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    monster[currentMap][i].draw(g2);
                }
            }

//            // BOSSlvl1
//            for(int i = 0; i < boss1[1].length; i++) {
//                if(boss1[currentMap][i] != null) {
//                    boss1[currentMap][i].draw(g2);
//                }
//            }

            // PLAYER
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        // DEBUG
        if(keyH.checkDrawTime == true)
        {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10 , 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }
}
