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
        if(GameInfo.isIsCheatMode()){
JComboBox<Integer>Choices=new JComboBox<Integer>();
//加入所有选项
            for (int i = 1; i <= 12;i++){
                Choices.addItem(i);
            }
            Choices.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    switch (e.getStateChange()){
                           case 1 :GiveBackAnInt(1);
                               break;
                           case 2 :GiveBackAnInt(2);
                               break;
                           case 3 :GiveBackAnInt(3);
                               break;
                               case 4 :GiveBackAnInt(4);
                                   break;
                                   case 5 :GiveBackAnInt(5);
                                       break;
                                       case 6 :GiveBackAnInt(6);
                                           break;
                                           case 7 :GiveBackAnInt(7);
                                               break;
                                               case 8 :GiveBackAnInt(8);
                                                   break;
                                                   case 9 :GiveBackAnInt(9);
                                                       break;
                                                       case 10 :GiveBackAnInt(10);
                                                           break;
                                                           case 11 :GiveBackAnInt(11);
                                                               break;
                                                               case 12 :GiveBackAnInt(12);
                                                                   break;
                    }
                }
            });
        }
        //显示骰子，显示下拉菜单让用户选择
        else {
JButton 确定JButton=new JButton("确定");
            确定JButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
JFrame frame = new JFrame("选择前进步数");
JPanel jPanel = new JPanel();
jPanel.setLayout( new GridLayout(4,1));
jPanel.setPreferredSize(new Dimension(150,150));
frame.add(jPanel, BorderLayout.CENTER);
frame.setSize(400,400);
frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
frame.setVisible(true);
        }
    }
//创建JButtons选项
    public static void CreateButton(ArrayList<String>list){
        for(int i=0;i <list.size();i++){
            JButton[]jButtons=new JButton[list.size()];
            jButtons[i].setText(list.get(i));
            jButtons[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    //像后端回传选中的数字
                GiveBackAnInt(1);
                }
            });
        }
    }
    //向后端回传一个Integer
    public static void GiveBackAnInt(int i){

    }
 }
