package utilities.drawers;

import application.GamePanel;
import utilities.GameState;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static utilities.GameState.playableStates;
import static utilities.drawers.CoordinatesDrawer.shouldShowCoordinates;
import static utilities.drawers.CoordinatesDrawer.showPlayerCoordinates;
import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.GameTimeDrawer.playTime;

public class UserInterfaceController
{
    public static Font pixelFont;
    protected static Graphics2D g2;

    private final GamePanel gp;
    private final PlayerLifeDrawer playerLifeDrawer;

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

        GameState gameState = gp.getGameState();

        if (playableStates.contains(gameState))
        {
            playerLifeDrawer.drawPlayerLife(gp.player);
        }

        switch (gp.getGameState())
        {
            case HOME_STATE -> HomeScreenDrawer.drawHomeScreen();
            case HELP_STATE -> HelpScreenDrawer.drawHelpScreen();
            case DIALOGUE_STATE -> DialogueWindowDrawer.drawDialogueScreen();
            case PLAY_STATE ->
            {
                GameTimeDrawer.drawTime();
                MessageDrawer.drawMessage();
            }
            case PAUSE_STATE -> drawCenteredText("PAUSED", 8, true, 80F);
            case CHARACTER_STATE ->
            {
                CharacterWindowDrawer.drawCharacterWindow(gp.player);
                InventoryWindowDrawer.drawInventoryWindow(gp.player);
            }
            case END_STATE -> stopGame();
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
        EndScreenDrawer.drawEndScreen(playTime);
        gp.gameThread.interrupt();
    }
}
