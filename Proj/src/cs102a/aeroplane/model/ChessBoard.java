package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.EndGameAndShowRank;
import cs102a.aeroplane.frontend.GameGUI;
import cs102a.aeroplane.online.Client;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.GameState;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.Dice;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class ChessBoard extends JPanel {
    private int state;              // 状态（游戏未开始，游戏已开始，游戏结束）  // 重置游戏后先进入GAME_READY，完成后GAME_START
    private int nowPlayer;          // 当前回合
    private int xOffSet;            // 棋盘在屏幕X方向即右方向的偏移
    private int yOffSet;            // 棋盘在屏幕Y方向即下方向的偏移

    int[] rollResult;               // 骰子点数
    private int continueRoll;       // 记录连投的次数

    private GameGUI gameGUI;

    ArrayList<ArrayList<Integer>> stackingPlanes;     // 记录有无叠子，子ArrayList存叠一起的飞机number，只有第一个是visible
    ArrayList<Integer> movedPlanes;                   // 记录一个人摇多次时，移动过哪些棋子

    private Aeroplane[] planes;              // 16架飞机
    private PlayerAgent[] players;           // 4玩家
    private int winner1Index;                // 胜利者
    private int winner2Index;                // 胜利者
    private int winner3Index;                // 胜利者
    private int player1Steps;                // 截止胜利走了多少步
    private int player2Steps;                // 截止胜利走了多少步
    private int player3Steps;                // 截止胜利走了多少步
    private int player4Steps;                // 截止胜利走了多少步
    private int[] playerType;                // 四个玩家类型，人类、AI
    private int myColor = -1;                // 自己阵营，联机时

    public ChessBoard(GameGUI gameGUI, int xOffSet, int yOffSet) {
        this.state = GameState.GAME_READY;
        this.nowPlayer = -1;
        this.gameGUI = gameGUI;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;

        this.winner1Index = -1;
        this.winner2Index = -1;
        this.winner3Index = -1;

        this.player1Steps = 0;
        this.player2Steps = 0;
        this.player3Steps = 0;
        this.player4Steps = 0;

        continueRoll = 0;
        stackingPlanes = new ArrayList<>();
        movedPlanes = new ArrayList<>();

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
    }


    // 开始游戏
    public void startGame() {
        //开始播放bgm
        if (GameInfo.getTheme() == 1) Sound.GAMING_THEME1.play(true);
        else Sound.GAMING_THEME2.play(true);

        playerType = new int[4];
        for (int i = 0; i < 4; i++) {
            if (i < GameInfo.getHumanPlayerCnt()) playerType[i] = GameState.HUMAN;
            else playerType[i] = GameState.COMPUTER;
        }

        // TODO: 2020/12/16 如果是联网模式，还要初始化myCamp xxxx
        if (GameInfo.isIsOnlineGame()) myColor = Client.getDeltaPort();

        state = GameState.GAME_START;

        // 还原飞机位置
        for (Aeroplane plane : planes) plane.restore();

        // 随机决定哪方先开始
        nowPlayer = new Random().nextInt(4);

        beginTurn();
    }


    // TODO: 2020/12/8 socket 广播游戏结束
    public void recordOnePlayerEnd() {
        if (winner1Index == -1) winner1Index = nowPlayer;
        else if (winner2Index == -1) winner2Index = nowPlayer;
        else if (winner3Index == -1) winner3Index = nowPlayer;
        if (winner3Index != -1) state = GameState.GAME_END;

        // 联网模式还要广播获胜消息
        if (GameInfo.isIsOnlineGame()) {
            // TODO: 2020/12/8 socket 广播游戏结束
        }
    }


    private static boolean hasPressedButton = false;

    // TODO: 2020/12/11 按按钮改变boolean值
    public static void setHasPressedButton(boolean hasPressedButton) {
        ChessBoard.hasPressedButton = hasPressedButton;
    }


    // 开始回合
    public void beginTurn() {
        GameGUI.window.getPlayerInfoPanel().refresh();

        if (!GameInfo.isIsOnlineGame()) {
            // 如果当前回合是AI
            if (playerType[nowPlayer] == GameState) {
                ComputerAgent.agentTakeOver();
            }
            // 当前回合是玩家
            else {
                //                // FIXM1E: 2020/12/9 调整箭头位置
////                if (nowPlayer == Hangar.BLUE || nowPlayer == Hangar.GREEN) {
////                    arrowView.setX((float) (playerViews[nowPlayer].getX() + playerViews[nowPlayer].getWidth() * 1.01));
////                    arrowView.setY((float) (playerViews[nowPlayer].getY() + playerViews[nowPlayer].getHeight() * 0.3));
////                    arrowView.setRotationY(0);
////                } else {
////                    arrowView.setX((float) (playerViews[nowPlayer].getX() - playerViews[nowPlayer].getWidth() * 0.29));
////                    arrowView.setY((float) (playerViews[nowPlayer].getY() + playerViews[nowPlayer].getHeight() * 0.3));
////                    arrowView.setRotationY(180);
////                }
////                arrowView.setVisibility(View.VISIBLE);
//                // 做一个动画让箭头不断缩放
////                ScaleAnimation arrowAnim = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.ABSOLUTE, (float) (arrowView.getX() + arrowView.getWidth() * 0.5), Animation.ABSOLUTE, (float) (arrowView.getY() + arrowView.getHeight() * 0.5));
////                arrowAnim.setDuration(500);     //设置动画持续时间
////                arrowAnim.setRepeatCount(-1);   //设置重复次数，-1无限循环
////                arrowAnim.setRepeatMode(Animation.REVERSE); // 逆序重复
////                arrowAnim.setFillAfter(false);              // 不用停在最后一帧
////                arrowView.startAnimation(arrowAnim);
//
//                diceView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // 箭头消失
//                        arrowView.clearAnimation();
//                        arrowView.setVisibility(View.GONE);
//                        // 这回合内禁止再点击骰子
//                        diceView.setClickable(false);
//                        // 在骰子view载入逐帧动画来达到转骰子效果
//                        diceView.setBackgroundResource(R.drawable.diceanim);
//                        final AnimationDrawable diceAnim = (AnimationDrawable) diceView.getBackground();
//                        diceAnim.start();
//                        new Handler().postDelayed(new Runnable() {
//                            public void run() {
//                                // 一秒后停止逐帧动画
//                                diceAnim.stop();
//                                Random rand = new Random();
//                                diceNumbers = rand.nextInt(6) + 1;
//                                // 根据随机的骰子点数来设置要显示的骰子图片
//                                if (diceNumbers == 1) diceView.setBackgroundResource(R.drawable.dice1);
//                                else if (diceNumbers == 2) diceView.setBackgroundResource(R.drawable.dice2);
//                                else if (diceNumbers == 3) diceView.setBackgroundResource(R.drawable.dice3);
//                                else if (diceNumbers == 4) diceView.setBackgroundResource(R.drawable.dice4);
//                                else if (diceNumbers == 5) diceView.setBackgroundResource(R.drawable.dice5);
//                                else if (diceNumbers == 6) diceView.setBackgroundResource(R.drawable.dice6);
//
//                                // 连投奖励
//                                if (diceNumbers == 6) showInfo("获得一次投骰子机会");
//
//                                ArrayList<Integer> outsidePlanes = new ArrayList<Integer>();
//                                // 如果是迭在别人迭子上则自动走那个棋子
//                                if (markPlane != -1) {
//                                    planes[markPlane].receiveDiceNumber(diceNumbers);
//                                    markPlane = -1;
//                                } else {
//                                    // 是否全在机场
//                                    for (int i : Hangar.COLOR_PLANE[nowPlayer]) {
//                                        if (!planes[i].isInAirport()) {
//                                            // 添加在外面的飞机
//                                            outsidePlanes.add(i);
//                                        }
//                                    }
//                                    // 是否是起飞的点数，可以在Commdef.TAKE_OFF_NUMBER进行修改
//                                    boolean ableToTakeOff = false;
//                                    for (int each : Hangar.TAKE_OFF_NUMBER) {
//                                        if (each == diceNumbers) {
//                                            ableToTakeOff = true;
//                                            break;
//                                        }
//                                    }
//                                    if (ableToTakeOff) {
//                                        // 是起飞的点数则当前回合的所有飞机都可飞
//                                        for (int i : Hangar.COLOR_PLANE[nowPlayer]) {
//                                            if (planes[i].getStatus() != Hangar.FINISHED)
//                                                planes[i].getReadyToFly();
//                                        }
//                                    } else {
//                                        // 不是起飞点数则只有在外面的飞机可以飞
//                                        if (outsidePlanes.isEmpty()) {
//                                            showInfo("无法起飞");
//                                            new Handler().postDelayed(new Runnable() {
//                                                public void run() {
//                                                    nowPlayer = (nowPlayer + 1) % Hangar.PLAYER_NUM;
//                                                    beginTurn();
//                                                }
//
//                                            }, 1000);   // 等待一秒后执行
//                                        } else {
//                                            for (Integer i : outsidePlanes) {
//                                                planes[i].getReadyToFly();
//                                            }
//                                            outsidePlanes.clear();
//                                        }
//                                    }
//                                }
//                            }
//
//                        }, 1000);   // 等待一秒后执行
//                    }
//                });
//                // FIXME: 2020/12/8 更改具体listener代码
//            }
//        }
                while (!hasPressedButton) {
                    if (hasPressedButton) break;
                }
                hasPressedButton = false;
                int[] rollResult = {Dice.roll(), Dice.roll()};

                ArrayList<Integer> outsidePlanes = new ArrayList<Integer>();
//                // 如果是迭在别人迭子上则自动走那个棋子
//                if (markPlane != -1) {
//                    planes[markPlane].receiveDiceNumber(rollResult);
//                    markPlane = -1;
//                } else {
                // 是否全在机场
                for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
                    if (!planes[i].isInHangar() && !planes[i].isFinished()) {
                        // 添加在外面的飞机number
                        outsidePlanes.add(i);
                    }
                }

                boolean ableToTakeOff = rollResult[0] == 6 || rollResult[1] == 6;
                if (ableToTakeOff) {
                    // 是起飞的点数则当前回合的所有飞机都可飞
                    for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
                        if (!planes[i].isFinished())
                            try {
                                planes[i].getReadyToFly();
                            } catch (Exception e) {
                                planes[i].getPlaneView().setEnabled(false);
                            }
                    }
                } else {
                    // 不是起飞点数则只有在外面的飞机可以飞
                    if (outsidePlanes.isEmpty()) {
                        nowPlayer = (nowPlayer + 1) % 4;
                        beginTurn();
                    } else {
                        for (Integer i : outsidePlanes) {
                            try {
                                planes[i].getReadyToFly();
                            } catch (Exception e) {
                                planes[i].getPlaneView().setEnabled(false);
                            }
                        }
                        outsidePlanes.clear();
                    }
                }
            }
        }

        // 联机模式，在己方看来其他三个是人是AI都一样，在他们回合都是要等待diceNumber和飞机编号
        else {
            if (nowPlayer == myColor) { //己方回合
                if (playerType[myColor] == GameState.COMPUTER) {    // 己方为AI
                    ComputerAgent.agentTakeOver();
                } else {   // 己方为人类
                    // TODO: 2020/12/8 add code
                    // 同上等待点击骰子获取diceNumber，能走就等待点击飞机
                    // 从上一步获取骰子点数diceNumber和选择的飞机编号（没有则-1）发送给其他三位玩家

                    while (!hasPressedButton) {
                        if (hasPressedButton) break;
                    }
                    hasPressedButton = false;
                    int[] rollResult = {Dice.roll(), Dice.roll()};

                    ArrayList<Integer> outsidePlanes = new ArrayList<Integer>();
//                // 如果是迭在别人迭子上则自动走那个棋子
//                if (markPlane != -1) {
//                    planes[markPlane].receiveDiceNumber(rollResult);
//                    markPlane = -1;
//                } else {
                    // 是否全在机场
                    for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
                        if (!planes[i].isInHangar() && !planes[i].isFinished()) {
                            // 添加在外面的飞机number
                            outsidePlanes.add(i);
                        }
                    }

                    boolean ableToTakeOff = rollResult[0] == 6 || rollResult[1] == 6;
                    if (ableToTakeOff) {
                        // 是起飞的点数则当前回合的所有飞机都可飞
                        for (int i : BoardCoordinate.COLOR_PLANE_NUMBER[nowPlayer]) {
                            if (!planes[i].isFinished())
                                try {
                                    planes[i].getReadyToFly();
                                } catch (Exception e) {
                                    planes[i].getPlaneView().setEnabled(false);
                                }
                        }
                    } else {
                        // 不是起飞点数则只有在外面的飞机可以飞
                        if (outsidePlanes.isEmpty()) {
                            nowPlayer = (nowPlayer + 1) % 4;
                            beginTurn();
                        } else {
                            for (Integer i : outsidePlanes) {
                                try {
                                    planes[i].getReadyToFly();
                                } catch (Exception e) {
                                    planes[i].getPlaneView().setEnabled(false);
                                }
                            }
                            outsidePlanes.clear();
                        }
                    }


                    Client.updateRecordedChange();
                }
            } else {   // 其他玩家回合
                Client.getAndApplyChange();
            }
        }
    }

    public void setWinner1Index(int winner1Index) {
        this.winner1Index = winner1Index;
    }

    public void allowClick() {
        for (Aeroplane p : planes) {
            if (!p.isFinished())
                p.getPlaneView().setEnabled(true);
        }
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setNowPlayer(int nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public int getStep() {
        return step;
    }

    public int getNowPlayer() {
        return nowPlayer;
    }

    public Aeroplane[] getPlanes() {
        return planes;
    }

    // 结束回合
    public void endTurn() {
        // 检查游戏是否结束
        if (checkGameEnd()) {
            endGame();
        } else {
            // 游戏没有结束
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
                nowPlayer = (nowPlayer + 1) % GameInfo.getHumanPlayerCnt();
                movedPlanes.clear();
                beginTurn();
            }
        }
    }


    // 判断index上有没有其他方的棋子
    public boolean hasOtherPlane(int index) {
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getColor() != nowPlayer) return true;
        }
        return false;
    }

    // 获取所有当前格子上的敌机以battle
    public ArrayList<Aeroplane> getOppoPlanes(int index) {
        ArrayList<Aeroplane> p = new ArrayList<>();
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getColor() != nowPlayer) p.add(plane);
        }
        return p;
    }


    // 获取index上的飞机数目
    public int planeNumOnIndex(int index) {
        int planeNum = 0;
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index) {
                planeNum++;
            }
        }
        return planeNum;
    }


    // 结束游戏
    public void endGame() {
        new EndGameAndShowRank(this).setVisible(true);

        // 联网模式还要广播获胜消息
        if (GameInfo.isIsOnlineGame()) {
            ?
        }
    }


    public ArrayList<Integer> getMovedPlanes() {
        return movedPlanes;
    }
}
