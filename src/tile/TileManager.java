package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[20];

        mapTileNum = new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt", 0);
        loadMap("/maps/map02.txt", 1);
        loadMap("/maps/map03.txt", 2);
    }

    public void getTileImage() {

        // MAP 1
        setup(0, "grass_tile_1",false);
        setup(1, "ponton_tile",false);
        setup(2, "water_tile",true);
        setup(3, "sand_tile",false);
        setup(4, "rock_tile",true);
        setup(5, "brick_tile",true);
        setup(6, "stairs_tile",true);
        setup(7, "lava_tile",false);
        setup(8, "ponton_tile_1",false);

        // MAP 2
        setup(9, "nether_rock_tile",false);
        setup(10,"nether_floor_tile",false);
        setup(11,"lava_tile",true);
        setup(12,"double_bridge_tile",false);
        setup(13,"double_bridge_tile_1",false);

        // MAP 3
        setup(14,"snow_tile",false);
        setup(15,"bridge_tile",false);
        setup(16,"ice_tile",true);
        setup(17,"snowOnIce_tile",false);
    }


    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try
        {
            tile[index] = new Tile();
            //tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = ImageIO.read(new FileInputStream("res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {

        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow)
            {
                String line = br.readLine();

                while(col < gp.maxScreenCol)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e)
        {}
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[gp.currentMap][col][row];

            g2.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {

                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

        if(drawPath == true){
            g2.setColor(new Color(255, 0, 0, 20));

            for(int i = 0; i < gp.pFinder.pathList.size(); i++){

                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.x;
                int screenY = worldY - gp.player.x;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }
    }
}
