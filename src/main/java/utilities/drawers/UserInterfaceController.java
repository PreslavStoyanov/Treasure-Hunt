package utilities.drawers;

import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import java.util.Objects;


import static utilities.GameState.*;
import static utilities.drawers.CoordinatesDrawer.shouldShowCoordinates;
import static utilities.drawers.CoordinatesDrawer.showPlayerCoordinates;
import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.GameTimeDrawer.playTime;
import static utilities.drawers.MessageDrawer.messagesWIthTheirExpirationTime;

public class UserInterfaceController
{
    public static Font pixelFont;
    protected static Graphics2D g2;

    private final GamePanel gp;
    private final PlayerLifeDrawer playerLifeDrawer;
    public boolean gameFinished = false;

    public UserInterfaceController(GamePanel gp)
    {
        this.gp = gp;
        pixelFont = loadFont();
        playerLifeDrawer = new PlayerLifeDrawer();
    }

    public void draw(Graphics2D g2)
    {
        long drawStart = System.nanoTime();
        UserInterfaceController.g2 = g2;

        if (gp.getGameState() == HOME_STATE)
        {
            HomeScreenDrawer.drawHomeScreen();
        }
        if (gp.getGameState() == HELP_STATE)
        {
            HelpScreenDrawer.drawHelpScreen();
        }
        if (gp.getGameState() == DIALOGUE_STATE)
        {
            playerLifeDrawer.drawPlayerLife(gp.player);
            DialogueWindowDrawer.drawDialogueScreen();
        }
        if (gp.getGameState() == PLAY_STATE)
        {
            if (gameFinished)
            {
                EndScreenDrawer.drawEndScreen(playTime);
                stopGame();
            }

            playerLifeDrawer.drawPlayerLife(gp.player);
            GameTimeDrawer.drawTime();
            MessageDrawer.drawMessage();
        }
        if (gp.getGameState() == PAUSE_STATE)
        {
            playerLifeDrawer.drawPlayerLife(gp.player);
            drawCenteredText("PAUSED", 8, true, 80F);
        }
        if (gp.getGameState() == CHARACTER_STATE)
        {
            playerLifeDrawer.drawPlayerLife(gp.player);
            CharacterWindowDrawer.drawCharacterScreen(gp.player);
        }
        if (shouldShowCoordinates)
        {
            showPlayerCoordinates(drawStart, gp.player);
        }
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
}
