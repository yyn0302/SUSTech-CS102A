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

    //    private int continueRoll;
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
//    private ArrayList<Integer> crack;   // 飞行中的碰撞类型


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
        chessBoard.adjustPosition(path.get(path.size() - 1), number);
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
        // 不过终点，先移动再判断特殊规则
        if (selfPathIndex + steps < BoardCoordinate.PATH_LENGTH) {

            // 判断这一步会不会碰上其他方
            if (chessBoard.hasOtherPlane(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps])) {

                // 不是自己格子，battle
                if (!isSameColorGrid(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps])) {
                    for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps])) {
                        if (isWinnerInBattling()) {
                            p.backToHangar();
                        } else {
                            this.backToHangar();
                            break;
                        }
                        if (this.state != PlaneState.IN_HANGAR)
                            path.add(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps]);
                    }
                } else {
                    // 是自己格子，无条件赶走对方
                    for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps]))
                        p.backToHangar();

                    // 且跳到下一个同色格子，赶走这个格子的对方
                    for (Aeroplane p : chessBoard.getOppoPlanes(getNextGridWhenOnSelfColorGrid(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps])))
                        p.backToHangar();
                    chessBoard.adjustPosition(getNextGridWhenOnSelfColorGrid(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps]), number);
                    move();

                    if (isJetGrid(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps]) != -1) {
                        chessBoard.adjustPosition(BoardCoordinate.COLOR_PATH[color][selfPathIndex + steps], number);
                        for (Aeroplane p : chessBoard.getOppoPlanes(BoardCoordinate.COLOR_JET[color][1]))
                            p.backToHangar();
                        move();
                    }
                }

            }
        } else {

        }
    }


    private boolean isSameColorGrid(int index) {
        for (int i : BoardCoordinate.COLOR_GRID[color]) {
            if (i == BoardCoordinate.COLOR_PATH[color][index]) return true;
        }
        return false;
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

    public void move() {

    }

    // 通过index获取在自己路径上的下标
//    public int getStepFromIndex(int index) {
//        int step = -1;
//        for (int i = 0; i < BoardCoordinate.COLOR_PATH[color].length; i++) {
//            if (index == BoardCoordinate.COLOR_PATH[color][i]) {
//                step = i;
//                break;
//            }
//        }
//        return step;
//    }

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

    public void finishTask() {
        StringBuilder iconPathWhenFinish = new StringBuilder();
        iconPathWhenFinish.append(SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath());
        switch (color) {
            case Hangar.BLUE:
                iconPathWhenFinish.append("blue.png");
                break;
            case Hangar.GREEN:
                iconPathWhenFinish.append("green.png");
                break;
            case Hangar.RED:
                iconPathWhenFinish.append("red.png");
                break;
            case Hangar.YELLOW:
                iconPathWhenFinish.append("yellow.png");
                break;
        }
        this.planeView.setIcon(new ImageIcon(iconPathWhenFinish.toString()));
        backToHangar();
        state=PlaneState.FINISH;
    }

    public void restore() {
        StringBuilder iconPath=new StringBuilder();
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
        backToHangar();
        path.clear();
    }
}
