package cs102a.aeroplane.frontend.model;

import javax.swing.*;
import java.awt.*;

public class PlaneView {
    public static void setPosition(int x, int y,int positionx,int positiony) {
        JFrame frame = new JFrame("位置测试");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(x,y));
        JButton[][] Buttonarray = new JButton[x][y];
        //加入所有的Buttons并隐藏
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                panel.add(Buttonarray[i][j]);
                if (i == positionx && j == positiony) {
                    Buttonarray[i][j].setVisible(true);
                } else {
                    Buttonarray[i][j].setVisible(false);
                }
            }
        }
    }
}
