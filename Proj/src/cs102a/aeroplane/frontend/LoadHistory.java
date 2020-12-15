package cs102a.aeroplane.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class LoadHistory extends JFrame {
    public static LoadHistory loadHistory =new LoadHistory("读档");
    public LoadHistory(String title) {
        this.setTitle(title);

        JPanel panel = new BackgroundPanel(new ImageIcon("src\\读档图片.jpg").getImage());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1,20,150));
        mainPanel.setPreferredSize(new Dimension(600,600));
        JLabel chooseLabel=new JLabel("选择一个存档");
        chooseLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 30));
        JComboBox<String>chooseList=new JComboBox<String>();
        //chooseList.addItem();请加入list.get(i);
        chooseList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //请加入代码
            }
        });
        JButton confirmButton = new JButton("确定");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadHistory.dispose();
                Start.popstart();
            }
        });
        mainPanel.add(chooseLabel);
        mainPanel.add(chooseList);
        mainPanel.add(confirmButton);
        mainPanel.setOpaque(false);
        panel.add(mainPanel);
        this.add(panel);
        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        loadHistory.setVisible(true);
    }
}
