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

    private static int theme=1;

    public static int getTheme() {
        return theme;
    }

    public static void setTheme(int theme) {
        GameInfo.theme = theme;
    }
}
