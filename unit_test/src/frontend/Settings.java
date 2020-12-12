package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Settings {
    //设置窗口
    JFrame jf1=new JFrame("设置");
        jf1.setSize(800,600);
    JPanel jp1=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        jp1.setOpaque(false);
    JButton jb1=new JButton("主题");
    JButton jb2=new JButton("正常模式");
    JButton jb3 = new JButton("作弊模式");
    //主题的选择
        jb1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame jf2=new JFrame("主题");
            jf2.setSize(800,600);
            JPanel jp2=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
            JPanel jp3 = new JPanel();
            jp2.setOpaque(false);
            jp3.setBackground(null);
            jp3.setOpaque(false);
            jp3.setLayout( new GridLayout(2,1,10,50));
            jp3.setPreferredSize(new Dimension(150,150));
            JButton jb2=new JButton("海王");
            //大小及位置
            JButton jb3=new JButton("灵笼");
            //大小及位置
            jb3.setBounds(new Rectangle(100,200,100,50));
            jp3.add(jb2);
            jp3.add(jb3);
            jp2.add(jp3);
            jf2.add(jp2);
            jf2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jf1.setVisible(true);
            jf2.setVisible(true);
        }
    });
    //模式的选择
        jb2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameInfo.setIsCheatMode(false);
        }});
        jb3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameInfo.setIsCheatMode(true);
        }});
        jp1.add(jb1);jp1.add(jb2);jp1.add(jb3);
        jp1.setLayout( new GridLayout(3,1,10,30));
        jp1.setPreferredSize(new Dimension(150,150));
        jf1.add(jp1);
        jf1.setDefaultCloseOperation(jf1.EXIT_ON_CLOSE);

}
