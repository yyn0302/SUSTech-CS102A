package cs102a.aeroplane.savegame;

import java.io.*;

public class GameSaver extends Thread{

    public static void main(String[] args) {
        Thread s=new GameSaver();
        s.start();
    }

    // 在每走一步同时写入数据，追加到后面
    @Override
    public void run() {
        BufferedWriter bufferedWriter = null;
        // String filePath=String.format("1.aeroplane");
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                    new FileOutputStream(new File("Proj/src/cs102a/aeroplane/savegame/history/i.aeroplane"), true)));
            bufferedWriter.write("a");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
