package utilities.drawers;

import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static utilities.GameState.*;
import static utilities.drawers.DrawerUtils.drawCenteredText;

public class UIController
{
    protected static Graphics2D g2;
    public static Font pixelFont;
    public static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public static String currentDialogue = "";

    private final Map<String, Long> messagesWIthTheirExpirationTime = new ConcurrentHashMap<>();
    public boolean gameFinished = false;
    public double playTime;
    private final GamePanel gp;
    private final HomeScreenDrawer homeScreenDrawer;
    private final HelpScreenDrawer helpScreenDrawer;
    private final PlayerLifeDrawer playerLifeDrawer;
    private final DialogueWindowDrawer dialogueWindowDrawer;
    private final CharacterWindowDrawer characterWindowDrawer;
    private final EndScreenDrawer endScreenDrawer;

    public HomeScreenDrawer getHomeScreenDrawer()
    {
        return homeScreenDrawer;
    }

    public UIController(GamePanel gp)
    {
        this.gp = gp;
        pixelFont = loadFont();
        homeScreenDrawer = new HomeScreenDrawer();
        helpScreenDrawer = new HelpScreenDrawer();
        playerLifeDrawer = new PlayerLifeDrawer(gp);
        dialogueWindowDrawer = new DialogueWindowDrawer();
        characterWindowDrawer = new CharacterWindowDrawer(gp);
        endScreenDrawer = new EndScreenDrawer();
    }

    public void draw(Graphics2D g2)
    {
        UIController.g2 = g2;

        if (gp.getGameState() == HOME_STATE)
        {
            homeScreenDrawer.drawHomeScreen();
        }
        if (gp.getGameState() == HELP_STATE)
        {
            helpScreenDrawer.drawHelpScreen();
        }
        if (gp.getGameState() == DIALOGUE_STATE)
        {
            playerLifeDrawer.drawPlayerLife();
            dialogueWindowDrawer.drawDialogueScreen();
        }
        if (gp.getGameState() == PLAY_STATE)
        {
            if (gameFinished)
            {
                endScreenDrawer.drawEndScreen(playTime);
                stopGame();
            }

            playerLifeDrawer.drawPlayerLife();
            drawTime();
            drawMessage();

        }
        if (gp.getGameState() == PAUSE_STATE)
        {
            playerLifeDrawer.drawPlayerLife();
            drawCenteredText("PAUSED", 8, true, 80F);
        }
        if (gp.getGameState() == CHARACTER_STATE)
        {
            playerLifeDrawer.drawPlayerLife();
            characterWindowDrawer.drawCharacterScreen();
        }
    }

    public void addMessage(String text)
    {
        messagesWIthTheirExpirationTime.put(text, System.currentTimeMillis());
    }

    private Font loadFont()
    {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("font/prstartk.ttf"))
        {
            return Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(stream)).deriveFont(14f);
        }
        catch (FontFormatException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void stopGame()
    {
        gp.gameThread = null;
    }

    private void drawTime()
    {
        UIController.g2.setColor(Color.white);
        playTime += (double) 1 / 60;
        UIController.g2.drawString("Time: " + decimalFormat.format(playTime), GamePanel.tileSize * 11, GamePanel.tileSize);
    }

    public void drawMessage()
    {
        AtomicInteger messageY = new AtomicInteger(11);

        messagesWIthTheirExpirationTime.forEach((message, timeAdded) ->
        {
            drawCenteredText(message, messageY.get(), false, 12F);
            if ((System.currentTimeMillis() - timeAdded) / 1000 > 3)
            {
                messagesWIthTheirExpirationTime.remove(message);
            }
            messageY.addAndGet(-1);
        });
    }
}
