package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.Main;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Player;

import java.util.ArrayList;

public class Aeroplane implements AeroplaneInterface {

    private int state;
    private int step;
    private int continueRoll;
    private int selfPathIndex;      // 自己该走完的57格
    private int color;

    // FIXME: 2020/12/7 debug
    private ChessBoard chessBoard;
    private float xOffSet;              // 棋盘在屏幕X方向即右方向的偏移
    private float yOffSet;              // 棋盘在屏幕Y方向即下方向的偏移

    private ArrayList<Integer> path;
    private ArrayList<Integer> crack;   // 飞行中的碰撞类型


    public Aeroplane(xx) {
        chessBoard = Main.chessBoard;
    }

    @Override
    public void rollAndConfirmStep() {
        PlaneView.bringToFront();
        // FIXME: 2020/12/7 把飞机view提到布局最高层，从而实现飞过其他棋子时覆盖它们

        int[] rollResult = {Dice.roll(), Dice.roll()};
        if (state == PlaneState.IN_HANGAR) {
            // 起飞到候补区，再次投骰子
            if (rollResult[0] == 6 || rollResult[1] == 6) {
                state = PlaneState.WAITING;
                step = 1;
                rollAndConfirmStep();
            } else return;
        } else if (state == PlaneState.WAITING || state == PlaneState.ON_BOARD) {
            setPath(Player.askPlayerStep(rollResult));
        }

        // FIXME: 2020/12/7 具体实现
        // 离开当前位置时把当前位置的其他飞机重新调整位置
        board.adjustPosition(index, number);
        // 根据path来走棋
        move();


        if (rollResult[0] + rollResult[1] >= 10) {
            if (continueRoll < 3) {
                rollAndConfirmStep();
                continueRoll++;
            } else {
                backToHangar();
                continueRoll = 0;
            }
        }
    }

    private void backToHangar() {
        this.state = PlaneState.IN_HANGAR;
        this.step = 0;
    }

    @Override
    public void setPath(int steps) {
        for (int i = 1; i <= steps; i++) {
            // 判断往前走i步会不会越过终点
            if (selfPathIndex + i < BoardCoordinate.PATH_LENGTH) {
                // 不过终点，先移动再判断特殊规则
                path.add(BoardCoordinate.COLOR_PATH[color][selfPathIndex + i]);
                // 判断这一步会不会碰上其他方的迭子
                if (chessboard.isOverlap(BoardCoordinate.COLOR_PATH[color][selfPathIndex + i])) {
                    // 如果碰上其他方的迭子，判断是不是刚好会停在迭子的位置
                    if (i == steps) {
                        // 如果会刚好停在其他方迭子的位置，增加一个同归于尽的碰撞，再结束path的设置
                        if (isWinnerInBattling()) {
                            crack.add(PlaneState.CRACK_OTHER_STACK);
                            break;
                        }
                    }

                }
            }
        }
    }

    private boolean isWinnerInBattling() {
        if (GameInfo.isIsCheatMode()) {
            // TODO: 2020/12/7 若是作弊模式，直接两个按钮选择在这局中想赢或者输，回传按钮值
            return false;
        } else {
            int self = Dice.roll();
            int opposing = Dice.roll();
            // TODO: 2020/12/7 前端展示弹窗，两者摇骰子
            return self > opposing;
        }
    }

    @Override
    public int isSameColorGrid(int index) {
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

    @Override
    public int isJetGrid(int index) {
        int result = -1;
        if (index == BoardCoordinate.COLOR_JET[color][0])
            result = BoardCoordinate.COLOR_JET[color][2];
        return result;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isInAirport() {
        if (state != PlaneState.ON_BOARD) return true;
        else return false;
    }

    // 通过index获取在自己路径上的下标
    @Override
    public int getStepFromIndex(int index) {
        int step = -1;
        for (int i = 0; i < BoardCoordinate.COLOR_PATH[color].length; i++) {
            if (index == BoardCoordinate.COLOR_PATH[color][i]) {
                step = i;
                break;
            }
        }
        return step;
    }

    // 通过index来获取在屏幕上的x坐标
    @Override
    public float getXFromIndex(int index) {
        return xOffSet + gridLength * Commdef.POSITIONS[index][0];
    }

    @Override
    public float getYFromIndex(int index) {
        return 0;
    }

    @Override
    public void getReadyToFly() {

    }

    @Override
    public void crackByPlane() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void finishTask() {

    }
}
