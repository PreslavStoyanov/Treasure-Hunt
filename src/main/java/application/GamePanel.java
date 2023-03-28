package application;

import assets.Entity;
import assets.EntitySetter;
import assets.entities.InteractiveTile;
import assets.entities.Object;
import assets.entities.movingentities.Projectile;
import assets.entities.movingentities.liveentities.Player;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.liveentities.artificials.Npc;
import utilities.CollisionChecker;
import utilities.GameState;
import utilities.drawers.UserInterfaceController;
import utilities.statehandlers.GameStateHandler;
import utilities.sound.SoundHandler;
import utilities.tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

import static application.Application.properties;
import static utilities.GameState.HOME_STATE;
import static utilities.GameState.PLAY_STATE;
import static utilities.drawers.GameTimeDrawer.playTime;
import static utilities.sound.Sound.MAIN_BACKGROUND_MUSIC;

public class GamePanel extends JPanel implements Runnable
{
    private static final int FPS = 60;
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int HALF_TILE_SIZE = TILE_SIZE / 2;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 9;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    public static final int WORLD_COLUMNS = 50;
    public static final int WORLD_ROWS = 50;

    public static int fullScreenWidth = SCREEN_WIDTH;
    public static int fullScreenHeight = SCREEN_HEIGHT;
    private BufferedImage screen;
    private Graphics2D g2d;

    public TileManager tileManager = new TileManager(this);
    public GameStateHandler gameStateHandler = new GameStateHandler(this);
    public SoundHandler soundEffectsHandler = new SoundHandler(Boolean.parseBoolean(properties.getProperty("sound-effects")));
    public SoundHandler musicHandler = new SoundHandler(Boolean.parseBoolean(properties.getProperty("music")));
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public EntitySetter entitySetter = new EntitySetter(this);
    public UserInterfaceController ui = new UserInterfaceController(this);

    public Thread gameThread;

    public Player player = new Player(this);

    public List<Object> objects = new Vector<>();
    public List<Npc> npcs = new Vector<>();
    public List<Monster> monsters = new Vector<>();
    public List<Projectile> projectiles = new Vector<>();
    public List<InteractiveTile> interactiveTiles = new Vector<>();
    private final Stack<GameState> gameStatesOrder = new Stack<>();
    private GameState gameState = HOME_STATE;
    public boolean isGameStarted = false;
    private int frameCounter = 0;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(36, 84, 24));
        this.setDoubleBuffered(true);
        this.addKeyListener(gameStateHandler);
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
        this.gameState = gameStatesOrder.push(gameState);
    }

    public void returnToPreviousGameState()
    {
        gameStatesOrder.pop();
        this.gameState = gameStatesOrder.peek();
    }

    public void startNewGame()
    {
        gameStatesOrder.clear();
        setGameState(PLAY_STATE);
        startupDefaultSetup();
    }

    public void retryGame()
    {
        gameStatesOrder.clear();
        setGameState(PLAY_STATE);
        player.setDefaultLocation();
        player.setDefaultLife();
    }
    public void backToMainMenu()
    {
        isGameStarted = false;
        gameStatesOrder.clear();
        setGameState(HOME_STATE);
        startupDefaultSetup();
    }

    public void setUpGame()
    {
        tileManager.loadTileMap("/maps/map_one.txt");
        gameStatesOrder.clear();
        setGameState(HOME_STATE);
        startupDefaultSetup();

        musicHandler.playMusic(MAIN_BACKGROUND_MUSIC);
        screen = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) screen.getGraphics();
        if (Boolean.parseBoolean(properties.get("fullscreen").toString()))
        {
            setFullScreen();
        }
    }

    private void startupDefaultSetup()
    {
        playTime = 0;
        player.inventory.clear();
        player.setDefaultStats();
        player.setDefaultLocation();
        entitySetter.setMapOneEntities();
    }

    public void setFullScreen()
    {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Application.window);

        fullScreenWidth = Application.window.getWidth();
        fullScreenHeight = Application.window.getHeight();
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
                drawScreenImage();
                drawToScreen();
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

    public void drawToScreen()
    {
        Graphics graphics = getGraphics();
        graphics.drawImage(screen, 0, 0, fullScreenWidth, fullScreenHeight, null);
        graphics.dispose();
    }

    public void drawScreenImage()
    {
        g2d.setColor(new Color(36, 84, 24));
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        if (isGameStarted)
        {
            drawPlayScreen(g2d);
        }
        ui.draw(g2d);
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
    }

    public boolean isOnScreen(int worldX, int worldY)
    {
        return worldX + TILE_SIZE > player.worldX - player.screenX
                && worldX - TILE_SIZE < player.worldX + player.screenX
                && worldY + TILE_SIZE > player.worldY - player.screenY
                && worldY - TILE_SIZE < player.worldY + player.screenY;
    }
}
