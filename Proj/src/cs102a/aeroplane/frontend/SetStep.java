package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.MatchDicePicture;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// 只需要用 SetStep.askPlayerStep(rollResult)
public class SetStep {

    private static int stepSelected;


    public static int askPlayerStep(int[] rollResult) {
        stepSelected = 0;
        popCustomerChoiceFrame(rollResult);
        return stepSelected;
    }


    // 弹窗，只有设置好步数才能关闭
    public static void popCustomerChoiceFrame(int[] rollResult) {
        JFrame setStepFrame = new JFrame("选择棋子要走的步数");
        setStepFrame.setSize(400, 400);
        setStepFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // 作弊模式直接选择步数
        if (GameInfo.isIsCheatMode()) {
            JComboBox<Integer> choiceComboBox = getPossibleChoice(rollResult);

            JButton confirmButton = new JButton("确定");
            confirmButton.addActionListener(e -> {
                if (stepSelected != 0) setStepFrame.dispose();
            });


            JPanel choicePanel = new JPanel(new GridLayout(1, 1));
            choicePanel.add(choiceComboBox);

            JPanel basePanel = new JPanel(new GridLayout(2, 1));
            basePanel.setPreferredSize(new Dimension(150, 150));
            basePanel.add(choicePanel);
            basePanel.add(confirmButton);

            String picPath = SystemSelect.getImagePath();
            JPanel picPanel = new BackgroundPanel((new ImageIcon(picPath + "骰子背景图.jpg").getImage()));
            picPanel.add(basePanel);

            setStepFrame.add(picPanel);
        }

        //显示骰子，显示下拉菜单让用户选择
        else {
            int self = rollResult[0];
            int oppo = rollResult[1];
            JLabel selfDiceLabel = new JLabel();
            JLabel oppoDiceLabel = new JLabel();
            selfDiceLabel.setIcon(MatchDicePicture.getImage(self));
            oppoDiceLabel.setIcon(MatchDicePicture.getImage(oppo));

            JComboBox<Integer> choiceComboBox = getPossibleChoice(rollResult);

            JButton confirmButton = new JButton("确定");
            confirmButton.addActionListener(e -> {
                if (stepSelected != 0) setStepFrame.dispose();
            });


            JPanel dicePanel = new JPanel(new GridLayout(1, 2));
            dicePanel.add(selfDiceLabel, oppoDiceLabel);

            JPanel choicePanel = new JPanel(new GridLayout(1, 1));
            choicePanel.add(choiceComboBox);

            JPanel basePanel = new JPanel(new GridLayout(3, 1));
            basePanel.setPreferredSize(new Dimension(150, 150));
            basePanel.add(dicePanel);
            basePanel.add(choicePanel);
            basePanel.add(confirmButton);

            String picPath = SystemSelect.getImagePath();
            JPanel picPanel = new BackgroundPanel((new ImageIcon(picPath + "骰子背景图.jpg").getImage()));
            picPanel.add(basePanel);

            setStepFrame.add(picPanel);
        }

        setStepFrame.setVisible(true);
    }

    // 获取一个装满所有可能步数和配置好listener的JComboBox
    public static JComboBox<Integer> getPossibleChoice(int[] rollResult) {
        ArrayList<Integer> possibleChoice = new ArrayList<>();
        if (GameInfo.isIsCheatMode()) {
            if (rollResult[0] + rollResult[1] <= 12)
                possibleChoice.add(rollResult[0] + rollResult[1]);
            if (rollResult[0] - rollResult[1] > 0)
                possibleChoice.add(rollResult[0] - rollResult[1]);
            if (rollResult[1] - rollResult[0] > 0)
                possibleChoice.add(rollResult[1] - rollResult[0]);
            if (rollResult[0] * rollResult[1] <= 12)
                possibleChoice.add(rollResult[1] * rollResult[0]);
            if ((rollResult[0] / (float) rollResult[1]) % 1f == 0f)
                possibleChoice.add(rollResult[0] / rollResult[1]);
            if ((rollResult[1] / (float) rollResult[0]) % 1f == 0f)
                possibleChoice.add(rollResult[1] / rollResult[0]);
        } else {
            for (int i = 1; i <= 12; i++) {
                possibleChoice.add(i);
            }
        }

        JComboBox<Integer> stepChoices = new JComboBox<>();
        for (int i = 0; i < possibleChoice.size(); i++) {
            stepChoices.addItem(possibleChoice.get(i));
            int index = i;
            stepChoices.addItemListener(e -> SetStep.stepSelected = possibleChoice.get(index));
        }
        return stepChoices;
    }
}
