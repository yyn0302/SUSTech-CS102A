package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    public static void main(String[] args) {

        JFrame startFrame = new JFrame("飞行棋");

        String picPath = SystemSelect.isMacOS() ?
                SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath();

        JPanel startPanel = new BackgroundPanel((new ImageIcon(picPath + "开始图片.jpg").getImage()));
        JPanel subStartPanel = new JPanel();

        subStartPanel.setBackground(null);
        subStartPanel.setOpaque(false);
        JButton startButton = new JButton("开始游戏");
        JButton continueButton = new JButton("继续游戏");
        JButton settingButton = new JButton("游戏设置");
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.setVisible(true);
                Settings.settingsFrame.setVisible(true);
            }
        });
        //加上三个button
        subStartPanel.add(startButton);
        subStartPanel.add(continueButton);
        subStartPanel.add(settingButton);
        subStartPanel.setLayout(new GridLayout(3, 1, 10, 30));
        subStartPanel.setPreferredSize(new Dimension(150, 150));
        startPanel.add(subStartPanel);
        startFrame.add(startPanel);
        startFrame.setSize(800, 600);
        startFrame.setVisible(true);
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
