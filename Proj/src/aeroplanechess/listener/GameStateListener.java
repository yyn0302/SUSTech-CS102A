package aeroplanechess.listener;

// TODO: 2020/12/1 amend it
public interface GameStateListener extends Listener {
    void onPlayerStartRound(int player);

    void onPlayerEndRound(int player);
}
