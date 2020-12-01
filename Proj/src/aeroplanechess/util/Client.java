package aeroplanechess.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static final String LOCAL_IP = "10.25.50.131";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(LOCAL_IP, Server.MDK);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        // TODO: 2020/12/1 改为判断游戏没有结束
        while (true) {

            // TODO: 2020/12/1 这里用buf存储对方改动的状态缓存
            byte[] buff = new byte[1024];
            int len = in.read(buff);

            // TODO: 2020/12/1 `receive` ~> 获取的对方改动：以某种数据结构
            String receive = new String(buff, 0, len);

            // TODO: 2020/12/1 `line` -> 改为传出数据：当前移动棋子代号 & 新位置/状态
            String line = "sth to output";
            out.write(line.getBytes());
        }
    }
}
