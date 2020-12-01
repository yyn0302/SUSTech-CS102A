package aeroplanechess.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 服务器，存储，上下载四个客户机的数据
 */
public class Server {
    public ArrayList list = new ArrayList();
    public static final int MDK = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(MDK);
        Socket client = server.accept();
        System.out.println("Successfully connected...");
        InetAddress address = client.getInetAddress();
        System.out.println(address.getHostAddress());

        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        // TODO: 2020/12/1 改为判断游戏没有结束
        while (true) {
            String send = "本地改变, 待上传";
            out.write(send.getBytes());

            System.out.println("等待对方落子");
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String receive = new String(buf, 0, len);

        }
    }
}
