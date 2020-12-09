package cs102a.aeroplane.savegame;

import cs102a.aeroplane.util.SystemSelect;

import java.io.*;
import java.util.Objects;

public class GameSaver {
    public void recordStep() {
        BufferedWriter bufferedWriter = null;

        String fileDict = SystemSelect.isMacOS() ?
                SystemSelect.getMacHistoryPath() : SystemSelect.getWindowsHistoryPath();
        String filePath = String.format("%s%d.aeroplane", fileDict,
                Objects.requireNonNull(new File(fileDict).listFiles()).length + 1);


        // TODO: 2020/12/3 每一步写入的数据
        String stepInfo = String.format(
                        "@@@\n" +
                        "@STEP=%d\n" +
                        "@MOVING_PLAYER=%d\n" +
                        "@@PLANE_POSITION\n" +
                        "PLANE1,%d,%d\n" +
                        "PLANE2,%d,%d\n" +
                        "PLANE3,%d,%d\n" +
                        "PLANE4,%d,%d\n" +
                        "PLANE5,%d,%d\n" +
                        "PLANE6,%d,%d\n" +
                        "PLANE7,%d,%d\n" +
                        "PLANE8,%d,%d\n" +
                        "PLANE9,%d,%d\n" +
                        "PLANE10,%d,%d\n" +
                        "PLANE11,%d,%d\n" +
                        "PLANE12,%d,%d\n" +
                        "PLANE13,%d,%d\n" +
                        "PLANE14,%d,%d\n" +
                        "PLANE15,%d,%d\n" +
                        "PLANE16,%d,%d\n",
                1);

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(filePath), true)));
            // 追加单步数据
            bufferedWriter.write(stepInfo);
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
}
