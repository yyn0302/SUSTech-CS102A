package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.MatchDicePicture;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * 后端只要调用 Battle.isWinner()
 */
public class Battle {
    public static boolean isWinner() {
        ArrayBlockingQueue<Boolean> queue = new ArrayBlockingQueue<>(1);
        SwingUtilities.invokeLater(() -> {
            JFrame battleFrame = new JFrame("Battle");
            battleFrame.setSize(300, 250);
            battleFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            battleFrame.setLocationRelativeTo(null);
            battleFrame.setAlwaysOnTop(true);
            battleFrame.setResizable(false);

            // 作弊模式没有骰子，直接显示选择胜负两个按钮
            if (!GameInfo.isIsCheatMode()) {
                JLabel tipLabel = new JLabel("选择想在此轮Battle中的胜负");
                tipLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tipLabel.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 20));

                JButton winButton = new JButton("赢");
                JButton lossButton = new JButton("输");
                winButton.addActionListener(e -> {
                    battleFrame.dispose();
                    queue.offer(true);
                });
                lossButton.addActionListener(e -> {
                    battleFrame.dispose();
                    queue.offer(false);
                });

                JPanel labelPanel = new JPanel(new GridLayout(1, 1));
                labelPanel.add(tipLabel);

                JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 30, 0));
                buttonPanel.add(winButton);
                buttonPanel.add(lossButton);
                buttonPanel.add(new JLabel());
                buttonPanel.add(new JLabel());

                JPanel basePanel = new JPanel(new GridLayout(2, 1, 5, 10));
                basePanel.setPreferredSize(new Dimension(300, 250));
                basePanel.add(labelPanel);
                basePanel.add(buttonPanel);

                JPanel picPanel = new BackgroundPanel(null);
                picPanel.add(basePanel);

                battleFrame.add(picPanel);

                battleFrame.setVisible(true);
            }

            //显示骰子
            else {
                int[] result = Dice.rollDices();
                int self = result[0];
                int oppo = result[1];
                JLabel selfDiceLabel = new JLabel();
                JLabel oppoDiceLabel = new JLabel();
                selfDiceLabel.setIcon(MatchDicePicture.getImage(self));
                oppoDiceLabel.setIcon(MatchDicePicture.getImage(oppo));
                selfDiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                oppoDiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                queue.offer(self > oppo);

                JLabel tipLabel1 = new JLabel(String.format("你摇出 %d  %s  对方摇出 %d", self, (self < oppo ? " < " : " > "), oppo));
                JLabel tipLabel2 = new JLabel(self < oppo ? "\n你输了" : "\n你赢啦");
                tipLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                tipLabel2.setHorizontalAlignment(SwingConstants.CENTER);
                tipLabel1.setFont(new java.awt.Font("微软雅黑", Font.PLAIN, 20));
                tipLabel2.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 24));
                tipLabel2.setForeground(Color.red);

                JPanel dicePanel = new JPanel(new GridLayout(1, 2));
                dicePanel.add(selfDiceLabel);
                dicePanel.add(oppoDiceLabel);

                JPanel tipPanel = new JPanel(new GridLayout(2, 1));
                tipPanel.add(tipLabel1);
                tipPanel.add(tipLabel2);

                JPanel basePanel = new JPanel(new GridLayout(2, 1));
                basePanel.setPreferredSize(new Dimension(300, 200));
                basePanel.add(dicePanel);
                basePanel.add(tipPanel);

                JPanel picPanel = new BackgroundPanel(null);
                picPanel.add(basePanel);

                battleFrame.add(picPanel);
                battleFrame.setVisible(true);

                Timer.delay(2000);
                battleFrame.dispose();
            }
        });
        try {
            return queue.take();
        } catch (Exception e) {
            return false;
        }
    }


}
