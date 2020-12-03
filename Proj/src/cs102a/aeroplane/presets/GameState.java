package cs102a.aeroplane.presets;

public enum GameState {
    // 整体游戏状态
    LOCAL_4P(0, 4, 0),
    LOCAL_1P3C(0, 1, 3),
    LOCAL_2P2C(0, 2, 2),
    LOCAL_3P1C(0, 3, 1),

    ONLINE_4P(1, 4, 0),
    ONLINE_1P3C(1, 1, 3),
    ONLINE_2P2C(1, 2, 2),
    ONLINE_3P1C(1, 3, 1),


    // 玩家状态
    PEOPLE(true),
    COMPUTER(false),

    // 游戏状态
    GAME_READY(0),
    GAME_START(1),
    GAME_END(2);


    private int isOnline;       // 本地游戏 0, 在线游戏 1
    private int peopleCnt;      // 本场玩家中，人的数量 1-4
    private int computerCnt;    // 本场玩家中，AI的数量 0-3

    private boolean isPeople;   // 操作者是人，否则为AI

    private int gameStat;

    GameState(int isOnline, int peopleCnt, int computerCnt) {
        this.isOnline = isOnline;
        this.peopleCnt = peopleCnt;
        this.computerCnt = computerCnt;
    }

    GameState(boolean isPeople) {
        this.isPeople = isPeople;
    }

    GameState(int gameStat) {
        this.gameStat = gameStat;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public int getComputerCnt() {
        return computerCnt;
    }

    public boolean isPeople() {
        return isPeople;
    }

    public int getGameStat() {
        return gameStat;
    }
}
