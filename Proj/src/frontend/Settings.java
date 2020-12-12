package frontend;

import cs102a.aeroplane.GameInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {

    public static JFrame settingsFrame = new JFrame("设置");


    public Settings() {

        settingsFrame.setSize(800, 600);
        settingsFrame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        backgroundPanel.setOpaque(false);
        backgroundPanel.setLayout(new GridLayout(2, 1, 10, 10));
        backgroundPanel.setPreferredSize(new Dimension(800, 600));


        JLabel themeLabel = new JLabel("当前主题：海王");

        JButton themeSettings = new JButton("-> 灵笼主题");
        themeSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameInfo.getTheme() == 1) {
                    themeSettings.setText("-> 海王主题");
                    themeLabel.setText("当前主题：灵笼");
                    GameInfo.setTheme(2);
                } else {
                    themeSettings.setText("-> 灵笼主题");
                    themeLabel.setText("当前主题：海王");
                    GameInfo.setTheme(1);
                }
            }
        });


        JLabel modeLabel = new JLabel("当前模式：正常");

        JButton changeCheatMode = new JButton("-> 作弊模式");
        changeCheatMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameInfo.isIsCheatMode()) {
                    changeCheatMode.setText("-> 正常模式");
                    modeLabel.setText("当前模式：作弊");
                    GameInfo.setIsCheatMode(true);
                } else {
                    changeCheatMode.setText("-> 作弊模式");
                    modeLabel.setText("当前模式：正常");
                    GameInfo.setIsCheatMode(false);
                }
            }
        });


        JButton enterSuperMode = new JButton("注册管理权限");
        enterSuperMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameInfo.isSuperUser()) {
                    EnterSuperMode.enterSuperMode.setVisible(true);
                    if (EnterSuperMode.isRightPwd()) {
                        enterSuperMode.setText("已获取管理权限");
                        enterSuperMode.setEnabled(false);
                        GameInfo.setSuperUser(true);
                    }
                }
            }
        });


        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(2, 2, 10, 10));
        upPanel.add(themeLabel);
        upPanel.add(themeSettings);
        upPanel.add(modeLabel);
        upPanel.add(enterSuperMode);

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1, 1, 10, 10));
        downPanel.add(enterSuperMode);


        backgroundPanel.add(upPanel);
        backgroundPanel.add(downPanel);

        settingsFrame.setDefaultCloseOperation(settingsFrame.HIDE_ON_CLOSE);
        settingsFrame.add(backgroundPanel);
    }
}
