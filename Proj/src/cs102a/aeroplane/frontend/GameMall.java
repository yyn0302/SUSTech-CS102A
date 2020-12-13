package cs102a.aeroplane.frontend;

import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMall extends JFrame {

    public static GameMall gamemall = new GameMall("道具商店");

    private static int asPlayer;


    public GameMall(String title) {

        this.setTitle(title);

        String path= SystemSelect.isMacOS()?SystemSelect.getMacImagePath():SystemSelect.getWindowsImagePath();
        ImageIcon bomb = new ImageIcon(path+"炸弹.jpg");
        ImageIcon VIP = new ImageIcon(path+"VIP.jpg");
        ImageIcon boeing = new ImageIcon(path+"波音.jpg");

        JLabel bombJLabel = new JLabel(bomb);
        JLabel VIPJLabel = new JLabel(VIP);
        JLabel boeingJLabel = new JLabel(boeing);
        JLabel bombLabel = new JLabel("炸弹");
        JLabel VIPlabel = new JLabel("VIP");
        JLabel boyinLabel = new JLabel("无条件起飞");
        JButton buy = new JButton("购买");
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pay.popPay();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 20, 30));
        panel.setPreferredSize(new Dimension(600, 600));
        panel.add(boeingJLabel);
        panel.add(boyinLabel);
        panel.add(bombJLabel);
        panel.add(bombLabel);
        panel.add(VIPJLabel);
        panel.add(VIPlabel);
        this.add(panel);
        this.setSize(800, 600);
        this.setLayout(new GridLayout(3, 2, 20, 30));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());
    }

    public static void main(String[] args) {
        gamemall.setVisible(true);
    }

    public static int getAsPlayer() {
        return asPlayer;
    }

    public static void setAsPlayer(int asPlayer) {
        GameMall.asPlayer = asPlayer;
    }


}
