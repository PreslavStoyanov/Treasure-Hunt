package application;

import assets.Entity;
import assets.entities.InteractiveTile;
import assets.entities.movingentities.AliveEntity;
import assets.entities.Object;
import assets.entities.movingentities.Projectile;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.liveentities.artificials.Npc;
import assets.entities.movingentities.liveentities.Player;
import utilities.CollisionChecker;
import assets.EntitySetter;
import utilities.GameState;
import utilities.drawers.UserInterfaceController;
import utilities.keyboard.KeyboardHandler;
import utilities.sound.SoundHandler;
import utilities.tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static utilities.GameState.*;
import static utilities.sound.Sound.MAIN_BACKGROUND_MUSIC;

public class GamePanel extends JPanel implements Runnable
{
    private static final int FPS = 60;
    private static final int originalTileSize = 16;
    private static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    public static final int halfTileSize = tileSize / 2;
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

    public List<Object> objects = new CopyOnWriteArrayList<>();
    public List<Npc> npcs = new CopyOnWriteArrayList<>();
    public List<Monster> monsters = new CopyOnWriteArrayList<>();
    public List<Projectile> projectiles = new CopyOnWriteArrayList<>();
    public List<InteractiveTile> interactiveTiles = new CopyOnWriteArrayList<>();
    private GameState gameState = HOME_STATE;
    private int frameCounter = 0;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(36, 84, 24));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    public int getFrameCounter()
    {
        return frameCounter;
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
        entitySetter.setInteractiveTiles();
        setGameState(PLAY_STATE);
        soundHandler.playMusic(MAIN_BACKGROUND_MUSIC);
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

        //time can be used for some special time events
        long timer = 0;

        while (!gameThread.isInterrupted())
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                frameCounter++;
                if (frameCounter == Integer.MAX_VALUE)
                {
                    frameCounter = 0;
                }
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

            npcs.stream().filter(Objects::nonNull).forEach(Npc::update);

            monsters.stream().filter(monster -> !monster.isAlive).forEach(Monster::dropItem);
            monsters.removeIf(monster -> !monster.isAlive);
            monsters.stream().filter(monster -> !monster.isDying).forEach(Monster::update);

            projectiles.removeIf(projectile -> !projectile.isFlying());
            projectiles.stream().filter(Projectile::isFlying).forEach(Projectile::update);
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
        List<Entity> entities = new ArrayList<>();
        entities.add(player);
        entities.addAll(npcs);
        entities.addAll(objects);
        entities.addAll(monsters);
        entities.addAll(projectiles);
        entities.addAll(interactiveTiles);

        entities.sort(Comparator.comparingInt(e -> e.worldY));
        entities.forEach(entity -> entity.draw(g2));
        entities.clear();

        ui.draw(g2);
    }

    public boolean isOnScreen(int worldX, int worldY)
    {
        return worldX + tileSize > player.worldX - player.screenX
                && worldX - tileSize < player.worldX + player.screenX
                && worldY + tileSize > player.worldY - player.screenY
                && worldY - tileSize < player.worldY + player.screenY;
    }
}
