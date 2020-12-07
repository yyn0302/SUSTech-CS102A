package cs102a.aeroplane;

public class GameInfo {
    // TODO: 2020/12/4 开始界面中用户的设置要传入这里
    // 之后本局游戏的设置变量从这里读取


    public static void setIsCheatMode(boolean isCheatMode) {
        GameInfo.isCheatMode = isCheatMode;
    }

    public static boolean isIsCheatMode() {
        return isCheatMode;
    }

    // 若是作弊模式，用户控制的棋子不要用rollDice
    // 根据这个判断popup是放几个按钮还是放输入框
    private static boolean isCheatMode = false;

    public static int getHumanPlayerCnt() {
        return HumanPlayerCnt;
    }

    public static void setHumanPlayerCnt(int humanPlayerCnt) {
        HumanPlayerCnt = humanPlayerCnt;
    }

    private static int HumanPlayerCnt = 4;

    public static boolean isIsOnlineGame() {
        return isOnlineGame;
    }

    public static void setIsOnlineGame(boolean isOnlineGame) {
        GameInfo.isOnlineGame = isOnlineGame;
    }

    private static boolean isOnlineGame = false;




}
