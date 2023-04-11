package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilities.drawers.CoordinatesDrawer.shouldShowCoordinates;

public class GameStateHandler implements KeyListener
{
    private final GamePanel gp;
    public final HomeStateHandler homeStateHandler;
    public final HelpStateHandler helpStateHandler;
    public final PauseStateHandler pauseStateHandler;
    public final DialogueStateHandler dialogueStateHandler;
    public final InventoryKeyboardHandler inventoryKeyboardHandler;
    public final PlayStateHandler playStateHandler;
    public final OptionsStateHandler optionsStateHandler;
    public final EndStateHandler endStateHandler;
    public final TradeStateHandler tradeStateHandler;

    public GameStateHandler(GamePanel gp)
    {
        this.gp = gp;
        this.homeStateHandler = new HomeStateHandler(gp);
        this.helpStateHandler = new HelpStateHandler(gp);
        this.pauseStateHandler = new PauseStateHandler(gp);
        this.dialogueStateHandler = new DialogueStateHandler(gp);
        this.inventoryKeyboardHandler = new InventoryKeyboardHandler(gp);
        this.playStateHandler = new PlayStateHandler(gp);
        this.optionsStateHandler = new OptionsStateHandler(gp);
        this.endStateHandler = new EndStateHandler(gp);
        this.tradeStateHandler = new TradeStateHandler(gp);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        switch (gp.getGameState())
        {
            case HOME_STATE -> homeStateHandler.handleHomeStateEvent(event);
            case HELP_STATE -> helpStateHandler.handleHelpStateEvent(event);
            case PAUSE_STATE -> pauseStateHandler.handlePauseStateEvent(event);
            case PLAY_STATE -> playStateHandler.handlePlayStateEventOnButtonPress(event);
            case DIALOGUE_STATE -> dialogueStateHandler.handleDialogueStateEvent(event);
            case CHARACTER_STATE -> inventoryKeyboardHandler.handleInventoryEvent(event, gp.player.inventory);
            case OPTIONS_STATE -> optionsStateHandler.handleOptionsStateEvent(event);
            case GAME_OVER_STATE, GAME_WIN_STATE -> endStateHandler.handleEndStateEvent(event);
            case TRADE_STATE -> tradeStateHandler.handleTradeStateEvent(event, inventoryKeyboardHandler);
        }
        if (event.getKeyCode() == KeyEvent.VK_O)
        {
            shouldShowCoordinates = !shouldShowCoordinates;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        playStateHandler.handlePlayStateEventOnButtonRelease(event);
    }
}
