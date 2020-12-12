import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {

    public static Settings settingsFrame = new Settings("游戏设置");


    public Settings(String title) {

        this.setTitle(title);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        JPanel backgroundPanel = new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        backgroundPanel.setOpaque(false);
        backgroundPanel.setLayout(new GridLayout(3, 1, 10, 10));
        backgroundPanel.setPreferredSize(new Dimension(150, 150));


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


        JLabel onlineLabel = new JLabel("联机模式：关");

        JButton changeOnlineMode = new JButton("-> 开");
        changeOnlineMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameInfo.isIsOnlineGame()) {
                    changeOnlineMode.setText("-> 开");
                    onlineLabel.setText("联机模式：关");
                    GameInfo.setIsOnlineGame(true);
                } else {
                    changeOnlineMode.setText("-> 关");
                    onlineLabel.setText("联机模式：开");
                    GameInfo.setIsOnlineGame(false);
                }
            }
        });


        JLabel humanCntLabel = new JLabel("人类玩家数：");

        JRadioButton rb1 = new JRadioButton("1");
        JRadioButton rb2 = new JRadioButton("2");
        JRadioButton rb3 = new JRadioButton("3");
        JRadioButton rb4 = new JRadioButton("4", true);
        rb1.setOpaque(false);
        rb2.setOpaque(false);
        rb3.setOpaque(false);
        rb4.setOpaque(false);

        ButtonGroup humanCntSelection = new ButtonGroup();
        humanCntSelection.add(rb1);
        humanCntSelection.add(rb2);
        humanCntSelection.add(rb3);
        humanCntSelection.add(rb4);


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


        JPanel rowPanel1 = new JPanel();
        rowPanel1.setLayout(new GridLayout(2, 1, 10, 10));
        rowPanel1.add(themeLabel);
        rowPanel1.add(themeSettings);
        rowPanel1.setOpaque(false);
        rowPanel1.setPreferredSize(new Dimension(400, 150));

        JPanel rowPanel2 = new JPanel();
        rowPanel2.setLayout(new GridLayout(1, 5, 5, 10));
        rowPanel2.add(humanCntLabel);
        rowPanel2.add(rb1);
        rowPanel2.add(rb2);
        rowPanel2.add(rb3);
        rowPanel2.add(rb4);
        rowPanel2.setOpaque(false);
        rowPanel2.setPreferredSize(new Dimension(600, 300));

        JPanel rowPanel3 = new JPanel();
        rowPanel3.setLayout(new GridLayout(1, 1, 10, 10));
        rowPanel3.add(enterSuperMode);
        rowPanel3.setOpaque(false);
        rowPanel3.setPreferredSize(new Dimension(400, 150));

        JPanel setSizepanel = new JPanel(new GridLayout(3, 1, 10, 50));
        setSizepanel.setPreferredSize(new Dimension(800, 600));
        backgroundPanel.add(rowPanel1);
        backgroundPanel.add(rowPanel2);
        backgroundPanel.add(rowPanel3);
        setSizepanel.add(backgroundPanel);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.add(backgroundPanel);
    }

    public static void popsetting() {
        settingsFrame.setVisible(true);
    }
}