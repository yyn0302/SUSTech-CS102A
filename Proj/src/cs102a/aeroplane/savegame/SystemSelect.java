package cs102a.aeroplane.savegame;

public class SystemSelect {
    // 判断操作系统，适配windows和macos
    private final static String OS = System.getProperty("os.name").toLowerCase();

    private final static String macHistoryPath = "Proj/src/cs102a/aeroplane/savegame/history/";
    private final static String windowsHistoryPath = "Proj\\src\\cs102a\\aeroplane\\savegame\\history\\";

    private final static String macMusicPath = "Proj/src/cs102a/aeroplane/resources/audio/";
    private final static String windowsMusicPath = "Proj\\src\\cs102a\\aeroplane\\resources\\audio\\";


    public static boolean isMacOS() {
        return OS.contains("mac");
    }

    public static String getMacHistoryPath() {
        return macHistoryPath;
    }

    public static String getWindowsHistoryPath() {
        return windowsHistoryPath;
    }

    public static String getMacMusicPath() {
        return macMusicPath;
    }

    public static String getWindowsMusicPath() {
        return windowsMusicPath;
    }
}