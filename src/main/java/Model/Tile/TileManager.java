package Model.Tile;

import Controller.UtilityTool;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        /*setup(0, "water01", false);
        setup(1, "water01", true);
        setup(2, "water01", true);
        setup(3, "water01", false);
        setup(4, "water01", true);
        setup(5, "water01", false);
        setup(6, "water01", true);
        setup(7, "water01", false);
        setup(8, "water01", false);
        setup(9, "water01", false);

        setup(10, "water00", true);
        setup(11, "water01", true);
        setup(12, "water02", true);
        setup(13, "water03", true);
        setup(14, "water04", true);
        setup(15, "water05", true);
        setup(16, "water06", true);
        setup(17, "water07", true);
        setup(18, "water08", true);
        setup(19, "water09", true);
        setup(20, "water10", true);
        setup(21, "water11", true);
        setup(22, "water12", true);
        setup(23, "water13", true);
        setup(24, "grass", false);
        setup(25, "wall", true);
        setup(26, "earth", false);
        setup(27, "tree", true);
        setup(28, "tree2", true);
        setup(29, "road00", false);
        setup(30, "floor01", false);
        setup(31, "road01", false);
        setup(32, "road02", false);
        setup(33, "road03", false);
        setup(34, "road04", false);
        setup(35, "road05", false);
        setup(36, "road06", false);
        setup(37, "road07", false);
        setup(38, "road08", false);
        setup(39, "road09", false);
        setup(40, "road10", false);
        setup(41, "road11", false);
        setup(42, "road12", false);
        setup(43, "bridge", false);
        setup(44, "bridge2", false);*/
        setup(1, "water00", true);
        setup(35, "water01", true);
        setup(2, "water02", true);
        setup(3, "water03", true);
        setup(4, "water04", true);
        setup(5, "water05", true);
        setup(6, "water06", true);
        setup(7, "water07", true);
        setup(8, "water08", true);
        setup(9, "water09", true);
        setup(10, "water10", true);
        setup(11, "water11", true);
        setup(12, "water12", true);
        setup(13, "water13", true);

        setup(14, "road00", false);
        setup(15, "road01", false);
        setup(16, "road02", false);
        setup(17, "road03", false);
        setup(18, "road04", false);
        setup(19, "road05", false);
        setup(20, "road06", false);
        setup(21, "road07", false);
        setup(22, "road08", false);
        setup(23, "road09", false);
        setup(24, "road10", false);
        setup(25, "road11", false);
        setup(26, "road12", false);

        setup(27, "bridge", false);
        setup(28, "bridge2", false);

        setup(29, "earth", false);
        setup(30, "floor01", false);
        setup(31, "grass", false);
        setup(32, "tree", true);
        setup(33, "tree2", true);
        setup(34, "wall", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try (InputStream is = getClass().getResourceAsStream(mapPath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))){
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNumber = mapTileNumber[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
            int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNumber].image, screenX, screenY, null);
            }
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }

        }
    }
}
