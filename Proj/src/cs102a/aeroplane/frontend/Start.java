package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start {
    public static void main(String[] args) {

        JFrame startFrame = new JFrame("飞行棋");


        JLabel title = new JLabel("飞行棋");
        title.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setBackground(Color.blue);
        title.setOpaque(true);

        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                GameGUI.gameGUI.setVisible(true);
            }
        });

        JButton continueButton = new JButton("继续游戏");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                LoadHistory.loadHistory.setVisible(true);
            }
        });

        JButton storeButton = new JButton("道具商店");
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(false);
                GameMall.gameMall.setVisible(true);
            }
        });

        JButton settingButton = new JButton("游戏设置");
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(true);
                Settings.settingsFrame.setVisible(true);
            }
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

        String picPath = SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath();
        JPanel startPanel = new BackgroundPanel((new ImageIcon(picPath + "开始图片.jpg").getImage()));
        startPanel.add(subStartPanel);
        startFrame.add(startPanel);
        startFrame.setSize(800, 600);

        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
