package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.Main;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Player;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class Aeroplane {

    private int state;
    private int step;

    private int continueRoll;
    private int selfPathIndex;      // 自己该走完的57格
    private int color;

    private int number;                 // 飞机编号，0~15
    private int index;                  // 飞机所在位置0~97


    private ImageView planeView;

//     FIXME: 2020/12/7 debug
    private ChessBoard chessBoard;
    private float gridLength;           // 棋盘上一小格的长度
    private float xOffSet;              // 棋盘在屏幕X方向即右方向的偏移
    private float yOffSet;              // 棋盘在屏幕Y方向即下方向的偏移

    private ArrayList<Integer> path;
    private ArrayList<Integer> crack;   // 飞行中的碰撞类型


    public Aeroplane(ChessBoard chessBoard, int color, int number, int index,
                     float gridLength, float xOffSet, float yOffSet, ImageView planeView) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.index = index;
        this.gridLength = gridLength;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.planeView = planeView;
    }

    public int getColor() {
        return color;
    }

    public int getState() {
        return state;
    }

    public int getNumber() {
        return number;
    }

    public ImageView getPlaneView() {
        return planeView;
    }

    public int getIndex() {
        return index;
    }

//    @Override
// FIXME: 2020/12/9 核对源代码进行更改
    public void rollAndConfirmStep() {
        planeView.bringToFront();
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


//        if (rollResult[0] + rollResult[1] >= 10) {
//            if (continueRoll < 3) {
//                rollAndConfirmStep();
//                continueRoll++;
//            } else {
//                backToHangar();
//                continueRoll = 0;
//            }
//        }
    }

    protected void backToHangar() {
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
                if (Main.chessBoard.isOverlap(BoardCoordinate.COLOR_PATH[color][selfPathIndex + i])) {
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

    public int isJetGrid(int index) {
        int result = -1;
        if (index == BoardCoordinate.COLOR_JET[color][0])
            result = BoardCoordinate.COLOR_JET[color][2];
        return result;
    }

    public void move() {

    }

    public boolean isInAirport() {
        if (state != PlaneState.ON_BOARD) return true;
        else return false;
    }

    // 通过index获取在自己路径上的下标
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
    public float getXFromIndex(int index) {
        return xOffSet + gridLength * BoardCoordinate.COORDINATE[index][0];
    }

    public float getYFromIndex(int index) {
        return yOffSet + gridLength * BoardCoordinate.COORDINATE[index][1];
    }

    public void crackByPlane() {

    }

    public void resetGame() {

    }

    public void finishTask() {

    }

    public void restore() {
    }
}
