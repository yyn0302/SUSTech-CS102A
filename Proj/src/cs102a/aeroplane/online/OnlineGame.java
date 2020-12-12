package cs102a.aeroplane.online;

public class OnlineGame {

    private Server server;
    private Client client;


    public void configServer() {
        server = new Server();
        try {
            server.setSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void configClient(String ip) {
        client = new Client(ip);
    }
}
