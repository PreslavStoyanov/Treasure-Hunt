package View;

import Controller.*;
import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 62;
    public final int maxWorldRow = 62;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    int FPS = 60;

    public TileManager tileManager = new TileManager(this);
    public KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    SoundController music = new SoundController();
    SoundController soundEffect = new SoundController();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UIController ui = new UIController(this);
    public EventHandler eventHandler = new EventHandler(this);

    public Thread gameThread;

    public Player player = new Player(this, keyboardHandler);
    public ArrayList<Entity> objects = new ArrayList<>();
    public ArrayList<Entity> npc = new ArrayList<>();
    public ArrayList<Entity> monsters = new ArrayList<>();
    ArrayList<Entity> entities = new ArrayList<>();

    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int helpState = 3;
    public final int dialogueState = 4;
    public final int characterState = 5;
    public int gameState = titleState;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(36, 84, 24));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    public void setUpNewGame() {
        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();
        gameState = playState;
        ui.playTime = 0;
        player.setDefaultValues();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < monsters.size(); i++) {
                Entity monster = monsters.get(i);
                if (monster.alive && !monster.dying) {
                    monster.update();
                }
                if (!monster.alive) {
                    monsters.remove(monster);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        long drawStart = System.nanoTime();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else if (gameState == helpState) {
            ui.draw(g2);
        } else {
            tileManager.draw(g2);

            entities.add(player);
            entities.addAll(npc);
            entities.addAll(objects);
            entities.addAll(monsters);

            entities.sort(Comparator.comparingInt(e -> e.worldY));
            entities.forEach(e -> e.draw(g2));
            entities.clear();

            ui.draw(g2);
        }
        if (keyboardHandler.showCordText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

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
            g2.drawString("Draw Time: " + passed, x, y + lineHeight * 5);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
