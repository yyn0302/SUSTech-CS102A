package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.util.MusicPlayer;
import cs102a.aeroplane.online.Client;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.GameState;
import cs102a.aeroplane.presets.Hangar;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.Dice;

import java.util.ArrayList;
import java.util.Random;

public class ChessBoard {
    private int state;              // 状态（游戏未开始，游戏已开始，游戏结束）
    private int nowPlayer;          // 当前回合
    private float screenWidth;      // 屏幕宽度
    private final float boardLength;      // 棋盘宽度
    private final float gridLength;       // 棋盘上一小格的宽度
    private float xOffSet;          // 棋盘在屏幕X方向即右方向的偏移
    private float yOffSet;          // 棋盘在屏幕Y方向即下方向的偏移
    private int step;               // 总步数

    int[] rollResult;
    private int continueRoll;

    ArrayList<ArrayList<Integer>> stackingPlanes = new ArrayList<>();     // 记录有无叠子，四个子ArrayList存飞机number
    ArrayList<Integer> movedPlanes = new ArrayList<>();                   // 记录一个人摇多次时，移动过哪些棋子

    // FIXME: 2020/12/3 不知道popupmenu合不合适
//    private JPopupMenu tipView;       // 提示view
//    private int[] diceNumbers;         // 骰子点数
    private Aeroplane[] planes;              // 16架飞机
//    private int markPlane;                   // 被标记的飞机，下次自动走，在迭在别人迭子上时用
    private int winner1Index;                // 胜利者
    private int winner2Index;                // 胜利者
    private int winner3Index;                // 胜利者
    //    private TextView[] playerViews; // 4个玩家信息view
//    private SoundPool sp;           // 音效池
    // private HashMap<Integer, Integer> soundMap;     // 音效类型到音效id的映射
    private int gameType;                           // 游戏类型，单机、联网
    private int[] playerType;                       // 四个玩家类型，人类、AI
    private int myColor;                             // 自己阵营

    //    ChessBoard(/*ImageView boardView, ImageView diceView, ImageView arrowView, JPopupMenu tipView, float screenWidth, TextView[] playerViews, SoundPool sp,*/
//            /*HashMap<Integer, Integer> soundMap*/) {
    ChessBoard() {
        this.state = GameState.GAME_READY;
        // FIXME: 2020/12/8 把目标窗口大小赋值给 screenWidth
        this.screenWidth = screenWidth;
//        this.boardView = boardView;
//        this.diceView = diceView;
//        this.arrowView = arrowView;
//        this.tipView = tipView;
//        this.sp = sp;
//        this.soundMap = soundMap;
        boardLength = (int) (screenWidth / 18) * 18;
        gridLength = boardLength / 36;
        step=0;
        // 调整棋盘大小
//        ViewGroup.LayoutParams boardParams = boardView.getLayoutParams();
//        boardParams.width = (int) boardLength;
//        boardParams.height = (int) boardLength;
//        boardView.setLayoutParams(boardParams);
        // 调整提示框大小
//        ViewGroup.LayoutParams tipParams = tipView.getLayoutParams();
//        tipParams.width = (int) boardLength;
//        tipParams.height = (int) (boardLength * 0.5);
//        tipView.setLayoutParams(tipParams);

//        this.playerViews = new TextView[4];
//        this.playerViews[0] = playerViews[0];
//        this.playerViews[1] = playerViews[1];
//        this.playerViews[2] = playerViews[2];
//        this.playerViews[3] = playerViews[3];
        continueRoll = 0;
    }

    // 初始化飞机
    public void initPlanes() {
//    public void initPlanes(ImageView[] planeViews) {
        planes = new Aeroplane[]{
                new Aeroplane(this, Hangar.BLUE, 0, 0, gridLength, xOffSet, yOffSet),// planeViews[0]),
                new Aeroplane(this, Hangar.BLUE, 1, 1, gridLength, xOffSet, yOffSet),// planeViews[1]),
                new Aeroplane(this, Hangar.BLUE, 2, 2, gridLength, xOffSet, yOffSet),// planeViews[2]),
                new Aeroplane(this, Hangar.BLUE, 3, 3, gridLength, xOffSet, yOffSet),// planeViews[3]),
                new Aeroplane(this, Hangar.GREEN, 4, 5, gridLength, xOffSet, yOffSet),// planeViews[4]),
                new Aeroplane(this, Hangar.GREEN, 5, 6, gridLength, xOffSet, yOffSet),// planeViews[5]),
                new Aeroplane(this, Hangar.GREEN, 6, 7, gridLength, xOffSet, yOffSet),// planeViews[6]),
                new Aeroplane(this, Hangar.GREEN, 7, 8, gridLength, xOffSet, yOffSet),// planeViews[7]),
                new Aeroplane(this, Hangar.RED, 8, 10, gridLength, xOffSet, yOffSet),// planeViews[8]),
                new Aeroplane(this, Hangar.RED, 9, 11, gridLength, xOffSet, yOffSet),// planeViews[9]),
                new Aeroplane(this, Hangar.RED, 10, 12, gridLength, xOffSet, yOffSet),// planeViews[10]),
                new Aeroplane(this, Hangar.RED, 11, 13, gridLength, xOffSet, yOffSet),// planeViews[11]),
                new Aeroplane(this, Hangar.YELLOW, 12, 15, gridLength, xOffSet, yOffSet),// planeViews[12]),
                new Aeroplane(this, Hangar.YELLOW, 13, 16, gridLength, xOffSet, yOffSet),// planeViews[13]),
                new Aeroplane(this, Hangar.YELLOW, 14, 17, gridLength, xOffSet, yOffSet),// planeViews[14]),
                new Aeroplane(this, Hangar.YELLOW, 15, 18, gridLength, xOffSet, yOffSet)// planeViews[15]),
        };
    }

    public ArrayList<Integer> getMovedPlanes() {
        return movedPlanes;
    }

    // 开始游戏
    public void startGame() {
        playSound(GameInfo.getTheme() == 1 ? Sound.GAMING_THEME1 : Sound.GAMING_THEME2, true);
        // 禁止点击棋子
//        forbidClick();
        // 初始化游戏类型，玩家类型
        gameType = GameInfo.isIsOnlineGame() ? GameState.ONLINE : GameState.LOCAL;
        playerType = new int[4];
        for (int i = 0; i < 4; i++) {
            if (i < GameInfo.getHumanPlayerCnt()) playerType[i] = GameState.HUMAN;
            else playerType[i] = GameState.COMPUTER;
        }
        // 如果是联网模式，还要初始化myCamp xxxx


        if (GameInfo.isIsOnlineGame()) myColor = Client.getDeltaPort();

        state = GameState.GAME_START;
        // 还原飞机位置
        for (Aeroplane plane : planes) {
            plane.restore();
        }
        // 随机决定哪方先开始
        Random rd = new Random();
        nowPlayer = rd.nextInt(4);
//        showInfo(Commdef.campName[nowPlayer] + "开始");
//        markPlane = -1;
        winner1Index = -1;
        winner2Index = -1;
        winner3Index = -1;
        beginTurn();
    }


    // 结束游戏
    // TODO: 2020/12/8 socket 广播游戏结束
    public void recordOnePlayerEnd() {
        if (winner1Index == -1) winner1Index = nowPlayer;
        else if (winner2Index == -1) winner2Index = nowPlayer;
        else if (winner3Index == -1) winner3Index = nowPlayer;
//        showInfo("恭喜" + Commdef.campName[winner] + "获得胜利!!");
        if (winner3Index != -1) state = GameState.GAME_END;

        // 联网模式还要广播获胜消息
        if (gameType == GameState.ONLINE) {
// TODO: 2020/12/8 socket 广播游戏结束
        }
    }


    // 检查游戏是否结束
    public boolean checkGameEnd() {
//        int finishPlaneNum = 0;
//        for (int i : BoardCoordinate.COLOR_PLANE[nowPlayer]) {
//            if (planes[i].getState() == PlaneState.FINISH) finishPlaneNum++;
//        }
//        if (finishPlaneNum == 4) return true;
//        else return false;
        return state == GameState.GAME_END;
    }

    // 调整index上的飞机坐标，迭子情况下有飞机飞走了就会调整
    // FIXME: 2020/12/9 planeView用JButton，需要用布局器调整
    public void adjustPosition(int index, int number) {
        int planeNum = 0;
        float indexX = getXFromIndex(index);
        float indexY = getYFromIndex(index);
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getNumber() != number) {
                float adjustX = 0, adjustY = 0;
                switch (BoardCoordinate.OVERLAP_DIRECTION[index]) {
                    case BoardCoordinate.UP:
                        adjustX = indexX;
                        adjustY = indexY - BoardCoordinate.STACK_DISTANCE * gridLength * planeNum;
                        break;
                    case BoardCoordinate.DOWN:
                        adjustX = indexX;
                        adjustY = indexY + BoardCoordinate.STACK_DISTANCE * gridLength * planeNum;
                        break;
                    case BoardCoordinate.LEFT:
                        adjustX = indexX - BoardCoordinate.STACK_DISTANCE * gridLength * planeNum;
                        adjustY = indexY;
                        break;
                    case BoardCoordinate.RIGHT:
                        adjustX = indexX + BoardCoordinate.STACK_DISTANCE * gridLength * planeNum;
                        adjustY = indexY;
                        break;
                }
                // FIXME: 2020/12/8 planeView extends android.Textview
                plane.getPlaneView().setX(adjustX);
                plane.getPlaneView().setY(adjustY);
                planeNum++;
            }
        }
    }


    private static boolean hasPressedButton = false;

    // TODO: 2020/12/11 按按钮改变boolean值
    public static void setHasPressedButton(boolean hasPressedButton) {
        ChessBoard.hasPressedButton = hasPressedButton;
    }


    // 开始回合
    public void beginTurn() {
        // 调整骰子的位置
//        if (nowPlayer == Hangar.BLUE || nowPlayer == Hangar.GREEN) {
//            diceView.setX((float) (playerViews[nowPlayer].getX() + playerViews[nowPlayer].getWidth() * 0.64));
//            diceView.setY((float) (playerViews[nowPlayer].getY() + playerViews[nowPlayer].getHeight() * 0.3));
//        } else {
//            diceView.setX((float) (playerViews[nowPlayer].getX() + playerViews[nowPlayer].getWidth() * 0.07));
//            diceView.setY((float) (playerViews[nowPlayer].getY() + playerViews[nowPlayer].getHeight() * 0.3));
//        }
//        diceView.setVisibility(View.VISIBLE);
        // 根据游戏类型和玩家类型做判断
        // 当前是单机游戏
        if (!GameInfo.isIsOnlineGame()) {
            // 如果当前回合是AI
            if (playerType[nowPlayer] == GameState.COMPUTER) {
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
                for (int i : BoardCoordinate.COLOR_PLANE[nowPlayer]) {
                    if (!planes[i].isInAirport() && !planes[i].isFinished()) {
                        // 添加在外面的飞机number
                        outsidePlanes.add(i);
                    }
                }

                boolean ableToTakeOff = rollResult[0] == 6 || rollResult[1] == 6;
                if (ableToTakeOff) {
                    // 是起飞的点数则当前回合的所有飞机都可飞
                    for (int i : BoardCoordinate.COLOR_PLANE[nowPlayer]) {
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
                    for (int i : BoardCoordinate.COLOR_PLANE[nowPlayer]) {
                        if (!planes[i].isInAirport() && !planes[i].isFinished()) {
                            // 添加在外面的飞机number
                            outsidePlanes.add(i);
                        }
                    }

                    boolean ableToTakeOff = rollResult[0] == 6 || rollResult[1] == 6;
                    if (ableToTakeOff) {
                        // 是起飞的点数则当前回合的所有飞机都可飞
                        for (int i : BoardCoordinate.COLOR_PLANE[nowPlayer]) {
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

    public void forbidClick() {
        for (Aeroplane p : planes) {
            p.getPlaneView().setEnabled(false);
            p.getPlaneView().addActionListener(null);
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

// 横扫其他方的飞机
// FIXME: 2020/12/8 可能改规则
//    public void sweepOthersOnSelfColorGrid(int index) {
//        for (Aeroplane plane : planes) {
//            if (plane.getIndex() == index && plane.getColor() != nowPlayer) {
////                showInfo("撞子啦");
//                plane.crackByPlane();
//            }
//        }
//    }

// 禁止点击任何棋子并清除动画效果（因为可以飞的飞机会跑一个不断缩放的动画来提醒）
//    public void forbidClick() {
//        for (Aeroplane plane : planes) {
////            plane.getPlaneView().setClickable(false);
//            plane.getPlaneView().clearAnimation();
//        }
//    }

    // 判断index上有没有其他方的迭子
//    public boolean isOverlap(int index) {
//        int planeNum = 0;
//        for (Aeroplane plane : planes) {
//            if (plane.getIndex() == index && plane.getColor() != nowPlayer) {
//                planeNum++;
//                if (planeNum >= 2) return true;
//            }
//        }
//        return false;
//    }

    // 判断index上有没有其他方的棋子
    public boolean hasOtherPlane(int index) {
        for (Aeroplane plane : planes) {
            if (plane.getGeneralGridIndex() == index && plane.getColor() != nowPlayer) return true;
        }
        return false;
    }

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

//// 提示
//    public void showInfo(String sentence) {
//        Label tipView;
//        tipView.setText(sentence);
//        tipView.bringToFront();
//        tipView.setVisibility(View.VISIBLE);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                // 一秒后消失
//                tipView.setVisibility(View.GONE);
//            }
//
//        }, 1000);   // 等待一秒后执行
//    }

    // 播放音效
    public void playSound(Sound sound, boolean isLoop) {
//        int streamID = sp.play(soundMap.get(soundId), 0.8f, 0.8f, 1, 0, 1.0f);
        MusicPlayer player = new MusicPlayer(sound);
//        player.setVolume(6f);
        player.setLoop(isLoop);
        player.play();
    }

//    public int getDiceNumbers() {
//        return diceNumbers;
//    }

    public float getXFromIndex(int index) {
        return xOffSet + gridLength * BoardCoordinate.COORDINATE[index][0];
    }

    public float getYFromIndex(int index) {
        return yOffSet + gridLength * BoardCoordinate.COORDINATE[index][1];
    }

//    public void setXOffSet(float xOffSet) {
//        this.xOffSet = xOffSet;
//    }
//
//    public void setYOffSet(float yOffSet) {
//        this.yOffSet = yOffSet;
//        // 调整骰子大小
////        ViewGroup.LayoutParams diceParams = diceView.getLayoutParams();
////        diceParams.width = (int) (yOffSet * 0.4);
////        diceParams.height = (int) (yOffSet * 0.4);
////        diceView.setLayoutParams(diceParams);
////        // 调整箭头大小
////        ViewGroup.LayoutParams arrowParams = arrowView.getLayoutParams();
////        arrowParams.width = (int) (yOffSet * 0.4);
////        arrowParams.height = (int) (yOffSet * 0.4);
////        arrowView.setLayoutParams(arrowParams);
////        // 调整玩家信息框的大小
////        for (int i = 0; i < 4; i++) {
////            ViewGroup.LayoutParams playerParams = playerViews[i].getLayoutParams();
////            playerParams.width = (int) (yOffSet * 1.4);
////            playerParams.height = (int) (yOffSet);
////            playerViews[i].setLayoutParams(playerParams);
////        }
//    }

//    public void setMarkPlane(int number) {
//        markPlane = number;
//    }

    // 结束游戏
    public void endGame() {
        state = GameState.GAME_END;

        // 联网模式还要广播获胜消息
        if (gameType == GameState.ONLINE) {
            Client.notifyNewWinner();
        }
    }
}
