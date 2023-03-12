package entities.Tile;

import View.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static View.GamePanel.*;
import static View.GamePanel.worldColumns;
import static utilities.images.ImageUtils.setupDefaultImage;

public class TileManager
{

    private static final String SPACE = " ";
    private final GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNumber = new int[worldColumns][worldRows];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage()
    {
        setup(0, "00_water", true);
        setup(1, "01_water", true);
        setup(2, "02_water", true);
        setup(3, "03_water", true);
        setup(4, "04_water", true);
        setup(5, "05_water", true);
        setup(6, "06_water", true);
        setup(7, "07_water", true);
        setup(8, "08_water", true);
        setup(9, "09_water", true);
        setup(10, "10_water", true);
        setup(11, "11_water", true);
        setup(12, "12_water", true);
        setup(13, "13_water", true);

        setup(14, "14_road", false);
        setup(15, "15_road", false);
        setup(16, "16_road", false);
        setup(17, "17_road", false);
        setup(18, "18_road", false);
        setup(19, "19_road", false);
        setup(20, "20_road", false);
        setup(21, "21_road", false);
        setup(22, "22_road", false);
        setup(23, "23_road", false);
        setup(24, "24_road", false);
        setup(25, "25_road", false);
        setup(26, "26_road", false);

        setup(27, "27_bridge", false);
        setup(28, "28_bridge2", false);

        setup(29, "29_earth", false);
        setup(30, "30_floor", false);
        setup(31, "31_grass", false);
        setup(32, "32_tree", true);
        setup(33, "33_tree2", true);
        setup(34, "34_wall", true);
    }

    public void setup(int index, String imageName, boolean haveCollision)
    {
        tile[index] = new Tile(setupDefaultImage("/tiles/" + imageName + ".png"), haveCollision);
    }

    public void loadMap(String mapPath)
    {
        try (InputStream is = getClass().getResourceAsStream(mapPath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))))
        {
            int col = 0;
            int row = 0;

            while (col < worldColumns && row < worldRows)
            {
                String line = br.readLine();
                while (col < worldColumns)
                {
                    String[] numbers = line.split(SPACE);
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == worldColumns)
                {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < worldColumns && worldRow < worldRows)
        {
            int tileNumber = mapTileNumber[worldCol][worldRow];
            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;
            int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
            int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

            if (isTileVisibleOnPlayerScreen(worldX, worldY))
            {
                g2.drawImage(tile[tileNumber].image(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == worldColumns)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    private boolean isTileVisibleOnPlayerScreen(int worldX, int worldY)
    {
        return worldX + tileSize > gp.player.worldX - gp.player.screenX
                && worldX - tileSize < gp.player.worldX + gp.player.screenX
                && worldY + tileSize > gp.player.worldY - gp.player.screenY
                && worldY - tileSize < gp.player.worldY + gp.player.screenY;
    }
}
