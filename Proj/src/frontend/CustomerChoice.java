package frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CustomerChoice {
    public static void main(String[] args) {

        //没有骰子，直接显示下拉菜单
        if (GameInfo.isIsCheatMode()) {
            JFrame frame = new JFrame("选择前进步数");
            JPanel panel = new JPanel();
            JButton 确定JButton = new JButton("确定");
            确定JButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            panel.add(确定JButton);
            JComboBox<Integer> Choices = new JComboBox<Integer>();
//加入所有选项
            for (int i = 1; i <= 12; i++) {
                Choices.addItem(i);
            }
            //向后台传递玩家选项
            Choices.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {

                    Player.setCheatNum(e.getStateChange());
//                    switch (e.getStateChange()) {
//                        case 1:
//                            Player.setCheatNum(1);
//                            break;
//                        case 2:
//                            Player.setCheatNum(2);
//                            break;
//                        case 3:
//                            Player.setCheatNum(3);
//                            break;
//                        case 4:
//                            Player.setCheatNum(4);
//                            break;
//                        case 5:
//                            Player.setCheatNum(5);
//                            break;
//                        case 6:
//                            Player.setCheatNum(6);
//                            break;
//                        case 7:
//                            Player.setCheatNum(7);
//                            break;
//                        case 8:
//                            Player.setCheatNum(8);
//                            break;
//                        case 9:
//                            Player.setCheatNum(9);
//                            break;
//                        case 10:
//                            Player.setCheatNum(10);
//                            break;
//                        case 11:
//                            Player.setCheatNum(11);
//                            break;
//                        case 12:
//                            Player.setCheatNum(12);
//                            break;
//                    }
                }
            });
            panel.add(Choices);
            panel.setLayout(new GridLayout(2, 1, 10, 50));
            panel.setPreferredSize(new Dimension(150, 150));
            frame.add(panel);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        //显示骰子，显示下拉菜单让用户选择
        else {
            CreateCustomerChoice(Player.getPossibleChoice());
        }
        //向后端回传一个Integer
    }

    public static void CreateCustomerChoice(ArrayList<Integer> intarray) {
        JFrame frame = new JFrame("选择前进步数");
        JPanel jPanel = new JPanel();
        JButton 确定JButton = new JButton("确定");
        确定JButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        //向后台传递玩家选项
        JComboBox<Integer> Choice = new JComboBox<Integer>();
        //加入所有选项
        for (int i = 0; i < intarray.size(); i++) {
            Choice.addItem(intarray.get(i));
            int finalI = i;
            Choice.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    Player.setAns(intarray.get(finalI));
                }
            });
        }
        JLabel label1 = new JLabel(Matchpicture.chooseImage(Dice.roll()));
        JLabel label2 = new JLabel(Matchpicture.chooseImage(Dice.roll()));
        jPanel.add(label1, label2);
        jPanel.add(确定JButton);
        jPanel.add(Choice);
        jPanel.setLayout(new GridLayout(3, 1, 10, 50));
        jPanel.setPreferredSize(new Dimension(150, 150));
        frame.add(jPanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
