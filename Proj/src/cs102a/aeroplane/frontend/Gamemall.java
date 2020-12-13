package cs102a.aeroplane.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gamemall extends JFrame {
    public static Gamemall gamemall=new Gamemall("道具商店");
public Gamemall(String title) {
    this.setTitle(title);
    ImageIcon bomb=new ImageIcon("src\\炸弹.jpg");
    ImageIcon VIP=new ImageIcon("src\\VIP.jpg");
    ImageIcon boyin=new ImageIcon("src\\波音.jpg");
    JLabel bombJLabel=new JLabel(bomb);
    JLabel VIPJLabel=new JLabel(VIP);
    JLabel boyinJLabel=new JLabel(boyin);
    JLabel bombLabel=new JLabel("炸弹");
    JLabel VIPlabel=new JLabel("VIP");
    JLabel boyinLabel=new JLabel("无条件起飞");
    JButton buy=new JButton("购买");
    buy.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Pay.popPay();
        }
    });
    JPanel panel=new JPanel();
    panel.setLayout(new GridLayout(4,2,20,30));
    panel.setPreferredSize(new Dimension(600,600));
    panel.add(boyinJLabel);
    panel.add(boyinLabel);
    panel.add(bombJLabel);
    panel.add(bombLabel);
    panel.add(VIPJLabel);
    panel.add(VIPlabel);
    this.add(panel);
    this.setSize(800,600);
    this.setLayout(new GridLayout(3,2,20,30));
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout());
}
    public static void main(String[] args) {
        gamemall.setVisible(true);
    }
    public static void popgamemall() {
        gamemall.setVisible(true);
    }
}
