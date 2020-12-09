package t.frontend;

//import cs102a.aeroplane.GameInfo;
//import cs102a.aeroplane.util.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CustomerChoice {
    public static void main(ArrayList<Integer> args) {
//    public static void main(String[] args) {

        //没有骰子，直接显示下拉菜单
        if (GameInfo.isIsCheatMode()) {
            JComboBox<Integer> Choices = new JComboBox<Integer>();
            //加入所有选项
            for (int i = 1; i <= 12; i++) {
                Choices.addItem(i);
            }
            //向后台传递玩家选项
            Choices.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    switch (e.getStateChange()) {
                        case 1:
                            Player.setAns(1);
                            break;
                        case 2:
                            Player.setAns(2);
                            break;
                        case 3:
                            Player.setAns(3);
                            break;
                        case 4:
                            Player.setAns(4);
                            break;
                        case 5:
                            Player.setAns(5);
                            break;
                        case 6:
                            Player.setAns(6);
                            break;
                        case 7:
                            Player.setAns(7);
                            break;
                        case 8:
                            Player.setAns(8);
                            break;
                        case 9:
                            Player.setAns(9);
                            break;
                        case 10:
                            Player.setAns(10);
                            break;
                        case 11:
                            Player.setAns(11);
                            break;
                        case 12:
                            Player.setAns(12);
                            break;
                    }
                }
            });
        }
        //显示骰子，显示下拉菜单让用户选择
        else {
            JComboBox<Integer> Choices = new JComboBox<Integer>();
            //加入所有选项
            for (Integer i:args) {
                Choices.addItem(i);
            }
            //向后台传递玩家选项
            Choices.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    switch (e.getStateChange()) {
                        case 1:
                            Player.setAns(1);
                            break;
                        case 2:
                            Player.setAns(2);
                            break;
                        case 3:
                            Player.setAns(3);
                            break;
                        case 4:
                            Player.setAns(4);
                            break;
                        case 5:
                            Player.setAns(5);
                            break;
                        case 6:
                            Player.setAns(6);
                            break;
                        case 7:
                            Player.setAns(7);
                            break;
                        case 8:
                            Player.setAns(8);
                            break;
                        case 9:
                            Player.setAns(9);
                            break;
                        case 10:
                            Player.setAns(10);
                            break;
                        case 11:
                            Player.setAns(11);
                            break;
                        case 12:
                            Player.setAns(12);
                            break;
                    }
                }
            });

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
        jPanel.add(确定JButton);
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
        jPanel.add(Choice);
        jPanel.setLayout(new GridLayout(2, 1));
        jPanel.setPreferredSize(new Dimension(150, 150));
        frame.add(jPanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}