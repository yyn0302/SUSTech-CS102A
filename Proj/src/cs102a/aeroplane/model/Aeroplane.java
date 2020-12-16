package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.Battle;
import cs102a.aeroplane.frontend.SetStep;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;

public class Aeroplane {
    private final int itsHangar;
    private final int number;               // 飞机编号，0~15
    private final int color;

    private int selfPathIndex;              // 自己该走完的57格
    private int generalGridIndex;           // 飞机所在位置0~97，-1为完成路径

    private final PlaneView planeView;
    private final ChessBoard chessBoard;    // 传递句柄给PlaneView

    public Aeroplane(ChessBoard chessBoard, int color, int number, int itsHangar, int xOffSet, int yOffSet) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.generalGridIndex = itsHangar;
        this.itsHangar = itsHangar;

        selfPathIndex = -1;

        planeView = new PlaneView(chessBoard, number, color, itsHangar, xOffSet, yOffSet);
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public int getColor() {
        return color;
    }

    public int getState() {
        return planeView.getState();
    }

    public void setState(int state) {
        this.planeView.setState(state);
    }

    public int getNumber() {
        return number;
    }

    public int getGeneralGridIndex() {
        return generalGridIndex;
    }

    // FIXME: 2020/12/9 核对源代码进行更改
    //  @frontend
    public void receiveDiceNumber(int[] rollResult) throws Exception {
        if (isInAirport()) {
            if (rollResult[0] == 6 || rollResult[1] == 6) {
                planeView.setState(PlaneState.WAITING);
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
        this.planeView.setState(PlaneState.IN_HANGAR);
        this.generalGridIndex = 0;
    }

    public void setPath(int steps) {
        if(planeView.getState()==PlaneState.IN_HANGAR)重定向到起飞点
        for (int i = 1; i <= steps; i++) {
            // 不过终点，先移动再判断特殊规则
            if (selfPathIndex + i < BoardCoordinate.PATH_LENGTH) {
                path.add(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i]);
                // 判断这一步会不会碰上其他方
                if (chessBoard.hasOtherPlane(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i])) {
                    if (i == steps) {
                        // 不是自己格子，battle
                        if (!onSameColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps])) {
                            for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + steps])) {
                                if (Battle.isWinner()) {
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
                            if (!onSameColorGrid(BoardCoordinate.COLOR_NORM_PATH[color][selfPathIndex + i - j - 1])) {
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

    private boolean onSameColorGrid(int index) {
        for (int i : BoardCoordinate.COLOR_GRID[color]) {
            if (i == index) return true;
        }
        return false;
    }

    // 等待被点击飞行
    public void getReadyToFly() {
        this.planeView.setEnabled(true);
        this.planeView.makeItReadyToBeSelected();
    }


    public void setGeneralGridIndex(int generalGridIndex) {
        assert generalGridIndex >= -1 && generalGridIndex <= 96
                : "先辈这次动作挺小的，但还是被我发现啦！\n棋子怎么可能飞到棋盘外呢？先 辈 大 笨 蛋";
        this.generalGridIndex = generalGridIndex;
        if (generalGridIndex == -1) finishTask();
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
