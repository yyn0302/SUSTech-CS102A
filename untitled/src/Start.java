import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {
    public static void main(String[] args) {

        JFrame startFrame = new JFrame("飞行棋");


        JLabel title = new JLabel("飞行棋");
        title.setFont(new Font("微软雅黑", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setBackground(Color.blue);
        title.setOpaque(true);

        JButton startButton = new JButton("开始游戏");

        JButton continueButton = new JButton("继续游戏");

        JButton storeButton = new JButton("道具商店");

        JButton settingButton = new JButton("游戏设置");


        JPanel subStartPanel = new JPanel();
        subStartPanel.setLayout(new GridLayout(5, 1, 0, 20));
        subStartPanel.setPreferredSize(new Dimension(150, 150));
        subStartPanel.setBackground(null);
        subStartPanel.setOpaque(false);
        subStartPanel.add(title);
        subStartPanel.add(startButton);
        subStartPanel.add(continueButton);
        subStartPanel.add(storeButton);
        subStartPanel.add(settingButton);

        JPanel startPanel = new BackgroundPanel((new ImageIcon("ss.jpg").getImage()));
        startPanel.add(subStartPanel);
        startFrame.add(startPanel);
        startFrame.setSize(800, 600);

        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
