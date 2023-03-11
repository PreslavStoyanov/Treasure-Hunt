package View;

import entities.Entity.Entity;
import entities.Entity.Player;
import entities.Tile.TileManager;
import utilities.*;
import utilities.drawers.UserInterfaceController;
import utilities.keyboard.KeyboardHandler;
import utilities.sound.Sound;
import utilities.sound.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

import static utilities.GameState.*;
import static utilities.sound.Sound.*;

public class GamePanel extends JPanel implements Runnable
{
    private static final int originalTileSize = 16;
    private static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    public static final int maxWorldCol = 62;
    public static final int maxWorldRow = 62;
    int FPS = 60;
    public TileManager tileManager = new TileManager(this);

    public KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    SoundHandler soundHandler = new SoundHandler();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public EntitySetter entitySetter = new EntitySetter(this);
    public UserInterfaceController ui = new UserInterfaceController(this);
    public Thread gameThread;

    public Player player = new Player(this, keyboardHandler);

    public ArrayList<Entity> objects = new ArrayList<>();
    public ArrayList<Entity> npcs = new ArrayList<>();
    public ArrayList<Entity> monsters = new ArrayList<>();
    ArrayList<Entity> entities = new ArrayList<>();
    private GameState gameState;

    private boolean showCoordinates;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(36, 84, 24));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
        this.setGameState(HOME_STATE);
        this.showCoordinates = false;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public void toggleShowingCoordinates()
    {
        this.showCoordinates = !showCoordinates;
    }

    public void setUpNewGame()
    {
        entitySetter.setObjects();
        entitySetter.setNpcs();
        entitySetter.setMonsters();
        setGameState(PLAY_STATE);
        ui.playTime = 0;
        player.setDefaultValues();
        playMusic(PLAYBACK);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000)
            {
                timer = 0;
            }
        }
    }

    public void update()
    {
        if (getGameState() == PLAY_STATE)
        {
            player.update();
            for (Entity entity : npcs)
            {
                if (entity != null)
                {
                    entity.update();
                }
            }
            for (int i = 0; i < monsters.size(); i++)
            {
                Entity monster = monsters.get(i);
                if (monster.alive && !monster.dying)
                {
                    monster.update();
                }
                if (!monster.alive)
                {
                    monsters.remove(monster);
                }
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        long drawStart = System.nanoTime();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (getGameState() == HOME_STATE)
        {
            ui.draw(g2);
        }
        else if (getGameState() == HELP_STATE)
        {
            ui.draw(g2);
        }
        else
        {
            drawPlayScreen(g2);
        }
        if (showCoordinates)
        {
            showCoordinates(drawStart, g2);
        }
        g2.dispose();
    }

    private void drawPlayScreen(Graphics2D g2)
    {
        tileManager.draw(g2);

        entities.add(player);
        entities.addAll(npcs);
        entities.addAll(objects);
        entities.addAll(monsters);

        entities.sort(Comparator.comparingInt(e -> e.worldY));
        entities.forEach(e -> e.draw(g2));
        entities.clear();

        ui.draw(g2);
    }

    private void showCoordinates(long drawStart, Graphics2D g2)
    {
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.white);
        int x = 10;
        int y = 400;
        int lineHeight = 20;

        g2.drawString("Invincible: " + player.invincibleCounter, x, y);
        g2.drawString("WorldX: " + player.worldX, x, y + lineHeight);
        g2.drawString("WorldY: " + player.worldY, x, y + lineHeight * 2);
        g2.drawString("Col (x): " + (player.worldX + player.solidArea.x) / tileSize, x, y + lineHeight * 3);
        g2.drawString("Row: (y): " + (player.worldY + player.solidArea.y) / tileSize, x, y + lineHeight * 4);
        g2.drawString("Draw Time: " + (System.nanoTime() - drawStart), x, y + lineHeight * 5);
    }

    public void playMusic(Sound sound)
    {
        soundHandler.setFile(sound);
        soundHandler.play();
        soundHandler.loop();
    }

    public void stopMusic()
    {
        soundHandler.stop();
    }

    public void playSoundEffect(Sound sound)
    {
        soundHandler.setFile(sound);
        soundHandler.play();
    }
}
