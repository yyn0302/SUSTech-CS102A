package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {
    private static final JFrame startFrame = new JFrame("飞行棋");

    public static void main(String[] args) {
        startFrame.setLocationRelativeTo(null);
        JLabel title = new JLabel("飞行棋");
        title.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 30));
        title.setBounds(0, 0, 100, 50);
        title.setForeground(Color.white);
        title.setBackground(Color.blue);
        title.setOpaque(true);

        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(e -> {
            startFrame.setVisible(false);
            GameGUI.window.setVisible(true);
        });

        JButton continueButton = new JButton("继续游戏");
        continueButton.addActionListener(e -> {
            startFrame.setVisible(false);
            LoadHistory loadHistory = new LoadHistory("读档");
            loadHistory.setVisible(true);
        });

        JButton storeButton = new JButton("道具商店");
        storeButton.addActionListener(e -> {
            GameMall.window.setVisible(true);
        });

        JButton settingButton = new JButton("游戏设置");
        settingButton.addActionListener(e -> {
            startFrame.setVisible(true);
            Settings.window.setVisible(true);
        });


        JPanel subStartPanel = new JPanel();
        subStartPanel.setLayout(new GridLayout(5, 1, 10, 20));
        subStartPanel.setPreferredSize(new Dimension(150, 150));
        subStartPanel.setBackground(null);
        subStartPanel.setOpaque(false);
        subStartPanel.add(title);
        subStartPanel.add(startButton);
        subStartPanel.add(continueButton);
        subStartPanel.add(storeButton);
        subStartPanel.add(settingButton);

        String picPath = SystemSelect.getImagePath();
        JPanel startPanel = new BackgroundPanel((new ImageIcon(picPath + "开始图片.jpg").getImage()));
        startPanel.add(subStartPanel);
        startFrame.add(startPanel);
        startFrame.setSize(800, 600);

        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void popStart() {
        startFrame.setVisible(true);
    }
}
