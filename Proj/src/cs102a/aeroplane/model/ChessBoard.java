package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.*;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.GameState;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.Dice;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ChessBoard extends JPanel {
    private Aeroplane[] planes;                             // 16架飞机
    private int xOffSet;
    private int yOffSet;
    private int[] playerSteps;                              // 截止胜利走了多少步
    int[] rollResult;                                       // 骰子点数
    private ArrayList<Integer> movedPlanes;                 // 记录一个人摇多次时，移动过哪些棋子
    private int state;                                      // 状态（游戏未开始，游戏已开始，游戏结束）  // 重置游戏后先进入GAME_READY，完成后GAME_START
    private int nowPlayer;                                  // 当前回合
    public int nowMove;                                    // 当前玩家选择的准备对任何飞机的移动步数    // 要起飞则在判断至少一个6后接受任意的nowStep
    private int continueRoll;                               // 记录连投的次数
    private int winner1Index;                               // 胜利者
    private int winner2Index;                               // 胜利者
    private int winner3Index;                               // 胜利者
    private int[] playerType;                               // 四个玩家类型，人类、AI
    private int myColor = -1;                               // 自己阵营，联机时
    protected boolean[][] teamIndexUsed = new boolean[4][2];// 当前队伍已经有飞机
    GameGUI nowGamingGUI;

    public ChessBoard(int xOffSet, int yOffSet, GameGUI nowGamingGUI) {
        this.state = GameState.GAME_READY;
        this.nowPlayer = 0;
        this.nowGamingGUI = nowGamingGUI;
        this.rollResult = new int[2];

        this.setLayout(null);
        this.setSize(800, 800);
        this.setOpaque(false);

        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;

        this.winner1Index = -1;
        this.winner2Index = -1;
        this.winner3Index = -1;

        this.playerSteps = new int[]{0, 0, 0, 0};
        this.continueRoll = 0;
        this.movedPlanes = new ArrayList<>();

        playerType = new int[4];
        for (int i = 0; i < 4; i++) {
            if (i < GameInfo.getHumanPlayerCnt()) playerType[i] = GameState.HUMAN;
            else playerType[i] = GameState.COMPUTER;
        }

        this.setLayout(null);
    }


    /**
     * @apiNote 开局即放入ChessBoard并只需调用此方法，其他的自动执行
     */
    public void startGame() {
        //开始播放bgm
        if (GameInfo.getTheme() == 1) Sound.GAMING_THEME1.play(true);
        else Sound.GAMING_THEME2.play(true);

        // 初始化飞机
        planes = new Aeroplane[]{
                new Aeroplane(this, PlaneState.BLUE, 0, 0, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.BLUE, 1, 1, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.BLUE, 2, 2, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.BLUE, 3, 3, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.GREEN, 4, 5, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.GREEN, 5, 6, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.GREEN, 6, 7, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.GREEN, 7, 8, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.RED, 8, 10, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.RED, 9, 11, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.RED, 10, 12, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.RED, 11, 13, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.YELLOW, 12, 15, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.YELLOW, 13, 16, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.YELLOW, 14, 17, xOffSet, yOffSet),
                new Aeroplane(this, PlaneState.YELLOW, 15, 18, xOffSet, yOffSet)
        };


        // TODO: 2020/12/16 如果是联网模式，还要初始化myCamp
//        if (GameInfo.isIsOnlineGame()) ?
// FIXME: 2020/12/18 在线模式
        state = GameState.GAME_START;

        // 随机决定哪方先开始
        nowPlayer = new Random().nextInt(4);
        beginTurn();
    }


    // 开始回合
    public void beginTurn() {
        nowGamingGUI.getPlayerInfoPanel().refresh();
        if (!GameInfo.isIsOnlineGame()) {
            playerSteps[nowPlayer] += 1;
            rollAndApply();
        } else {  // 联机模式，在己方看来其他三个是人是AI都一样，在他们回合都是要等待diceNumber和飞机编号
//            if (nowPlayer == myColor) {     //己方回合
//                rollAndApply();
//                Client.uploadChanges();
//            } else {   // 其他玩家回合
//                Client.getAndApplyChange();
//            }
            // FIXME: 2020/12/18 增加在线游戏模式
        }
    }

    public void rollAndApply() {
        rollResult[0] = Dice.roll();
        rollResult[1] = Dice.roll();
        System.err.println("\nnowPlayer " + nowPlayer);
        ArrayList<Integer> outsidePlanes = new ArrayList<>();
        // 是否全在机场
        for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
            if (!(planes[i].isInHangar() && planes[i].notFinished())) {
                outsidePlanes.add(i);       // 添加在外面的飞机
                System.out.println("outside added:" + i);
            }
        }

        boolean ableToTakeOff = rollResult[0] == 6 || rollResult[1] == 6;
        System.out.println("able to take off:" + ableToTakeOff);
        if (ableToTakeOff||GameInfo.isIsCheatMode()) {
            SetStep.askPlayerStep(nowGamingGUI, this, rollResult, true);
            // 是起飞的点数则当前回合的所有飞机都可飞
        } else {
            // 不是起飞点数则只有在外面的飞机可以飞
            if (outsidePlanes.isEmpty()) {
                System.out.println("skip to next player");
                new TimeDialog().showDialog(Settings.window, "你骰出了" + rollResult[0] +
                        "和" + rollResult[1] + "，不满足起飞条件", 3);
                do {
                    nowPlayer = (nowPlayer + 1) % 4;
                } while (nowPlayer == winner1Index || nowPlayer == winner2Index || nowPlayer == winner3Index);
                beginTurn();
            } else {
                SetStep.askPlayerStep(nowGamingGUI, this, rollResult, false);
            }
        }

        // 如果当前回合是AI，让他选择后点击
//        if (playerType[nowPlayer] == GameState.COMPUTER) ComputerAgent.selectAndClick();
    }

    public void continueAfterAsk() {
        System.err.println(nowMove);
        for(Aeroplane p:planes){
            for (ActionListener actionListener : p.getPlaneView().getActionListeners()) {
                p.getPlaneView().removeActionListener(actionListener);
            }
        }
        for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
            if (planes[i].notFinished())
                planes[i].getPlaneView().readyToBeSelected();
        }
    }

    public void continueAfterAskFalse() {
        System.err.println(nowMove);
        for(Aeroplane p:planes){
            for (ActionListener actionListener : p.getPlaneView().getActionListeners()) {
                p.getPlaneView().removeActionListener(actionListener);
            }

        }

        ArrayList<Integer> outsidePlanes = new ArrayList<>();
        // 是否全在机场
        for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
            if (!(planes[i].isInHangar() && planes[i].notFinished())) {
                outsidePlanes.add(i);       // 添加在外面的飞机
            }
        }
        for (Integer i : outsidePlanes) {
            planes[i].getPlaneView().readyToBeSelected();
        }
        outsidePlanes.clear();
    }


    // 结束回合
    public void endTurn() {
        // 检查游戏是否结束
        if (checkGameEnd()) {
            endGame();
        } else {
            // 先确定当前玩家有没有赢
            boolean flag = true;
            for (int number : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
                if (planes[number].notFinished()) {
                    flag = false;
                    break;
                }
            }
            if (flag) recordOnePlayerEnd();
            else {
//                if (!isTakingOff) {
                if (rollResult[0] + rollResult[1] >= 10) {
                    if (continueRoll < 3) {
                        continueRoll++;
                        beginTurn();
                    } else {
                        for (Aeroplane p : planes) {
                            for (int i : movedPlanes) {
                                if (p.getNumber() == i) {
                                    p.backToHangarForInit();
                                    break;
                                }
                            }
                        }
                        continueRoll = 0;
                        movedPlanes.clear();
                    }
                } else {
                    // 否则下一个回合为顺时针下一阵营
                    do {
                        nowPlayer = (nowPlayer + 1) % 4;
                    } while (nowPlayer == winner1Index || nowPlayer == winner2Index || nowPlayer == winner3Index);
                    beginTurn();
                }
//                } else beginTurn();
            }
        }
    }

    // 结束游戏
    public void endGame() {
        // TODO: 2020/12/8 联网模式还要广播游戏结束
//        if (GameInfo.isIsOnlineGame()) {
//            ?
//        }
        // FIXME: 2020/12/18 在线模式
        EndGameAndShowRank endGameAndShowRank = new EndGameAndShowRank(nowGamingGUI);
        endGameAndShowRank.setVisible(true);
    }

    public void battleInTeam(int indexOfMyTeam, int indexOfTargetGrid) {
        while (planesInTeam(indexOfMyTeam) > 0 || hasOtherPlane(indexOfTargetGrid)) {
            if (Battle.isWinner())
                getOppoPlanes(indexOfTargetGrid).get(getOppoPlanes(indexOfTargetGrid).size() - 1).backToHangarDueToCrash();
            else getPartners(indexOfMyTeam).get(getPartners(indexOfMyTeam).size() - 1).backToHangarDueToCrash();
        }
    }


    /*
     * util methods for assisting judging
     */

    // 判断index上有没有其他方的棋子
    public boolean hasOtherPlane(int index) {
        for (Aeroplane plane : planes)
            if (plane.getGeneralGridIndex() == index && plane.getColor() != nowPlayer) return true;
        return false;
    }

    // 判断index上有没有我方的棋子
    public boolean hasMyPlane(int index) {
        for (Aeroplane plane : planes)
            if (plane.getGeneralGridIndex() == index && plane.getColor() == nowPlayer) return true;
        return false;
    }

    // 判断当前组内还有没有棋子
    public int planesInTeam(int teamNumber) {
        int cnt = 0;
        for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer])
            if (planes[i].indexOfTeam == teamNumber) cnt++;
        return cnt;
    }

    // 获取所有当前格子上的敌机以battle
    public LinkedList<Aeroplane> getOppoPlanes(int index) {
        LinkedList<Aeroplane> p = new LinkedList<>();
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getColor() != nowPlayer) p.add(plane);
        }
        return p;
    }

    // 获取所有当前格子上任意一架
    public LinkedList<Aeroplane> getMyPlanes(int index) {
        LinkedList<Aeroplane> p = new LinkedList<>();
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getColor() == nowPlayer) p.add(plane);
        }
        return p;
    }

    public LinkedList<Aeroplane> getPartners(int indexOfMyTeam) {
        LinkedList<Aeroplane> p = new LinkedList<>();
        for (Aeroplane plane : planes) {
            if (plane.indexOfTeam == indexOfMyTeam && plane.getColor() == nowPlayer) p.add(plane);
        }
        if (indexOfMyTeam == -1) {
            p.clear();
        }
        return p;
    }

    // 获取index上的飞机数目
    public int selfPlaneNumOnIndex(int index) {
        int planeNum = 0;
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index) {
                planeNum++;
            }
        }
        return planeNum;
    }

    public void recordOnePlayerEnd() {
        if (winner1Index == -1) winner1Index = nowPlayer;
        else if (winner2Index == -1) winner2Index = nowPlayer;
        else if (winner3Index == -1) winner3Index = nowPlayer;
        if (winner3Index != -1) state = GameState.GAME_END;

        if (GameInfo.isIsOnlineGame()) {
            // TODO: 2020/12/8 socket 广播玩家胜利
        }
    }

    private boolean checkGameEnd() {
        return state == GameState.GAME_END;
    }


    /*
     * util methods for external accessing
     */
    public ArrayList<Integer> getMovedPlanes() {
        return movedPlanes;
    }

    public void setWinner1Index(int winner1Index) {
        this.winner1Index = winner1Index;
    }

    public int getNowPlayer() {
        return nowPlayer;
    }

    public void setNowPlayer(int nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public Aeroplane[] getPlanes() {
        return planes;
    }

    public int getNowMove() {
        return nowMove;
    }

    public int getWinner1Index() {
        return winner1Index;
    }

    public int getWinner2Index() {
        return winner2Index;
    }

    public int getWinner3Index() {
        return winner3Index;
    }

    public int getWinner4Index() {
        for (int i = 0; i < 4; i++) {
            if (i != winner1Index && i != winner2Index && i != winner3Index) return i;
        }
        return -1;
    }

    public int[] getPlayerSteps() {
        return playerSteps;
    }

    public void setWinner2Index(int parseInt) {
        this.winner2Index = parseInt;
    }
}
