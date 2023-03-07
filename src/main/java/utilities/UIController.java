package utilities;

import View.GamePanel;
import entities.Entity.Entity;
import entities.Objects.Heart;
import entities.Objects.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import static utilities.GameState.*;

public class UIController
{
    GamePanel gp;
    Graphics2D g2;

    public Font pixelFont, pixelFont20, pixelFont40;

    BufferedImage keyImage, heartFull, heartHalf, heartBlank;


    public boolean messageOn = false;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageTimer = new ArrayList<>();


    public boolean gameFinished = false;
    public double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public int commandNumber = 0;

    public String currentDialogue = "";


    public UIController(GamePanel gamePanel)
    {
        this.gp = gamePanel;
        Key key = new Key(gamePanel);
        keyImage = key.downSprites.get(0);

        try
        {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("font/prstartk.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(stream)).deriveFont(14f);

        } catch (FontFormatException | IOException e)
        {
            e.printStackTrace();
        }
        pixelFont20 = pixelFont.deriveFont(20f);
        pixelFont40 = pixelFont.deriveFont(40f);

        Entity heart = new Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
    }

    public void addMessage(String text)
    {
        messages.add(text);
        messageTimer.add(0);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(pixelFont);
        g2.setColor(Color.white);
        if (gp.getGameState() == HOME_STATE)
        {
            drawTitleScreen();
        }
        if (gp.getGameState() == HELP_STATE)
        {
            drawHelpMenu();
        }
        if (gp.getGameState() == DIALOGUE_STATE)
        {
            drawPlayerLife();
            drawDialogueScreen();
        }
        if (gp.getGameState() == PLAY_STATE)
        {
            if (gameFinished)
            {
                drawEndScreen(g2);
                gp.gameThread = null;
            }
            else
            {
                g2.setFont(pixelFont20);
                g2.setColor(Color.white);
                drawPlayerLife();
                playTime += (double) 1 / 60;
                g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize * 11, gp.tileSize);
                drawMessage();
            }
        }
        if (gp.getGameState() == PAUSE_STATE)
        {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.getGameState() == CHARACTER_STATE)
        {
            drawPlayerLife();
            drawCharacterScreen();
        }
    }

    public void drawMessage()
    {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 6;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
        for (int i = 0; i < messages.size(); i++)
        {
            if (messages.get(i) != null)
            {
                g2.setColor(Color.black);
                g2.drawString(messages.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(messages.get(i), messageX, messageY);

                int counter = messageTimer.get(i) + 1;
                messageTimer.set(i, counter);
                messageY += 30;

                if (messageTimer.get(i) > 180)
                {
                    messages.remove(i);
                    messageTimer.remove(i);
                }
            }
        }
    }

    public void drawPlayerLife()
    {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;

        for (int i = 0; i < gp.player.maxLife / 2; i++)
        {
            g2.drawImage(heartBlank, x, y, null);
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        for (int i = 0; i < gp.player.life; i++)
        {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gp.player.life)
            {
                g2.drawImage(heartFull, x, y, null);
            }
            x += gp.tileSize;
        }
    }

    public void drawEndScreen(Graphics2D g2)
    {
        g2.setFont(pixelFont20);
        g2.setColor(Color.white);

        String text;
        int textLength;
        int x;
        int y;

        text = "You found the treasure!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        text = "Your Time is : " + decimalFormat.format(playTime) + "!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 + (gp.tileSize * 4);
        g2.drawString(text, x, y);

        g2.setFont(pixelFont40);
        g2.setColor(Color.yellow);
        text = "Congratulations!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - textLength / 2;
        y = gp.screenHeight / 2 + (gp.tileSize * 2);
        g2.drawString(text, x, y);
    }

    private void drawDialogueScreen()
    {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawTitleScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56F));
        String text = "Treasure Hunt";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.thumbUp, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNumber == 0)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "HELP";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNumber == 1)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNumber == 2)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawHelpMenu()
    {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        String text = "Buttons";
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        x = gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15F));

        g2.drawString("    W | MOVE UP", x, y + gp.tileSize);
        g2.drawString("    S | MOVE DOWN", x, y + gp.tileSize * 2);
        g2.drawString("    A | MOVE LEFT", x, y + gp.tileSize * 3);
        g2.drawString("    D | MOVE RIGHT", x, y + gp.tileSize * 4);
        g2.drawString("SPACE | ATTACK", x, y + gp.tileSize * 5);
        g2.drawString("    P | PAUSE", x, y + gp.tileSize * 6);
        g2.drawString("    E | TALK WITH NPC", x, y + gp.tileSize * 7);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "MAIN MENU";
        x = getXForCenteredText(text);
        y += gp.tileSize * 10;
        g2.drawString(text, x, y);
        g2.drawString(">", x - gp.tileSize, y);
    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawCharacterScreen()
    {
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(15F));
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        g2.drawString("Level", textX, textY);
        g2.drawString("Life", textX, textY + lineHeight);
        g2.drawString("Strength", textX, textY + lineHeight * 2);
        g2.drawString("Agility", textX, textY + lineHeight * 3);
        g2.drawString("Attack", textX, textY + lineHeight * 4);
        g2.drawString("Defense", textX, textY + lineHeight * 5);
        g2.drawString("Exp", textX, textY + lineHeight * 6);
        g2.drawString("Next level", textX, textY + lineHeight * 7);
        g2.drawString("Coins", textX, textY + lineHeight * 8);
        g2.drawString("Weapon", textX, textY + lineHeight * 9 + 10);
        g2.drawString("Shield", textX, textY + lineHeight * 10 + 30);

        int tailX = frameX + frameWidth - 30;

        drawParameter(String.valueOf(gp.player.level), textY, tailX);
        drawParameter(gp.player.life + "/" + gp.player.maxLife, textY + lineHeight, tailX);
        drawParameter(String.valueOf(gp.player.strength), textY + lineHeight * 2, tailX);
        drawParameter(String.valueOf(gp.player.agility), textY + lineHeight * 3, tailX);
        drawParameter(String.valueOf(gp.player.attack), textY + lineHeight * 4, tailX);
        drawParameter(String.valueOf(gp.player.defense), textY + lineHeight * 5, tailX);
        drawParameter(String.valueOf(gp.player.exp), textY + lineHeight * 6, tailX);
        drawParameter(String.valueOf(gp.player.nextLevelExp), textY + lineHeight * 7, tailX);
        drawParameter(String.valueOf(gp.player.coins), textY + lineHeight * 8, tailX);
        g2.drawImage(gp.player.currentWeapon.image, tailX - gp.tileSize, textY + lineHeight * 7 + gp.tileSize, null);
        g2.drawImage(gp.player.currentShield.image, tailX - gp.tileSize, textY + lineHeight * 7 + gp.tileSize * 2 + 10, null);
    }

    private void drawParameter(String value, int textY, int tailX)
    {
        int textX;
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
    }

    public int getXForCenteredText(String text)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public int getXForAlignToRightText(String text, int tailX)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
