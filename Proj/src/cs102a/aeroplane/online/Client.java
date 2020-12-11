package cs102a.aeroplane.online;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public String receive(String host, int s) {
        Socket client;
        BufferedReader br;
        String output;
        try {
            client = new Socket(host, 8080 + s);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = br.readLine();
            br.close();
            client.close();
        } catch (Exception e) {
            output = "@@Error";
        }
        return output;
    }

    public static void getAndApplyChange() {
//        加标识符
    }

    public static void updateRecordedChange() {

    }

    public static void notifyNewWinner() {

    }
}
