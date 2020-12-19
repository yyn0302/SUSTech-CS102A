package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.GameGUI;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @apiNote 点击按键时会自动移动叠子，虽然他们是 invisible & disabled
 * 棋子移动到终点会自动解散叠子状态
 */
public class PlaneView extends JButton {
    private final int color;
    private final int number;
    private final int itsHangar;

    // TODO: 2020/12/14 放好棋盘GUI后把棋盘图片左上角重定位(0,0)
    private final int xOffSet;
    private final int yOffSet;
    private final ChessBoard chessboard;    // 引用
    private final Aeroplane aeroplane;
    private int state;
    private int numOfStackedPlanes;
    MouseListener ableToMoveTipListener = new MouseListener() {
        @Override
        // 选择此飞机，禁止其他点击，飞到相应位置
        public void mousePressed(MouseEvent e) {
            for (Aeroplane p : chessboard.getPlanes()) {
                if (p.getNumber() != number) p.getPlaneView().setEnabled(false);
                aeroplane.tryMovingFront(chessboard.getNowMove());
                if (aeroplane.indexOfTeam != -1)  // 在队伍,移动队员
                    for (Aeroplane a : chessboard.getPlanes()) {
                        if (a.getColor() == color && a.indexOfTeam == aeroplane.indexOfTeam)
                            // 驱动所有队内成员一起移动
                            a.getPlaneView().moveTo(aeroplane.getGeneralGridIndex());
                    }
            }
        }

        // 做了个移入移出的可选择的提示
        @Override
        public void mouseEntered(MouseEvent e) {
            StringBuilder themeSelectedIconPath = new StringBuilder();
            themeSelectedIconPath.append(SystemSelect.getImagePath());

            themeSelectedIconPath.append("pl_fin_");
            switch (color) {
                case PlaneState.BLUE:
                    themeSelectedIconPath.append("bl.png");
                    break;
                case PlaneState.GREEN:
                    themeSelectedIconPath.append("gr.png");
                    break;
                case PlaneState.RED:
                    themeSelectedIconPath.append("re.png");
                    break;
                case PlaneState.YELLOW:
                    themeSelectedIconPath.append("ye.png");
                    break;
            }
            setIcon(new ImageIcon(themeSelectedIconPath.toString()));
        }

        // 做了个移入移出的可选择的提示
        @Override
        public void mouseExited(MouseEvent e) {
            setIconAsPlaneNum(numOfStackedPlanes);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    };

    public PlaneView(ChessBoard chessboard, int number, int color, int itsHangar, int xOffSet, int yOffSet, Aeroplane aeroplane) {
        this.color = color;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.chessboard = chessboard;
        this.number = number;
        this.itsHangar = itsHangar;
        this.aeroplane = aeroplane;
        this.setVisible(true);
        this.setSize(BoardCoordinate.GRID_SIZE, BoardCoordinate.GRID_SIZE);

        this.numOfStackedPlanes = 1;
        this.state = PlaneState.IN_HANGAR;
        this.setIconAsPlaneNum(1);  // 设置单个飞机图片
//        this.moveTo(itsHangar);     // 放上棋盘
        this.setVisible(true);
    }

    // 输入飞机叠子数量，将此飞机设置为对应的图片
    // 其他被叠飞机另外设置invisible
    public void setIconAsPlaneNum(int numOfStackedPlanes) {
        this.numOfStackedPlanes = numOfStackedPlanes;
        StringBuilder themeSelectedIconPath = new StringBuilder();

        themeSelectedIconPath.append(SystemSelect.getImagePath());
        themeSelectedIconPath.append(GameInfo.getTheme() == 1 ? "t1_p" : "t2_p");
        themeSelectedIconPath.append(numOfStackedPlanes).append("_");
        switch (color) {
            case PlaneState.BLUE:
                themeSelectedIconPath.append("bl.png");
                break;
            case PlaneState.GREEN:
                themeSelectedIconPath.append("gr.png");
                break;
            case PlaneState.RED:
                themeSelectedIconPath.append("re.png");
                break;
            case PlaneState.YELLOW:
                themeSelectedIconPath.append("ye.png");
                break;
        }
        setIcon(new ImageIcon(themeSelectedIconPath.toString()));
    }

    public void moveTo(int generalIndex) {
        this.setBounds(xOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][0] - BoardCoordinate.GRID_SIZE / 2,
                yOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][1] - BoardCoordinate.GRID_SIZE / 2,
                BoardCoordinate.GRID_SIZE, BoardCoordinate.GRID_SIZE);
    }

    public int getItsHangar() {
        return itsHangar;
    }

    public void readyToBeSelected() {
        for (ActionListener actionListener : this.getActionListeners()) {
            this.removeActionListener(actionListener);
        }
        this.addMouseListener(ableToMoveTipListener);
        this.setEnabled(true);
    }

    public void finish() {
        chessboard.teamIndexUsed[color][aeroplane.indexOfTeam] = false;
        aeroplane.indexOfTeam = -1;
        moveTo(itsHangar);
        StringBuilder themeSelectedIconPath = new StringBuilder();
        themeSelectedIconPath.append(SystemSelect.getImagePath());

        themeSelectedIconPath.append("pl_fin_");
        switch (color) {
            case PlaneState.BLUE:
                themeSelectedIconPath.append("bl.png");
                break;
            case PlaneState.GREEN:
                themeSelectedIconPath.append("gr.png");
                break;
            case PlaneState.RED:
                themeSelectedIconPath.append("re.png");
                break;
            case PlaneState.YELLOW:
                themeSelectedIconPath.append("ye.png");
                break;
        }
        setIcon(new ImageIcon(themeSelectedIconPath.toString()));
        this.state = PlaneState.FINISH;
        this.setEnabled(false);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
