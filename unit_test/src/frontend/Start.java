package frontend;

//import cs102a.aeroplane.GameInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Start  {
    public static void main(String[] args) {
        setPosition(5,7,1,3);
//        //开始窗口
//        JFrame startmenu=new JFrame("飞行棋大作战");
//
//        JPanel startpanel=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
//        JPanel substartpanel = new JPanel();
//
//        substartpanel.setBackground(null);
//        substartpanel.setOpaque(false);
//        JButton startbutton = new JButton("开始游戏");
//        JButton continueButton = new JButton("继续游戏");
//        JButton settingbutton = new JButton("设置");
//        settingbutton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startmenu.setVisible(true);
//                Settings.settingsFrame.settingsFrame.setVisible(true);
//            }
//        });
//        //加上三个button
//        substartpanel.add(startbutton);substartpanel.add(continueButton);substartpanel.add(settingbutton);
//        substartpanel.setLayout( new GridLayout(3,1,10,30));
//        substartpanel.setPreferredSize(new Dimension(150,150));
//        startpanel.add(substartpanel);
//        startmenu.add(startpanel);
//        startmenu.setSize(800,600);
//        startmenu.setVisible(true);startmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setPosition(int x, int y,int positionx,int positiony) {
        JFrame frame = new JFrame("位置测试");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(x,y));
        JButton[][] Buttonarray = new JButton[x][y];
        JButton jb=new JButton();
//        Arrays.fill(Buttonarray,jb);
        //加入所有的Buttons并隐藏
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Buttonarray[i][j]=jb;
                panel.add(Buttonarray[i][j]);
                if (i == positionx && j == positiony) {
                    Buttonarray[i][j].setVisible(true);
                } else {
                    Buttonarray[i][j].setVisible(false);
                }
            }
        }
        frame.setVisible(true);
    }
}
