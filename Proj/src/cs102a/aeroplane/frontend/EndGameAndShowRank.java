package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

public class EndGameAndShowRank extends JFrame {
    public static EndGameAndShowRank endGameAndShowRank = new EndGameAndShowRank("游戏结束");

    public EndGameAndShowRank(String title) {
        this.setTitle(title);

        String path = SystemSelect.getImagePath();

        GridBagLayout gridBagLayout = new GridBagLayout();

        JPanel mainPanel = new JPanel(gridBagLayout);
        mainPanel.setPreferredSize(new Dimension(1000, 1000));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;


        //游戏结束label
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        JLabel overLabel = new JLabel("游戏结束");
        gridBagLayout.setConstraints(overLabel, gridBagConstraints);


        //确定button
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        JButton confirmButton = new JButton("确定");
        confirmButton.addActionListener(e -> {
            endGameAndShowRank.dispose();
            GameGUI.window.setChessBoard(null);
            GameGUI.window.setVisible(false);
            System.gc();
            Start.popStart();
        });
        gridBagLayout.setConstraints(confirmButton, gridBagConstraints);


        //中间4个玩家的排名
        //第一名的三个label
        //第一名的图片
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel number1 = new JLabel(new ImageIcon(path + "第一名.png"));
        gridBagLayout.setConstraints(number1, gridBagConstraints);

        //第一名的第二个label
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info1ofNumber1 = new JLabel("第一名：玩家" + GameGUI.window.getChessBoard().getWinner1Index() +
                "(" + GameInfo.getPlayerName()[GameGUI.window.getChessBoard().getWinner1Index()] + ")");
        gridBagLayout.setConstraints(info1ofNumber1, gridBagConstraints);

        //第一名的第三个label
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info2ofNumber1 = new JLabel("步数：" + GameGUI.window.getChessBoard().getPlayerSteps()[GameGUI.window.getChessBoard().getWinner1Index()]);
        gridBagLayout.setConstraints(info2ofNumber1, gridBagConstraints);


        //第二名的三个label
        //第二名的图片
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel number2 = new JLabel(new ImageIcon(path + "第二名.png"));
        gridBagLayout.setConstraints(number2, gridBagConstraints);

        //第二名的第二个label
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info1ofNumber2 = new JLabel("第二名：玩家" + GameGUI.window.getChessBoard().getWinner2Index() +
                "(" + GameInfo.getPlayerName()[GameGUI.window.getChessBoard().getWinner2Index()] + ")");
        gridBagLayout.setConstraints(info1ofNumber2, gridBagConstraints);

        //第二名的第三个label
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info2ofNumber2 = new JLabel("步数：" + GameGUI.window.getChessBoard().getPlayerSteps()[GameGUI.window.getChessBoard().getWinner2Index()]);
        gridBagLayout.setConstraints(info2ofNumber2, gridBagConstraints);


        //第三名的三个label
        //第三名的图片
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel number3 = new JLabel(new ImageIcon(path + "第三名.png"));
        gridBagLayout.setConstraints(number3, gridBagConstraints);

        //第三名的第二个label
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info1ofNumber3 = new JLabel("第三名：玩家" + GameGUI.window.getChessBoard().getWinner3Index() +
                "(" + GameInfo.getPlayerName()[GameGUI.window.getChessBoard().getWinner4Index()] + ")");
        gridBagLayout.setConstraints(info1ofNumber3, gridBagConstraints);

        //第三名的第三个label
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info2ofNumber3 = new JLabel("步数：" + GameGUI.window.getChessBoard().getPlayerSteps()[GameGUI.window.getChessBoard().getWinner3Index()]);
        gridBagLayout.setConstraints(info2ofNumber3, gridBagConstraints);


        //第四名的三个label
        //第四名的图片
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel number4 = new JLabel(new ImageIcon(path + "第四名.png"));
        gridBagLayout.setConstraints(number4, gridBagConstraints);

        //第四名的第二个label
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info1ofNumber4 = new JLabel("第四名：玩家" + GameGUI.window.getChessBoard().getWinner4Index() +
                "(" + GameInfo.getPlayerName()[GameGUI.window.getChessBoard().getWinner4Index()] + ")");
        gridBagLayout.setConstraints(info1ofNumber4, gridBagConstraints);

        //第四名的第三个label
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        JLabel info2ofNumber4 = new JLabel(""); // 留空
        gridBagLayout.setConstraints(info2ofNumber4, gridBagConstraints);


        //初始化
        mainPanel.add(overLabel);
        mainPanel.add(number1);
        mainPanel.add(info1ofNumber1);
        mainPanel.add(info2ofNumber1);
        mainPanel.add(number2);
        mainPanel.add(info1ofNumber2);
        mainPanel.add(info2ofNumber2);
        mainPanel.add(number3);
        mainPanel.add(info1ofNumber3);
        mainPanel.add(info2ofNumber3);
        mainPanel.add(number4);
        mainPanel.add(info1ofNumber4);
        mainPanel.add(info2ofNumber4);
        mainPanel.add(confirmButton);
        mainPanel.setOpaque(false);
        this.add(mainPanel);
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
