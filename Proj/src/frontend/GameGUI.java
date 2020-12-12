package frontend;

import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    public static void main(String[] args) {
        JFrame GameGui=new JFrame("飞行棋大作战");
        JPanel Chessboard=new BackgroundPanel((new ImageIcon("src\\开始图片.jpg").getImage()));
        JPanel Playerboard=new JPanel();
        GameGui.setLayout(new GridLayout(1,2,50,50));//大小有待后续调整
        Playerboard.setLayout(new GridLayout(2,1,20,20));
        //玩家面板
        JPanel infoPanel=new JPanel();
        infoPanel.setLayout(new GridLayout(1,2));
        //头像图片
        String path = SystemSelect.isMacOS()?SystemSelect.getMacImagePath():SystemSelect.getWindowsImagePath();
        ImageIcon pic1=new ImageIcon(path+"玩家1.jpg");
        ImageIcon pic2=new ImageIcon("src\\玩家2.jpg");
        ImageIcon pic3=new ImageIcon("src\\玩家3.jpg");
        ImageIcon pic4=new ImageIcon("src\\玩家4.jpg");

        JLabel playerLabel=new JLabel(pic2);
        JLabel nameLabel=new JLabel("Feshele");
        infoPanel.add(playerLabel);
        infoPanel.add(nameLabel);
        infoPanel.setPreferredSize(new Dimension(100,100));//大小有待调整
        //保存面板
        JPanel savePanel=new JPanel();
        savePanel.setLayout(new GridLayout(3,1,10,10));//大小有待调整
        JButton resetButton = new JButton("重置");
        JButton saveButton=new JButton("保存");
        JButton returnButton = new JButton("返回");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //重置游戏的后台
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //保存游戏的后台
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameGui.dispose();
                //打开startmenu

            }
        });
        savePanel.add(resetButton);
        savePanel.add(saveButton);
        savePanel.add(returnButton);
        savePanel.setPreferredSize(new Dimension(100,100));//大小有待后续调整
        //窗口初始化
        Playerboard.add(infoPanel);
        Playerboard.add(savePanel);
        GameGui.add(Chessboard);
        GameGui.add(Playerboard);
        GameGui.setVisible(true);
        GameGui.setSize(800,600);
        GameGui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
