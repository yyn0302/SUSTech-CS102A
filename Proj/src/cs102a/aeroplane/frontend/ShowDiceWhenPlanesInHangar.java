package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.MatchDicePicture;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.SystemSelect;
import cs102a.aeroplane.util.Timer;

import javax.swing.*;
import java.awt.*;

public class ShowDiceWhenPlanesInHangar {

//    private static boolean isWinInBattle;
//
//    public static void popBattleFrame() {
//        JFrame battleFrame = new JFrame("Battle");
//        battleFrame.setSize(400, 400);
//        battleFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//
//        // 作弊模式没有骰子，直接显示选择胜负两个按钮
//        if (GameInfo.isIsCheatMode()) {
//            JLabel tipLabel = new JLabel("选择想在此轮Battle中的胜负");
//
//            JButton winButton = new JButton("赢");
//            JButton lossButton = new JButton("输");
//            winButton.addActionListener(e -> {
//                isWinInBattle = true;
//                battleFrame.dispose();
//            });
//            lossButton.addActionListener(e -> {
//                isWinInBattle = false;
//                battleFrame.dispose();
//            });
//
//            JPanel labelPanel = new JPanel(new GridLayout(1, 1));
//            labelPanel.add(tipLabel);
//
//            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
//            buttonPanel.add(winButton);
//            buttonPanel.add(lossButton);
//
//            JPanel basePanel = new JPanel(new GridLayout(2, 1));
//            basePanel.setPreferredSize(new Dimension(150, 150));
//            basePanel.add(labelPanel);
//            basePanel.add(buttonPanel);
//
//            String picPath = SystemSelect.getImagePath();
//            JPanel picPanel = new BackgroundPanel((new ImageIcon(picPath + "骰子背景图.jpg").getImage()));
//            picPanel.add(basePanel);
//
//            battleFrame.add(picPanel);
//
//            battleFrame.setVisible(true);
//        }
//
//        //显示骰子，显示下拉菜单让用户选择
//        else {
//            int self = Dice.roll();
//            int oppo = Dice.roll();
//            JLabel selfDiceLabel = new JLabel();
//            JLabel oppoDiceLabel = new JLabel();
//            selfDiceLabel.setIcon(MatchDicePicture.getImage(self));
//            oppoDiceLabel.setIcon(MatchDicePicture.getImage(oppo));
//
//            JLabel tipLabel1 = new JLabel("你摇出" + self + (self < oppo ? " < " : " > ") +
//                    "对方摇出" + oppo + (self < oppo ? "\n你输啦" : "\n你赢啦"));
//
//            JPanel dicePanel = new JPanel(new GridLayout(1, 2));
//            dicePanel.add(selfDiceLabel, oppoDiceLabel);
//
//            JPanel tipPanel = new JPanel(new GridLayout(1, 1));
//            tipPanel.add(tipLabel1);
//
//            JPanel basePanel = new JPanel(new GridLayout(2, 1));
//            basePanel.setPreferredSize(new Dimension(150, 150));
//            basePanel.add(dicePanel, tipPanel);
//
//            String picPath = SystemSelect.getImagePath();
//            JPanel picPanel = new BackgroundPanel((new ImageIcon(picPath + "骰子背景图.jpg").getImage()));
//            picPanel.add(basePanel);
//
//            battleFrame.add(picPanel);
//            battleFrame.setVisible(true);
//
//            Timer.delay(1000);
//            battleFrame.dispose();
//        }
//    }
//
//
//    public static boolean isWinner() {
//        popBattleFrame();
//        System.gc();
//        return isWinInBattle;
//    }
}//做窗口
