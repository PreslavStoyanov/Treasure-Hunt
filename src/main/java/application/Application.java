package application;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application
{
    public static Properties objectsImagesUrls = new Properties();
    public static Properties soundsUrls = new Properties();
    public static Properties interactiveTilesUrls = new Properties();
    public static Properties properties = new Properties();
    public static JFrame window;

    public static void main(String[] args) throws IOException
    {
        setupUrlsFromProperties();
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Treasure Hunt");
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    private static void setupUrlsFromProperties() throws IOException
    {
        objectsImagesUrls.load(new FileInputStream("src/main/resources/objects/objects_images.properties"));
        interactiveTilesUrls.load(new FileInputStream("src/main/resources/interactive-tiles/interactive_tiles.properties"));
        soundsUrls.load(new FileInputStream("src/main/resources/sounds/sounds.properties"));
        properties.load(new FileInputStream("src/main/resources/application.properties"));
    }
}
