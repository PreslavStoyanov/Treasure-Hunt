package application;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application
{
    public static Properties defaultImagesUrls = new Properties();
    public static Properties soundsUrls = new Properties();

    public static void main(String[] args) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/defaultImages.properties");
        defaultImagesUrls.load(fileInputStream);
        fileInputStream = new FileInputStream("src/main/resources/sounds.properties");
        soundsUrls.load(fileInputStream);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Treasure Hunt");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
