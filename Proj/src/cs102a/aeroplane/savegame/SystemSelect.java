package cs102a.aeroplane.savegame;

public class SystemSelect {
    // 判断操作系统，适配windows和macos
    private final static String OS = System.getProperty("os.name").toLowerCase();
    private final static String macPath = "Proj/src/cs102a/aeroplane/savegame/history/";
    private final static String windowsPath = "Proj\\src\\cs102a\\aeroplane\\savegame\\history\\";

    public static boolean isMacOS() {
        return OS.contains("mac");
    }

    public static String getMacPath() {
        return macPath;
    }

    public static String getWindowsPath() {
        return windowsPath;
    }
}
