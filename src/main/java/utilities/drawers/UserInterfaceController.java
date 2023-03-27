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
    private final PlayerStatsDrawer playerStatsDrawer;

    public UserInterfaceController(GamePanel gp)
    {
        this.gp = gp;
        pixelFont = loadFont();
        playerStatsDrawer = new PlayerStatsDrawer();
    }

    public void draw(Graphics2D g2)
    {
        long drawStart = System.nanoTime();
        UserInterfaceController.g2 = g2;

        GameState gameState = gp.getGameState();

        if (playableStates.contains(gameState))
        {
            playerStatsDrawer.drawPlayerStats(gp.player);
        }

        switch (gp.getGameState())
        {
            case HOME_STATE -> HomeScreenDrawer.drawHomeScreen();
            case HELP_STATE -> HelpScreenDrawer.drawHelpScreen();
            case DIALOGUE_STATE -> DialogueWindowDrawer.drawDialogueScreen();
            case OPTIONS_STATE -> OptionsWindowDrawer.drawOptionWindow();
            case PLAY_STATE ->
            {
                GameTimeDrawer.drawTime();
                MessageDrawer.drawMessage();
            }
            case PAUSE_STATE -> drawPauseStateScreen();
            case CHARACTER_STATE ->
            {
                CharacterWindowDrawer.drawCharacterWindow(gp.player);
                InventoryWindowDrawer.drawInventoryWindow(gp.player);
                MessageDrawer.drawMessage();
            }
            case GAME_WIN_STATE -> drawGameWinScreen();
            case GAME_LOSE_STATE -> drawGameLoseScreen();
        }
        if (shouldShowCoordinates)
        {
            showPlayerCoordinates(drawStart, gp.player);
        }
    }

    private static void drawPauseStateScreen()
    {
        drawCenteredText("PAUSED", 4.5F, false, 80F);
        drawCenteredText("QUIT GAME", 8.5F, true, 36F);
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

    private void drawGameWinScreen()
    {
        EndScreenDrawer.drawGameWinScreen(playTime);
        gp.gameThread.interrupt();
    }

    private void drawGameLoseScreen()
    {
        EndScreenDrawer.drawGameLoseScreen(playTime);
        gp.gameThread.interrupt();
    }
}
