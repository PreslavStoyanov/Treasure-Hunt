package utilities;

import java.util.List;

public enum GameState
{
    HOME_STATE,
    PLAY_STATE,
    PAUSE_STATE,
    OPTIONS_STATE,
    HELP_STATE,
    DIALOGUE_STATE,
    CHARACTER_STATE,
    GAME_LOSE_STATE,
    GAME_WIN_STATE;

    public static final List<GameState> playableStates = List.of(PLAY_STATE, PAUSE_STATE, DIALOGUE_STATE, CHARACTER_STATE);
}
