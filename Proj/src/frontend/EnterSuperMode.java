package frontend;

import javax.swing.*;

public class EnterSuperMode {

    public static JFrame enterSuperMode = new JFrame("设置");
    private static final String pwd = "020924";

    public EnterSuperMode() {

        enterSuperMode.setSize(320, 200);
        enterSuperMode.setLocationRelativeTo(null);
        enterSuperMode.setAlwaysOnTop(true);



    }

    public static boolean isRightPwd() {
        return false;
    }
}
