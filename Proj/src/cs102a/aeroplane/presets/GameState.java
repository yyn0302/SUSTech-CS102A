package cs102a.aeroplane.presets;

public class GameState {
    // 整体游戏状态
//    public static final GameState LOCAL_1P = new GameState(false, 1, 3);
//    public static final GameState LOCAL_2P = new GameState(false, 2, 2);
//    public static final GameState LOCAL_3P = new GameState(false, 3, 1);
//    public static final GameState LOCAL_4P = new GameState(false, 4, 0);
//
//    public static final GameState ONLINE_1P = new GameState(true, 1, 3);
//    public static final GameState ONLINE_2P = new GameState(true, 2, 2);
//    public static final GameState ONLINE_3P = new GameState(true, 3, 1);
//    public static final GameState ONLINE_4P = new GameState(true, 4, 0);
    public static final int ONLINE=1;
    public static final int LOCAL=0;

    public static final int COMPUTER=1;
    public static final int HUMAN=0;


    // 玩家状态
//    public static final GameState IS_PEOPLE=new GameState(true);
//    public static final GameState IS_COMPUTER=new GameState(true);

    // 游戏状态
    public static final GameState GAME_READY=new GameState(0);
    public static final GameState GAME_START=new GameState(1);
    public static final GameState GAME_END=new GameState(2);


    private boolean isOnline;   // 本地游戏, 在线游戏
    private int peopleCnt;      // 本场玩家中，人的数量 1-4
    private int computerCnt;    // 本场玩家中，AI的数量 0-3

//    private boolean isPeople;   // 操作者是人，否则为AI

    private int gameStat;

    GameState(boolean isOnline, int peopleCnt, int computerCnt) {
        this.isOnline = isOnline;
        this.peopleCnt = peopleCnt;
        this.computerCnt = computerCnt;
    }

//    GameState(boolean isPeople) {
//        this.isPeople = isPeople;
//    }

    GameState(int gameStat) {
        this.gameStat = gameStat;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public int getComputerCnt() {
        return computerCnt;
    }

//    public boolean isPeople() {
//        return isPeople;
//    }

    public int getGameStat() {
        return gameStat;
    }
}
