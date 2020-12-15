package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.frontend.SetStep;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Aeroplane {

    private final int number;           // 飞机编号，0~15
    private final int color;

    private int selfPathIndex;    // 自己该走完的57格
    private int generalGridIndex;       // 飞机所在位置0~97

    private final PlaneView planeView;
    private final ChessBoard chessBoard;    // 传递句柄给PlaneView


    public Aeroplane(ChessBoard chessBoard, int color, int number, int generalGridIndex, float gridLength, float xOffSet, float yOffSet) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.generalGridIndex = generalGridIndex;

        selfPathIndex = -1;
        state = PlaneState.IN_HANGAR;
        path = new ArrayList<>();

        // 飞机的view实际上是JButton，每次只有己方未完成的飞机可以点击
        // TODO: 2020/12/9 add code 可以点击

        iconPath.append(SystemSelect.getImagePath());
        iconPath.append(GameInfo.getTheme() == 1 ? "plane_theme1_" : "plane_");
        switch (color) {
            case PlaneState.BLUE:
                iconPath.append("blue.png");
                break;
            case PlaneState.GREEN:
                iconPath.append("green.png");
                break;
            case PlaneState.RED:
                iconPath.append("red.png");
                break;
            case PlaneState.YELLOW:
                iconPath.append("yellow.png");
                break;
        }
        planeView = new JButton();
        this.planeView.setIcon(new ImageIcon(iconPath.toString()));
    }

    public int getColor() {
        return color;
    }

//    public int getContinueRoll() {
//        return continueRoll;
//    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public int getState() {
        return state;
    }

    public int getNumber() {
        return number;
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public int getGeneralGridIndex() {
        return generalGridIndex;
    }

    // FIXME: 2020/12/9 核对源代码进行更改
    //  @frontend
    public void receiveDiceNumber(int[] rollResult) throws Exception {
        planeView.bringToFront();
//         FIXME: 2020/12/7 把飞机view提到布局最高层，从而实现飞过其他棋子时覆盖它们

        if (isInAirport()) {
            if (rollResult[0] == 6 || rollResult[1] == 6) {
                state = PlaneState.WAITING;
                setPath(1);
            } else throw new Exception("不满足起飞条件，无法选择移动此飞机！");
        } else if (state == PlaneState.WAITING || state == PlaneState.ON_BOARD) {
            if (state == PlaneState.WAITING) state = PlaneState.ON_BOARD;
            setPath(SetStep.askPlayerStep(rollResult));
        }

        // 离开当前位置时把当前位置的其他飞机重新调整位置
        chessBoard.adjustPosition(path.get(path.size() - 1), number);
        move();
    }

    public boolean isInAirport() {
        return state == PlaneState.IN_HANGAR;
    }

    public boolean isFinished() {
        return state == PlaneState.FINISH;
    }

    public void backToHangarDueToCrash() {
        this.state = PlaneState.IN_HANGAR;
        this.generalGridIndex = 0;
        this.path.add(0);
        chessBoard.playSound(Sound.CRACK, false);
    }

    protected void backToHangarWhenFinish() {
        this.state = PlaneState.IN_HANGAR;
        this.generalGridIndex = 0;
        this.path.clear();
        chessBoard.playSound(Sound.FINISH_ONE_PLANE, false);
    }

    protected void backToHangarForInit() {
        this.state = PlaneState.IN_HANGAR;
        this.generalGridIndex = 0;
        this.path.clear();
    }

    public void setPath(int steps) {
        for (int i = 1; i <= steps; i++) {
            // 不过终点，先移动再判断特殊规则
            if (selfPathIndex + i < BoardCoordinate.PATH_LENGTH) {
                path.add(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i]);
                // 判断这一步会不会碰上其他方
                if (chessBoard.hasOtherPlane(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i])) {
                    if (i == steps) {
                        // 不是自己格子，battle
                        if (!isSameColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps])) {
                            for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps])) {
                                if (isWinnerInBattling()) {
                                    p.backToHangarDueToCrash();
                                } else {
                                    this.backToHangarDueToCrash();
                                    break;
                                }
                                if (this.state != PlaneState.IN_HANGAR)
                                    path.add(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]);
                            }
                        } else {
                            // 是自己格子，无条件赶走对方
                            for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]))
                                p.backToHangarDueToCrash();

                            // 且跳到下一个同色格子，赶走这个格子的对方
                            for (Aeroplane p : chessBoard.getOppoPlanes(isJetGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]) == -1 ?
                                    getNextGridWhenOnSelfColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]) :
                                    isJetGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps])))
                                p.backToHangarDueToCrash();

                            if (isJetGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]) != -1) {
                                chessBoard.adjustPosition(isJetGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]), number);
                                for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_JET[color][1]))
                                    p.backToHangarDueToCrash();
                            } else {
                                chessBoard.adjustPosition(getNextGridWhenOnSelfColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps]), number);
                            }
                            move();
                        }
                    }
                }
            } else {
                for (int j = 1; j <= steps - i + 1; j++) {
                    path.add(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1]);
                    // 判断这一步会不会碰上其他方
                    if (chessBoard.hasOtherPlane(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1])) {
                        if (j == steps - i + 1) {
                            // 不是自己格子，battle
                            if (!isSameColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1])) {
                                for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1])) {
                                    if (isWinnerInBattling()) {
                                        p.backToHangarDueToCrash();
                                    } else {
                                        this.backToHangarDueToCrash();
                                        break;
                                    }
                                    if (this.state != PlaneState.IN_HANGAR)
                                        path.add(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1]);
                                }
                            } else {
                                // 是自己格子，无条件赶走对方
                                for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1]))
                                    p.backToHangarDueToCrash();

                                // 且跳到下一个同色格子，赶走这个格子的对方
                                for (Aeroplane p : chessBoard.getOppoPlanes(isJetGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1])))
                                    p.backToHangarDueToCrash();

                                chessBoard.adjustPosition(getNextGridWhenOnSelfColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1]), number);
                                move();
                            }
                        }
                    }
                }
            }


        }
    }

    private boolean isSameColorGrid(int index) {
        for (int i : BoardCoordinate.COLOR_GRID[color]) {
            if (i == index) return true;
        }
        return false;
    }

    public void setState(int state) {
        this.state = state;
    }

    // 等待被点击飞行
    // FIXME: 2020/12/11 这玩意怎么写呜呜呜
    public void getReadyToFly() {
        this.planeView.setEnabled(true);

        // FIXME: 2020/12/11 这玩意怎么写呜呜呜  lambda?
        this.planeView.addActionListener();
        {
            // 被点击时禁止其他的棋子再被点击
            chessBoard.forbidClick();
            try {
                this.receiveDiceNumber(chessBoard.rollResult);
            } catch (Exception e) {
//                throw e;
            }
        }

        // 做一个不断重复的缩放动画，告诉玩家可以移动的棋子
//        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.ABSOLUTE, planeView.getX()+gridLength, Animation.ABSOLUTE, planeView.getY()+gridLength);
//        scaleAnim.setDuration(500);     //设置动画持续时间
//        scaleAnim.setRepeatCount(-1);   //设置重复次数，-1无限循环
//        scaleAnim.setRepeatMode(Animation.REVERSE); // 逆序重复
//        scaleAnim.setFillAfter(false);              // 不用停在最后一帧
//        planeView.startAnimation(scaleAnim);
//        planeView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 被点击时禁止其他的棋子再被点击
//                board.forbidClick();
//                // 调用函数来应用这个点数
//                receiveDiceNumber(board.getDiceNumber());
//            }
//        });
    }


    private int battlingSelfNumber;
    private int battlingOpposingNumber;
    private boolean battlingCheatChoice;

    public int getBattlingSelfNumber() {
        return battlingSelfNumber;
    }

    public int getBattlingOpposingNumber() {
        return battlingOpposingNumber;
    }

    public void setGeneralGridIndex(int generalGridIndex) {
        assert generalGridIndex >= -1 && generalGridIndex <= 96
                : "先辈这次动作挺小的，但还是被我发现啦！\n棋子怎么可能飞到棋盘外呢？先 辈 大 笨 蛋";
        this.generalGridIndex = generalGridIndex;
        if (generalGridIndex == -1) finishTask();
        path.clear();
        path.add(generalGridIndex);
    }


    public void setBattlingCheatChoice(boolean battlingCheatChoice) {
        this.battlingCheatChoice = battlingCheatChoice;
    }

    // TODO: 2020/12/7 若是作弊模式，直接两个按钮选择在这局中想赢或者输，回传按钮值
    // 做一个battle弹窗，基于CustomerChoice改动
    private boolean isWinnerInBattling() {
        if (GameInfo.isIsCheatMode()) {
            // TODO: 2020/12/7 若是作弊模式，直接两个按钮选择在这局中想赢或者输，回传按钮值
            return battlingCheatChoice;
        } else {
            battlingSelfNumber = Dice.roll();
            battlingOpposingNumber = Dice.roll();
            // TODO: 2020/12/7 前端提示自己摇了多少，对方摇了多少
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
            }
            return battlingSelfNumber > battlingOpposingNumber;
        }
    }

    public int getNextGridWhenOnSelfColorGrid(int index) {
        int result = -1;
        for (int i = 0; i < BoardCoordinate.COLOR_GRID[color].length; i++) {
            if (index == BoardCoordinate.COLOR_GRID[color][i]
                    && i != BoardCoordinate.COLOR_GRID[color].length - 1) {
                result = BoardCoordinate.COLOR_GRID[color][i + 1];
                break;
            }
        }
        return result;
    }

    public int isJetGrid(int index) {
        int result = -1;
        if (index == BoardCoordinate.COLOR_JET[color][0])
            result = BoardCoordinate.COLOR_JET[color][2];
        return result;
    }

    // FIXME: 2020/12/11 旋转图片，布局坐标调整（可能由chessBoard负责）
    public void move() {
        generalGridIndex = path.get(path.size() - 1);
        // 控件要旋转的角度
        planeView.setRotation(BoardCoordinate.REVOLVE_ANGLE[generalGridIndex]);
        // FIXME: 2020/12/11 旋转图片
        // FIXME: 2020/12/11 控件旋转

//        planeView.setRotation(BoardCoordinate.REVOLVE_ANGLE[generalGridIndex]);
        // 如果最后一步上没有碰撞发生
//        if (path.size() == 1 && (crack.isEmpty() || crack.get(0) == BoardCoordinate.NO_CRACK)) {
        int planeNum = chessBoard.planeNumOnIndex(generalGridIndex);    // 获取最后一步位置上的飞机数目
        // 根据飞机数目对要去的坐标进行调整，避免几个棋子完全叠在一起看不到
        if (planeNum > 1) {
            switch (BoardCoordinate.OVERLAP_DIRECTION[generalGridIndex]) {
                case BoardCoordinate.UP:
                    targetX = getXFromIndex(generalGridIndex);
                    targetY = getYFromIndex(generalGridIndex) - BoardCoordinate.STACK_DISTANCE * gridLength * (planeNum - 1);
                    break;
                case BoardCoordinate.DOWN:
                    targetX = getXFromIndex(generalGridIndex);
                    targetY = getYFromIndex(generalGridIndex) + BoardCoordinate.STACK_DISTANCE * gridLength * (planeNum - 1);
                    break;
                case BoardCoordinate.LEFT:
                    targetX = getXFromIndex(generalGridIndex) - BoardCoordinate.STACK_DISTANCE * gridLength * (planeNum - 1);
                    targetY = getYFromIndex(generalGridIndex);
                    break;
                case BoardCoordinate.RIGHT:
                    targetX = getXFromIndex(generalGridIndex) + BoardCoordinate.STACK_DISTANCE * gridLength * (planeNum - 1);
                    targetY = getYFromIndex(generalGridIndex);
                    break;
            }
        } else {
            targetX = getXFromIndex(generalGridIndex);
            targetY = getYFromIndex(generalGridIndex);
        }
//        } else {
//            targetX = getXFromIndex(generalGridIndex);
//            targetY = getYFromIndex(generalGridIndex);
//        }
//        TranslateAnimation anim = new TranslateAnimation(0, targetX - planeView.getX(), 0, targetY - planeView.getY());
//        anim.setDuration(500);
//        anim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                planeView.clearAnimation();
//                path.remove(0);
//                planeView.setX(targetX);
//                planeView.setY(targetY);
//                // 现在path.size()表示还要走的步数
////                if (path.size() < crack.size()) {
////                    int crackType = crack.get(0);
////                    crack.remove(0);
////                    // 根据碰撞类型执行不同操作
////                    switch (crackType) {
////                        case Commdef.NO_CRACK:
////                            break;
////                        case Commdef.SWEEP_OTHERS:
////                            chessBoard.sweepOthers(index);
////                            break;
////                        case Commdef.DOWN_TOGETHER:
////                            // 同归于尽的话在path中增加停机处
////                            chessBoard.sweepOthers(index);
////                            path.add(portIndex);
////                            break;
////                        case Commdef.JET_CRACK:
////                            chessBoard.sweepOthers(Commdef.COLOR_JET[camp][1]);
////                            break;
////                        case Commdef.JET_CRACK_AND_SWEEP_OTHERS:
////                            chessBoard.sweepOthers(Commdef.COLOR_JET[camp][1]);
////                            chessBoard.sweepOthers(index);
////                            break;
////                        case Commdef.JET_CRACK_AND_DOWN_TOGETHER:
////                            chessBoard.sweepOthers(Commdef.COLOR_JET[camp][1]);
////                            chessBoard.sweepOthers(index);
////                            path.add(portIndex);
////                            break;
////                    }
////                }
//                if (!path.isEmpty()) move();
//                else {
//                    selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);
//                    path.clear();
//                    crack.clear();
        // 如果最后一步到达终点，飞机完成任务
        if (generalGridIndex == BoardCoordinate.COLOR_DESTINATION[color]) finishTask();
        // 结束回合
        chessBoard.endTurn();
        chessBoard.getMovedPlanes().add(this.number);
//                }
//            }
//        });
//        planeView.startAnimation(anim);
    }

    //     通过index获取在自己路径上的下标
    public int getSelfPathIndexFromGeneralIndex(int index) {
        int step = -1;
        for (int i = 0; i < BoardCoordinate.COLOR_NORM_PATH[color].length; i++) {
            if (index == BoardCoordinate.COLOR_NORM_PATH[color][i]) {
                step = i;
                break;
            }
        }
        return step;
    }

    // 通过index来获取在屏幕上的x坐标
//    public float getXFromIndex(int index) {
//        return xOffSet + gridLength * BoardCoordinate.COORDINATE[index][0];
//    }
//
//    public float getYFromIndex(int index) {
//        return yOffSet + gridLength * BoardCoordinate.COORDINATE[index][1];
//    }

//    public void crackByPlane() {
//
//    }

    // 通过index来获取在屏幕上的x坐标
    public float getXFromIndex(int index) {
        return xOffSet + gridLength * BoardCoordinate.GRID_CENTER_OFFSET[index][0];
    }

    // 通过index来获取在屏幕上的y坐标
    public float getYFromIndex(int index) {
        return yOffSet + gridLength * BoardCoordinate.GRID_CENTER_OFFSET[index][1];
    }


    public void finishTask() {
        StringBuilder iconPathWhenFinish = new StringBuilder();
        iconPathWhenFinish.append(SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath());
        switch (color) {
            case PlaneState.BLUE:
                iconPathWhenFinish.append("blue.png");
                break;
            case PlaneState.GREEN:
                iconPathWhenFinish.append("green.png");
                break;
            case PlaneState.RED:
                iconPathWhenFinish.append("red.png");
                break;
            case PlaneState.YELLOW:
                iconPathWhenFinish.append("yellow.png");
                break;
        }
        this.planeView.setIcon(new ImageIcon(iconPathWhenFinish.toString()));
        backToHangarWhenFinish();
        state = PlaneState.FINISH;
    }

    public void restore() {
        StringBuilder iconPath = new StringBuilder();
        iconPath.append(SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath());
        iconPath.append(GameInfo.getTheme() == 1 ? "plane_theme1_" : "plane_");
        switch (color) {
            case PlaneState.BLUE:
                iconPath.append("blue.png");
                break;
            case PlaneState.GREEN:
                iconPath.append("green.png");
                break;
            case PlaneState.RED:
                iconPath.append("red.png");
                break;
            case PlaneState.YELLOW:
                iconPath.append("yellow.png");
                break;
        }
        this.planeView.setIcon(new ImageIcon(iconPath.toString()));
        backToHangarDueToCrash();
        path.clear();
    }
}
