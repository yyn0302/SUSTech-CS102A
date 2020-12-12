package cs102a.aeroplane.frontend;


import cs102a.aeroplane.frontend.GameGUI;
import cs102a.aeroplane.frontend.model.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Start  extends JFrame{
public static Start start=new Start("飞行器大作战");
public Start(String title){
    this.setTitle(title);
    //开始窗口
    JPanel startpanel=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
    JPanel substartpanel = new JPanel();
    substartpanel.setBackground(null);
    substartpanel.setOpaque(false);
    JButton startbutton = new JButton("开始游戏");
    JButton continueButton = new JButton("继续游戏");
    JButton settingbutton = new JButton("设置");
    //三个button的listener
    startbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Todo:初始化游戏
            start.dispose();
            GameGUI.popgamegui();
        }
    });
    continueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Todo:继续游戏
        }
    });
    settingbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Settings.popsetting();
        }
    });
    //加上三个button
    substartpanel.add(startbutton);substartpanel.add(continueButton);substartpanel.add(settingbutton);
    substartpanel.setLayout( new GridLayout(3,1,10,30));
    substartpanel.setPreferredSize(new Dimension(150,150));
    startpanel.add(substartpanel);
    this.add(startpanel);
    this.setSize(800,600);
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}
public static void popstart(){
    start.setVisible(true);
}
    public static void main(String[] args) {
        start.setVisible(true);
    }
}
