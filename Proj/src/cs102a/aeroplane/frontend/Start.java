package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {
    private static final JFrame startFrame = new JFrame("飞行棋");

    public static void main(String[] args) {
        JLabel title = new JLabel("飞行棋",JLabel.CENTER);
        title.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 50));
        title.setForeground(Color.CYAN);
        title.setOpaque(false);

        JButton startButton = new JButton("开始游戏");
        startButton.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 16));
        startButton.addActionListener(e -> {
            startFrame.setVisible(false);
            GameGUI.window.setVisible(true);
        });

        JButton continueButton = new JButton("继续游戏");
        continueButton.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 16));
        continueButton.addActionListener(e -> {
            startFrame.setVisible(false);
            LoadHistory loadHistory = new LoadHistory("读档");
            loadHistory.window.setVisible(true);
        });

        JButton storeButton = new JButton("道具商店");
        storeButton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 16));
        storeButton.addActionListener(e -> GameMall.window.setVisible(true));

        JButton settingButton = new JButton("游戏设置");
        settingButton.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 16));
        settingButton.addActionListener(e -> {
            startFrame.setVisible(true);
            Settings.window.setVisible(false);
        });


        JPanel subStartPanel = new JPanel();
        subStartPanel.setLayout(new GridLayout(5, 1, 10, 40));
        subStartPanel.setPreferredSize(new Dimension(300, 500));
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
        startFrame.setSize(400, 600);

        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void popStart() {
        startFrame.setVisible(true);
    }
}
