package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.TimeDialog;

import javax.swing.*;
import java.awt.*;

public class EnterSuperMode extends JFrame {

    public static EnterSuperMode window = new EnterSuperMode("确认权限");

    private static final String PWD = "020924";
    private static boolean isRightPwd = false;


    public EnterSuperMode(String title) {

        this.setTitle(title);
        this.setSize(320, 200);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        JPanel base = new JPanel();
        base.setLayout(new GridLayout(2, 1, 10, 5));
        base.setPreferredSize(new Dimension(150, 150));


        JLabel tip = new JLabel("输入密码：");
        JPasswordField pwd = new JPasswordField();

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1, 2, 10, 20));
        upPanel.add(tip);
        upPanel.add(pwd);


        JButton confirm = new JButton("确定");
        confirm.addActionListener(e -> {
            try {
                if (pwd.getText().equals(PWD)) {
                    isRightPwd = true;
                    window.dispose();
                    Settings.enterSuperMode.setText("已获取管理权限");
                    Settings.enterSuperMode.setEnabled(false);
                    GameInfo.setSuperUser(true);
                } else {
                    isRightPwd = false;
                    new TimeDialog().showDialog(window, "密码错误 (pwd = 020924)", 2);
                    EnterSuperMode.window.setVisible(false);
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        });

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1, 1, 10, 10));
        downPanel.add(confirm);

        base.add(upPanel);
        base.add(downPanel);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.add(base);
    }

    public static boolean isRightPwd() {
        return isRightPwd;
    }
}
