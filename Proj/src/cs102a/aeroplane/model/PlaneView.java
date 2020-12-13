package cs102a.aeroplane.model;

import javax.swing.*;
import java.awt.*;

//20*20，创建680*680的panel
// 棋盘
public class PlaneView extends JButton {
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
    //你需要把button的listener写成一个方法，然后如果显示，就调用，如果不显示，就放空。
    //比如从（0,0)到(1,1),(0,0)设为不可见，没有listner，（1，1）设为可见，有listner
}
