package cs102a.aeroplane.savegame;

import java.io.File;
import java.util.Scanner;

public class GameLoader {
    private static int fileNumber;

    // 接受前端传入的文件名信息（存档编号）
    public static void setFileNumber(int fileNumber) {
        GameLoader.fileNumber = fileNumber;
    }

    private static void errorChecker() throws Exception {

        String fileDict = SystemSelect.isMacOS() ?
                SystemSelect.getMacHistoryPath() : SystemSelect.getWindowsHistoryPath();
        String filePath = String.format("%s%d.aeroplane", fileDict, fileNumber);

        Scanner sc = new Scanner(new File(filePath));
        int
    }
}
