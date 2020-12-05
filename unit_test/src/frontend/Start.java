package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    public static void main(String[] args) {
        //设置窗口
        JFrame jf1=new JFrame("设置");
        jf1.setBounds(100,50,800,600);
        JPanel jp1=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        JButton jb1=new JButton("主题");
        //主题的选择
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf2=new JFrame("主题");
                jf2.setSize(800,600);
                JPanel jp2=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
                JButton jb2=new JButton("海王");
                //大小及位置
                jb2.setBounds(new Rectangle(100,50,100,50));
                JButton jb3=new JButton("灵笼");
                //大小及位置
                jb3.setBounds(new Rectangle(100,200,100,50));
                jp2.add(jb2);
                jp2.add(jb3);
                jf2.add(jp2);
                jf2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jf1.setVisible(false);
                jf2.setVisible(true);
            }
        });
        jp1.add(jb1);
        jf1.add(jp1);
        jf1.setDefaultCloseOperation(jf1.EXIT_ON_CLOSE);
        //开始窗口
        JFrame startmenu=new JFrame("飞行棋大作战");
        JPanel startpanel=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        JButton startbutton = new JButton("开始游戏");
        JButton continueButton = new JButton("继续游戏");
        JButton settingbutton = new JButton("设置");
        settingbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startmenu.setVisible(false);
                jf1.setVisible(true);
            }
        });
        //加上三个button
        startpanel.add(startbutton);startpanel.add(continueButton);startpanel.add(settingbutton);
        startmenu.add(startpanel);
        startmenu.setSize(800,600);
        startmenu.setVisible(true);startmenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
