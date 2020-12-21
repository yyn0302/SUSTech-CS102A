package cs102a.aeroplane.model;

import cs102a.aeroplane.frontend.Battle;
import cs102a.aeroplane.frontend.SetStep;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.presets.Sound;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Timer;


import static cs102a.aeroplane.presets.BoardCoordinate.COLOR_PATH;

public class Aeroplane {
    private final int itsHangar;
    private final int number;               // 飞机编号，0~15
    private final int color;
    private final ChessBoard chessBoard;    // 传递句柄给PlaneView
    private final PlaneView planeView;
    private int selfPathIndex;              // 自己该走完的57格
    private int generalGridIndex;           // 飞机所在位置0~97，-1为完成路径
    public int indexOfTeam;                 // 当加入team时记录这个team在chessboard的index方便访问，不在队为-1

    public int steps;


    public Aeroplane(ChessBoard chessBoard, int color, int number, int itsHangar, int xOffSet, int yOffSet) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.generalGridIndex = itsHangar;
        this.itsHangar = itsHangar;
        this.indexOfTeam = -1;
        selfPathIndex = -1;
        planeView = new PlaneView(chessBoard, number, color, itsHangar, xOffSet, yOffSet, this);
        backToHangarForInit();
    }


    /**
     * @apiNote 自动判定移动过去后有无特殊事件（如跳子，撞机）发生，包括负责播放音效
     */
    public void tryMovingFront() {
//        chessBoard.checkStackForInit();
        this.planeView.setIconAsPlaneNum(chessBoard.getPartners(indexOfTeam).size());

        // 如果能在机场，无论骰出多少，直接走到起飞处
        if (planeView.getState() == PlaneState.IN_HANGAR) {

            selfPathIndex = 0;
            generalGridIndex = COLOR_PATH[color][0];

            planeView.setState(PlaneState.ON_BOARD);
            planeView.moveTo(generalGridIndex);
            chessBoard.rollResult = new int[]{Dice.roll(), Dice.roll()};
            SetStep.askPlayerStep(chessBoard, number);
            return;
        } else {
            // 不过终点，先移动再判断特殊规则
            if (selfPathIndex + steps < BoardCoordinate.PATH_LENGTH) {
                generalGridIndex = COLOR_PATH[color][selfPathIndex + steps];
                selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

                if (chessBoard.hasOtherPlane(generalGridIndex)) {
                    move();
                    // 不是自己格子，battle
                    if (!onSameColorGrid(generalGridIndex)) {
                        if (indexOfTeam == -1) {    // 一对多

                            for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) {
                                if (Battle.isWinner()) p.backToHangarDueToCrash();
                                else {
                                    this.backToHangarDueToCrash();
                                    return;
                                }
                                move();
                                Timer.delay(1100);
                            }
                            chessBoard.checkStackForInit();
                            for (Aeroplane p : chessBoard.getPartners(this.indexOfTeam))
                                p.getPlaneView().setIconAsPlaneNum(chessBoard.getPartners(this.indexOfTeam).size());

                        } else {     // 多对n
                            chessBoard.battleInTeam( this.generalGridIndex);
//                            if (getState() == PlaneState.IN_HANGAR) return;
                            move();
                        }
                    } else {
                        // 是自己格子，无条件赶走对方
                        for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex))
                            p.backToHangarDueToCrash();

                        // 且跳到下一个同色格子，赶走这个格子的对方
                        if (isJetGrid(generalGridIndex) == -1) {

                            generalGridIndex = getNextGridWhenOnSelfColorGrid(generalGridIndex);
                            selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

                            for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                            Sound.JUMP.play(false);
                        } else {

                            generalGridIndex = isJetGrid(selfPathIndex);
                            selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

                            for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_JET[color][1]))
                                p.backToHangarDueToCrash();
                            for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                            Sound.JET.play(false);
                        }
                    }
                } else if (onSameColorGrid(generalGridIndex)) {
                    if (isJetGrid(generalGridIndex) == -1) {
                        generalGridIndex = getNextGridWhenOnSelfColorGrid(generalGridIndex);
                        selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

                        for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                        Sound.JUMP.play(false);
                    } else {

                        generalGridIndex = isJetGrid(generalGridIndex);
                        selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

                        for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_JET[color][1]))
                            p.backToHangarDueToCrash();
                        for (Aeroplane p : chessBoard.getOppoPlanes(generalGridIndex)) p.backToHangarDueToCrash();
                        Sound.JET.play(false);
                    }
                }

            } else {
                // FIXME: 2020/12/16 检查数学计算有没有出错
                // 回来的路上不可能碰上别人
                generalGridIndex = COLOR_PATH[color][2 * BoardCoordinate.PATH_LENGTH - selfPathIndex - steps - 2];
                selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);
            }
        }
        move();
        checkStack();
        chessBoard.checkStackForInit();
    }

    public void checkStack() {
        System.err.println("checking stack by aeroplane");
        if (chessBoard.selfPlaneNumOnIndex(generalGridIndex) == 2) {     // 跳后可以叠子
            if (chessBoard.teamIndexUsed[color][0]) {
                for (Aeroplane p : chessBoard.getMyPlanes(generalGridIndex)) p.indexOfTeam = 1;
                chessBoard.teamIndexUsed[color][1] = true;
            } else {
                for (Aeroplane p : chessBoard.getMyPlanes(generalGridIndex)) p.indexOfTeam = 0;
                chessBoard.teamIndexUsed[color][0] = true;
            }
        } else if (chessBoard.selfPlaneNumOnIndex(generalGridIndex) > 2) {
            if (this.indexOfTeam == -1) {    // 本棋加入组
                this.indexOfTeam = 0;         // 逻辑上讲，此时只可能有一组，即index=0
            } else {      // 两组合并成 index=0
                for (Aeroplane p : chessBoard.getMyPlanes(generalGridIndex)) p.indexOfTeam = 0;
                chessBoard.teamIndexUsed[color][1] = false;
            }
        }
        for (Aeroplane p : chessBoard.getPartners(this.indexOfTeam))
            p.getPlaneView().setIconAsPlaneNum(chessBoard.getPartners(this.indexOfTeam).size());

    }

    /**
     * @throws AssertionError 档案描述了错误的坐标
     * @apiNote 用于读档或联机游戏时设置单个棋子的状态
     */
    public void setGeneralGridIndexAndMove(int generalGridIndex) throws AssertionError {
        assert generalGridIndex >= -1 && generalGridIndex <= 96
                : "先辈这次动作挺小的，但还是被我发现啦！\n棋子怎么可能飞到棋盘外呢？先 辈 大 笨 蛋";

        this.generalGridIndex = generalGridIndex;
        selfPathIndex = getSelfPathIndexFromGeneralIndex(generalGridIndex);

        if (generalGridIndex == -1) {
            setState(PlaneState.FINISH);
            planeView.finish();
            this.backToHangarWhenFinish();
            return;
        } else if (generalGridIndex == itsHangar) setState(PlaneState.IN_HANGAR);
        else {
            planeView.moveTo(generalGridIndex);
            this.setState(PlaneState.ON_BOARD);
        }
    }

    public int getNextGridWhenOnSelfColorGrid(int generalIndex) {
        int result = -1;
        for (int i = 0; i < BoardCoordinate.COLOR_GRID[color].length; i++) {
            if (generalIndex == BoardCoordinate.COLOR_GRID[color][i]
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
            if (i == index && i != BoardCoordinate.COLOR_GRID[color][BoardCoordinate.COLOR_GRID[color].length - 1])
                return true;
        }
        return false;
    }

    /**
     * @apiNote 在这里绑定叠子, 回机场
     */
    public void move() {
        for (Aeroplane p : chessBoard.getPartners(this.indexOfTeam))
            System.out.print(p.number + " (" + p.indexOfTeam + ")\n");
        if (indexOfTeam != -1) {
            if (generalGridIndex == BoardCoordinate.COLOR_DESTINATION[color])
                for (Aeroplane p : chessBoard.getPartners(this.indexOfTeam))
                    p.backToHangarWhenFinish();

            else for (Aeroplane p : chessBoard.getPartners(this.indexOfTeam)) {
                p.planeView.moveTo(generalGridIndex);
                p.generalGridIndex = generalGridIndex;
            }


            if (chessBoard.checkGameEnd())
                chessBoard.endGame();

        } else {
            if (generalGridIndex == BoardCoordinate.COLOR_DESTINATION[color])
                backToHangarWhenFinish();

            else
                planeView.moveTo(generalGridIndex);
        }
        if (chessBoard.checkGameEnd())
            chessBoard.endGame();

        // 结束回合
        chessBoard.getMovedPlanes().add(this.number);
        if (chessBoard.endTurn())
            chessBoard.continueEndTurn();
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

    public boolean notFinished() {
        return planeView.getState() != PlaneState.FINISH;
    }

    public void backToHangarDueToCrash() {
        this.indexOfTeam = -1;
        this.generalGridIndex = itsHangar;
        this.selfPathIndex = -1;
        planeView.setState(PlaneState.IN_HANGAR);
        Sound.CRACK.play(false);
        this.getPlaneView().setIconAsPlaneNum(1);
        planeView.moveTo(itsHangar);
    }

    protected void backToHangarWhenFinish() {
        this.selfPathIndex = -1;
        this.generalGridIndex = itsHangar;
        this.planeView.setIconAsPlaneNum(1);
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
}
