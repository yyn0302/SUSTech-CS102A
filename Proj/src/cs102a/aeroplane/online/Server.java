package cs102a.aeroplane.online;

//import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.ArrayList;


public class Server {
    // output 为当前移动的棋子 或广播：游戏结束
    public void Server(String output, int s) {
        ServerSocket server;
        Socket client;
//        PrintWriter out;
        try {
            server = new ServerSocket(8080 + s);
            client = server.accept();
            System.out.println("Successfully connected...");
            InetAddress address = client.getInetAddress();

//            out = new PrintWriter(client.getOutputStream(), true);
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            out.write(output.getBytes());

            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String receive = new String(buf, 0, len);
            // todo 本地接受receive的数据并读档改变
            client.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//public class Server {
//    public ArrayList list = new ArrayList();
//    public static final int MDK = 8080;

//    public static void main(String[] args) throws IOException {
//        ServerSocket server = new ServerSocket(MDK);
//        Socket client = server.accept();
//        System.out.println("Successfully connected...");
//        InetAddress address = client.getInetAddress();
//        System.out.println(address.getHostAddress());

//        InputStream in = client.getInputStream();
//        OutputStream out = client.getOutputStream();

        // TODO: 2020/12/1 改为判断游戏没有结束
//        while (true) {
//            String send = "本地改变, 待上传";
//            out.write(send.getBytes());

//            System.out.println("等待对方落子");
//            byte[] buf = new byte[1024];
//            int len = in.read(buf);
//            String receive = new String(buf, 0, len);
//
//        }
//    }
//}
