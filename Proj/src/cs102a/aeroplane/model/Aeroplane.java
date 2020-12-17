package cs102a.aeroplane.model;

import cs102a.aeroplane.frontend.Battle;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;

import static cs102a.aeroplane.presets.BoardCoordinate.COLOR_PATH;

public class Aeroplane {
    private final int itsHangar;
    private final int number;               // 飞机编号，0~15
    private final int color;
    private final ChessBoard chessBoard;    // 传递句柄给PlaneView
    private int selfPathIndex;              // 自己该走完的57格
    private int generalGridIndex;           // 飞机所在位置0~97，-1为完成路径
    private final PlaneView planeView;

    public Aeroplane(ChessBoard chessBoard, int color, int number, int itsHangar, int xOffSet, int yOffSet) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.generalGridIndex = itsHangar;
        this.itsHangar = itsHangar;

        selfPathIndex = -1;

        planeView = new PlaneView(chessBoard, number, color, itsHangar, xOffSet, yOffSet, this);
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


    public boolean isInHangar() {
        return planeView.getState() == PlaneState.IN_HANGAR;
    }

    /**
     * @param steps 选择向前移动的步数
     * @apiNote 自动判定移动过去后有无特殊事件（如跳子，撞机）发生，包括负责播放音效
     */
    public void tryMovingFront(int steps) {
        // 如果能在机场，无论骰出多少，直接走到起飞处
        if (planeView.getState() == PlaneState.IN_HANGAR) {
            selfPathIndex = 0;
            generalGridIndex = COLOR_PATH[color][0];
        } else {

            // 不过终点，先移动再判断特殊规则
            if (generalGridIndex + steps < BoardCoordinate.PATH_LENGTH) {

                // 判断这一步会不会碰上其他方
                if (chessBoard.hasOtherPlane(COLOR_PATH[color][generalGridIndex + steps])) {
                    // 不是自己格子，battle
                    if (!onSameColorGrid(COLOR_PATH[color][selfPathIndex + steps])) {
                        for (Aeroplane p : chessBoard.getOppoPlanes(COLOR_PATH[color][selfPathIndex + steps])) {
                            if (Battle.isWinner()) {
                                p.backToHangarDueToCrash();
                            } else {
                                this.backToHangarDueToCrash();
                                break;
                            }
                        }
                        if (planeView.getState() != PlaneState.IN_HANGAR)
                            generalGridIndex = COLOR_PATH[color][selfPathIndex + steps];
                    } else {
                        // 是自己格子，无条件赶走对方
                        for (Aeroplane p : chessBoard.getOppoPlanes(COLOR_PATH[color][selfPathIndex + steps]))
                            p.backToHangarDueToCrash();

                        // 且跳到下一个同色格子，赶走这个格子的对方
                        if (isJetGrid(COLOR_PATH[color][selfPathIndex + steps]) == -1) {
                            generalGridIndex = getNextGridWhenOnSelfColorGrid(COLOR_PATH[color][selfPathIndex + steps]);
                            for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                            Sound.JUMP.play(false);
                        } else {
                            generalGridIndex = isJetGrid(COLOR_PATH[color][selfPathIndex + steps]);
                            for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_JET[color][0]))
                                p.backToHangarDueToCrash();
                            for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                            Sound.JET.play(false);
                        }
                    }
                }
            } else {
                // 判断这一步会不会碰上其他方
                // FIXME: 2020/12/16 检查数学计算有没有出错
                if (chessBoard.hasOtherPlane(COLOR_PATH[color][2 * BoardCoordinate.PATH_LENGTH - generalGridIndex - steps])) {
                    // 是自己格子，无条件赶走对方
                    for (Aeroplane p : chessBoard.getOppoPlanes(COLOR_PATH[color][2 * BoardCoordinate.PATH_LENGTH - generalGridIndex - steps]))
                        p.backToHangarDueToCrash();
                    generalGridIndex = COLOR_PATH[color][2 * BoardCoordinate.PATH_LENGTH - generalGridIndex - steps];
                }
            }
        }
        move();
    }


    /**
     * @throws AssertionError 档案描述了错误的坐标
     * @apiNote 用于读档或联机游戏时设置单个棋子的状态
     */
    public void setGeneralGridIndexAndMove(int generalGridIndex) throws AssertionError {
        assert generalGridIndex >= -1 && generalGridIndex <= 96
                : "先辈这次动作挺小的，但还是被我发现啦！\n棋子怎么可能飞到棋盘外呢？先 辈 大 笨 蛋";
        this.generalGridIndex = generalGridIndex;
        if (generalGridIndex == -1) {
            setState(PlaneState.FINISH);
            planeView.finish();
        } else if (generalGridIndex == itsHangar) setState(PlaneState.IN_HANGAR);
        else planeView.moveTo(generalGridIndex);
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

    private boolean onSameColorGrid(int index) {
        for (int i : BoardCoordinate.COLOR_GRID[color]) {
            if (i == index) return true;
        }
        return false;
    }

    public void move() {
        if (generalGridIndex == BoardCoordinate.COLOR_DESTINATION[color]) backToHangarWhenFinish();
        else planeView.moveTo(generalGridIndex);
        // 结束回合
        chessBoard.endTurn();
        chessBoard.getMovedPlanes().add(this.number);
    }

    /**
     * @apiNote 通过index获取在自己路径上的下标
     */
    public int getSelfPathIndexFromGeneralIndex(int index) {
        int step = -1;
        for (int i = 0; i < COLOR_PATH[color].length; i++) {
            if (index == COLOR_PATH[color][i]) {
                step = i;
                break;
            }
        }
        return step;
    }

    public boolean isFinished() {
        return planeView.getState() == PlaneState.FINISH;
    }

    public void backToHangarDueToCrash() {
        this.generalGridIndex = itsHangar;
        this.selfPathIndex = -1;
        planeView.setState(PlaneState.IN_HANGAR);
        Sound.CRACK.play(false);
        planeView.moveTo(itsHangar);
    }

    protected void backToHangarWhenFinish() {
        this.selfPathIndex = -1;
        this.generalGridIndex = itsHangar;
        planeView.finish();
        Sound.FINISH_ONE_PLANE.play(false);
    }

    protected void backToHangarForInit() {
        this.planeView.setState(PlaneState.IN_HANGAR);
        this.generalGridIndex = itsHangar;
        planeView.moveTo(itsHangar);
        planeView.setIconAsPlaneNum(1);
        planeView.setEnabled(false);
    }
}
