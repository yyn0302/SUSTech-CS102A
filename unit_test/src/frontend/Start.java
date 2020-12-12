package frontend;

import cs102a.aeroplane.GameInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start  {
    public static void main(String[] args) {
        //开始窗口
        JFrame startmenu=new JFrame("飞行棋大作战");
        JPanel startpanel=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        JPanel substartpanel = new JPanel();
        substartpanel.setBackground(null);
        substartpanel.setOpaque(false);
        JButton startbutton = new JButton("开始游戏");
        JButton continueButton = new JButton("继续游戏");
        JButton settingbutton = new JButton("设置");
        settingbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startmenu.setVisible(true);
                jf1.setVisible(true);
            }
        });
        //加上三个button
        substartpanel.add(startbutton);substartpanel.add(continueButton);substartpanel.add(settingbutton);
        substartpanel.setLayout( new GridLayout(3,1,10,30));
        substartpanel.setPreferredSize(new Dimension(150,150));
        startpanel.add(substartpanel);
        startmenu.add(startpanel);
        startmenu.setSize(800,600);
        startmenu.setVisible(true);startmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
