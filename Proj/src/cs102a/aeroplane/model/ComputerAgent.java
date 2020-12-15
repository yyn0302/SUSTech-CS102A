package cs102a.aeroplane.model;

import cs102a.aeroplane.util.Timer;

public class ComputerAgent extends HumanAgent {

    // TODO: 2020/12/8 add code
    // 自动转骰子获取点数diceNumber，如果能走就根据AI策略获取要走的飞机编号，Commdef.COLOR_PLANE[turn]是当前回合四架飞机的编号
    // 通过diceNumber来做转骰子动画，有选择的飞机编号则通过调用飞机的receiveDiceNumber来自动移动


    public static int getBestStep() {

    }

    public static int getBestPlaneToMove() {

    }

    public static void agentTakeOver() {
        Timer.delay(1000);

    }
}
