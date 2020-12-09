package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.Hangar;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.savegame.SystemSelect;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Player;

import javax.swing.*;
import java.util.ArrayList;

public class Aeroplane {

    private int state;
    private int step;

    private int continueRoll;
    private int selfPathIndex;          // 自己该走完的57格
    private int color;

    private int number;                 // 飞机编号，0~15
    private int index;                  // 飞机所在位置0~97


    private JButton planeView;

    private ChessBoard chessBoard;
    private float gridLength;           // 棋盘上一小格的长度
    private float xOffSet;              // 棋盘在屏幕X方向即右方向的偏移
    private float yOffSet;              // 棋盘在屏幕Y方向即下方向的偏移

    private ArrayList<Integer> path;
    private ArrayList<Integer> crack;   // 飞行中的碰撞类型


    public Aeroplane(ChessBoard chessBoard, int color, int number, int index, float gridLength, float xOffSet, float yOffSet) {
        this.chessBoard = chessBoard;
        this.color = color;
        this.number = number;
        this.index = index;
        this.gridLength = gridLength;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;

        // 飞机的view实际上是JButton，每次只有己方未完成的飞机可以点击
        // TODO: 2020/12/9 add code 可以点击
        StringBuilder iconPath = new StringBuilder();

        iconPath.append(SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath());
        iconPath.append(GameInfo.getTheme() == 1 ? "plane_theme1_" : "plane_");
        switch (color) {
            case Hangar.BLUE:
                iconPath.append("blue.png");
                break;
            case Hangar.GREEN:
                iconPath.append("green.png");
                break;
            case Hangar.RED:
                iconPath.append("red.png");
                break;
            case Hangar.YELLOW:
                iconPath.append("yellow.png");
                break;
        }
        this.planeView.setIcon(new ImageIcon(iconPath.toString()));
    }

    public int getColor() {
        return color;
    }

    public int getContinueRoll() {
        return continueRoll;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public ArrayList<Integer> getCrack() {
        return crack;
    }

    public int getState() {
        return state;
    }

    public int getNumber() {
        return number;
    }

    public JButton getPlaneView() {
        return planeView;
    }

    public int getIndex() {
        return index;
    }

    // FIXME: 2020/12/9 核对源代码进行更改
    //  @frontend
    public void receiveDiceNumber(int[] rollResult) {
        // planeView.bringToFront();
        // FIXME: 2020/12/7 把飞机view提到布局最高层，从而实现飞过其他棋子时覆盖它们

        if (isInAirport()) {
            if (rollResult[0] == 6 || rollResult[1] == 6) {
                state = PlaneState.WAITING;
                setPath(1);
            } else return;
        } else if (state == PlaneState.WAITING || state == PlaneState.ON_BOARD) {
            if (state == PlaneState.WAITING) state = PlaneState.ON_BOARD;
            setPath(Player.askPlayerStep(rollResult));
        }

        // 离开当前位置时把当前位置的其他飞机重新调整位置
        chessBoard.adjustPosition(index, number);
        move();
    }

    public boolean isInAirport() {
        boolean b = state == PlaneState.IN_HANGAR;
        return b;
    }

    protected void backToHangar() {
        this.state = PlaneState.IN_HANGAR;
        this.step = 0;
    }

    public void setPath(int steps) {
        for (int i = 1; i <= steps; i++) {
            // 判断往前走i步会不会越过终点
            if (selfPathIndex + i < BoardCoordinate.PATH_LENGTH) {

                // 不过终点，先移动再判断特殊规则
                path.add(BoardCoordinate.COLOR_PATH[color][selfPathIndex + i]);

                // 判断这一步会不会碰上其他方的迭子
                if (chessBoard.isOverlap(BoardCoordinate.COLOR_PATH[color][selfPathIndex + i])) {

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
