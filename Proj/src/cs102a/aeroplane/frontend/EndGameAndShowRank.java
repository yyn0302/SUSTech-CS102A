package cs102a.aeroplane.frontend;

import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameAndShowRank extends JFrame {
    public static EndGameAndShowRank endGameAndShowRank=new EndGameAndShowRank("游戏结束");
    public EndGameAndShowRank(String title) {
        this.setTitle(title);
        String path= SystemSelect.getImagePath();
        GridBagLayout gridBagLayout = new GridBagLayout();
        JPanel mainPanel = new JPanel(gridBagLayout);
        mainPanel.setPreferredSize(new Dimension(1000,1000));
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        //游戏结束label
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.gridheight=1;
        JLabel overLabel=new JLabel("游戏结束");
        gridBagLayout.setConstraints(overLabel, gridBagConstraints);
        //确定button
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth=3;
        gridBagConstraints.gridheight=1;
        JButton confirmButton=new JButton("确定");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endGameAndShowRank.dispose();
                Start.popStart();
            }
        });
        gridBagLayout.setConstraints(confirmButton, gridBagConstraints);
        //中间4个玩家的排名
        //第一名的三个Jlabel
        //第一名的图片
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel number1=new JLabel(new ImageIcon(path+"第一名.png"));
        gridBagLayout.setConstraints(number1, gridBagConstraints);
        //第一名的第二个Jlabel
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info1ofnumber1=new JLabel();//加入内容
        gridBagLayout.setConstraints(info1ofnumber1, gridBagConstraints);
        //第一名的第三个Jlabel
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info2ofnumber1=new JLabel();//加入内容
        gridBagLayout.setConstraints(info2ofnumber1, gridBagConstraints);
        //第二名的三个Jlabel
        //第二名的图片
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel number2=new JLabel(new ImageIcon(path+"第二名.png"));
        gridBagLayout.setConstraints(number2, gridBagConstraints);
        //第二名的第二个Jlabel
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info1ofnumber2=new JLabel();//加入内容
        gridBagLayout.setConstraints(info1ofnumber2, gridBagConstraints);
        //第二名的第三个Jlabel
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info2ofnumber2=new JLabel();//加入内容
        gridBagLayout.setConstraints(info2ofnumber2, gridBagConstraints);
        //第三名的三个Jlabel
        //第三名的图片
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel number3=new JLabel(new ImageIcon(path+"第三名.png"));
        gridBagLayout.setConstraints(number3, gridBagConstraints);
        //第三名的第二个Jlabel
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info1ofnumber3=new JLabel();//加入内容
        gridBagLayout.setConstraints(info1ofnumber3, gridBagConstraints);
        //第三名的第三个Jlabel
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info2ofnumber3=new JLabel();//加入内容
        gridBagLayout.setConstraints(info2ofnumber3, gridBagConstraints);
        //第四名的三个Jlabel
        //第四名的图片
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel number4=new JLabel(new ImageIcon(path+"第四名.png"));
        gridBagLayout.setConstraints(number4, gridBagConstraints);
        //第四名的第二个Jlabel
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info1ofnumber4=new JLabel();//加入内容
        gridBagLayout.setConstraints(info1ofnumber4, gridBagConstraints);
        //第四名的第三个Jlabel
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        JLabel info2ofnumber4=new JLabel();//加入内容
        gridBagLayout.setConstraints(info2ofnumber4, gridBagConstraints);
        //初始化
        mainPanel.add(overLabel);
        mainPanel.add(number1);mainPanel.add(info1ofnumber1);mainPanel.add(info2ofnumber1);
        mainPanel.add(number2);mainPanel.add(info1ofnumber2);mainPanel.add(info2ofnumber2);
        mainPanel.add(number3);mainPanel.add(info1ofnumber3);mainPanel.add(info2ofnumber3);
        mainPanel.add(number4);mainPanel.add(info1ofnumber4);mainPanel.add(info2ofnumber4);
        mainPanel.add(confirmButton);
        mainPanel.setOpaque(false);
        this.add(mainPanel);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        endGameAndShowRank.setVisible(true);
    }
}
