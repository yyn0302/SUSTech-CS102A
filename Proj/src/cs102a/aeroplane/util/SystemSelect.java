package cs102a.aeroplane.util;

public class SystemSelect {
    // 判断操作系统，适配windows和macos
    private final static String OS = System.getProperty("os.name").toLowerCase();

    private final static String macHistoryPath = "Proj/src/cs102a/aeroplane/savegame/history/";
    private final static String windowsHistoryPath = "Proj\\src\\cs102a\\aeroplane\\savegame\\history\\";

    private final static String macMusicPath = "Proj/src/cs102a/aeroplane/resources/audio/";
    private final static String windowsMusicPath = "Proj\\src\\cs102a\\aeroplane\\resources\\audio\\";

    public static String getMacImagePath() {
        return macImagePath;
    }

    public static String getWindowsImagePath() {
        return windowsImagePath;
    }

    private final static String macImagePath = "Proj/src/cs102a/aeroplane/resources/image/";
    private final static String windowsImagePath = "Proj\\src\\cs102a\\aeroplane\\resources\\image\\";


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