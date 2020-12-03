package cs102a.aeroplane.savegame;

import java.io.*;
import java.util.Objects;

public class GameSaver extends Thread {

    // 在每走一步同时写入数据，追加到后面
    @Override
    public void run() {
        BufferedWriter bufferedWriter = null;

        String fileDict = SystemSelect.isMacOS() ? SystemSelect.getMacPath() : SystemSelect.getWindowsPath();

        String filePath = String.format("%s%d.aeroplane", fileDict,
                Objects.requireNonNull(new File(fileDict).listFiles()).length + 1);


        // TODO: 2020/12/3 每一步写入的数据
        String stepInfo = String.format("!!STEP %d\n" +
                        "info",
                1);


        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File(filePath), true)));
            bufferedWriter.write(stepInfo);     // 追加单步数据
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // TODO: 2020/12/3 模块测试，记得删
    public static void main(String[] args) {
        Thread s = new GameSaver();
        s.start();
    }

}
