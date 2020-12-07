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
                           case 1 :
                               break;
                           case 2 :
                               break;
                           case 3 :
                               break;
                               case 4 :
                                   break;
                                   case 5 :
                                       break;
                                       case 6 :
                                           break;
                                           case 7 :
                                               break;
                                               case 8 :
                                                   break;
                                                   case 9 :
                                                       break;
                                                       case 10 :
                                                           break;
                                                           case 11 :
                                                               break;
                                                               case 12 :
                                                                   break;
                    }
                }
            });
        }
        //显示骰子，显示下拉菜单让用户选择
        else {
JButton 确定JButton=new JButton("确定");


        }
    }

    public void CreateButton(ArrayList<String>list){
        for(int i=0;i <list.size();i++){
            JButton[]jButtons=new JButton[list.size()];
            jButtons[i].setText(list.get(i));
            jButtons[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }
 }
