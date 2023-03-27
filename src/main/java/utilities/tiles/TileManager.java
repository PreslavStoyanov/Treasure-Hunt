package utilities.tiles;

import application.GamePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.awt.*;
import java.io.*;
import java.util.Objects;

import static application.GamePanel.*;

public class TileManager
{
    private final GamePanel gp;
    private final ObjectMapper objectMapper;
    private final Tiles tiles;
    private final int[][] mapTileNumbers;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.tiles = setUpTiles();
        this.mapTileNumbers = new int[WORLD_COLUMNS][WORLD_ROWS];
    }

    public int[][] getMapTileNumbers()
    {
        return mapTileNumbers;
    }

    public Tiles getTiles()
    {
        return tiles;
    }

    private Tiles setUpTiles()
    {
        try
        {
            return objectMapper.readValue(new File("src/main/resources/tiles/tiles.yaml"), Tiles.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Setting tiles error", e);
        }
    }

    public void loadTileMap(String mapPath)
    {
        try (InputStream is = getClass().getResourceAsStream(mapPath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is))))
        {
            String line;
            for (int row = 0; row < WORLD_ROWS && (line = br.readLine()) != null; row++)
            {
                String[] numbers = line.split("\\s+");
                for (int col = 0; col < Math.min(WORLD_COLUMNS, numbers.length); col++)
                {
                    mapTileNumbers[col][row] = Integer.parseInt(numbers[col]);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to load map from " + mapPath, e);
        }
    }

    public void draw(Graphics2D g2)
    {
        for (int row = 0; row < WORLD_ROWS; row++)
        {
            for (int col = 0; col < WORLD_COLUMNS; col++)
            {
                int tileNumber = mapTileNumbers[col][row];
                int worldX = col * TILE_SIZE;
                int worldY = row * TILE_SIZE;
                int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
                int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

                if (gp.isOnScreen(worldX, worldY))
                {
                    g2.drawImage(tiles.getTiles().get(tileNumber).getImage(), screenX, screenY, null);
                }
            }
        }
    }
}
