package cs102a.aeroplane.online;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Server {

    private Socket socket;

    protected InputStream inputStream;
    protected OutputStream outputStream;


    public Client(String ip) {
        try {
            socket = new Socket(ip, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 FIXME: 2020/12/12
    public static void getAndApplyChange() {
//        加标识符
    }
//
//    public static void updateRecordedChange() {
//
//    }
//
//    public static void notifyNewWinner() {
//
//    }
//
//    public static int getDeltaPort() {
//        return deltaPort;
//    }
//
//    public static void setDeltaPort(int deltaPort) {
//        Client.deltaPort = deltaPort;
//    }
//
//    private static int deltaPort;   // 0 to 3
}
