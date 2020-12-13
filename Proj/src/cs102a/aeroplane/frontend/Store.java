package cs102a.aeroplane.frontend;

import javax.swing.*;

public class Store extends JFrame {
    public static Store store=new Store();

    public static int getAsPlayer() {
        return asPlayer;
    }

    public static void setAsPlayer(int asPlayer) {
        Store.asPlayer = asPlayer;
    }

    private static int asPlayer;
}
