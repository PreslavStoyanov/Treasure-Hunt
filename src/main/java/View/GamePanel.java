package View;

import entities.Entity;
import entities.types.Monster;
import entities.types.Npc;
import entities.types.Object;
import entities.types.Player;
import utilities.CollisionChecker;
import utilities.EntitySetter;
import utilities.GameState;
import utilities.drawers.UserInterfaceController;
import utilities.keyboard.KeyboardHandler;
import utilities.sound.Sound;
import utilities.sound.SoundHandler;
import utilities.tiles.TileManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static utilities.GameState.*;
import static utilities.drawers.GameTimeDrawer.playTime;
import static utilities.sound.Sound.PLAYBACK;

public class GamePanel extends JPanel implements Runnable
{
    private static final int FPS = 60;
    private static final int originalTileSize = 16;
    private static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    public static final int worldColumns = 62;
    public static final int worldRows = 62;
    public TileManager tileManager = new TileManager(this);

    public KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    public SoundHandler soundHandler = new SoundHandler();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public EntitySetter entitySetter = new EntitySetter(this);
    public UserInterfaceController ui = new UserInterfaceController(this);
    public Thread gameThread;

    public Player player = new Player(this, keyboardHandler);

    public List<Object> objects = new ArrayList<>();
    public List<Npc> npcs = new ArrayList<>();
    public List<Monster> monsters = new ArrayList<>();
    public List<Entity> entities = new ArrayList<>();
    private GameState gameState = HOME_STATE;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(36, 84, 24));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public void setUpNewGame()
    {
        tileManager.loadTileMap("/maps/world01.txt");
        entitySetter.setObjects();
        entitySetter.setNpcs();
        entitySetter.setMonsters();
        setGameState(PLAY_STATE);
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
                if (monster.isAlive && !monster.isDying)
                {
                    monster.update();
                }
                if (!monster.isAlive)
                {
                    monsters.remove(monster);
                }
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (getGameState() == HOME_STATE || getGameState() == HELP_STATE)
        {
            ui.draw(g2);
        }
        else
        {
            drawPlayScreen(g2);
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
