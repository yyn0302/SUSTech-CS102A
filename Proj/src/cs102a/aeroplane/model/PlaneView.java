package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaneView extends JButton {

    private final int color;
    private final int itsHangar;
    private final int number;
    private int state;
    private int numOfStackedPlanes;

    // TODO: 2020/12/14 放好棋盘GUI后把棋盘图片左上角重定位(0,0)
    private final int xOffSet;
    private final int yOffSet;
    private final ChessBoard chessboard;    // 引用

    public PlaneView(ChessBoard chessboard, int number, int color, int itsHangar, int xOffSet, int yOffSet) {
        this.color = color;
        this.itsHangar = itsHangar;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.chessboard = chessboard;
        this.number = number;

        this.numOfStackedPlanes = 1;
        this.state = PlaneState.IN_HANGAR;
        this.setIconAsPlaneNum(1);  // 设置单个飞机图片
        this.moveTo(itsHangar);     // 放上棋盘
    }

    // 输入飞机叠子数量，将此飞机设置为对应的图片
    // 其他被叠飞机另外设置invisible
    public void setIconAsPlaneNum(int numOfStackedPlanes) {
        this.numOfStackedPlanes = numOfStackedPlanes;
        StringBuilder themeSelectedIconPath = new StringBuilder();
        themeSelectedIconPath.append(SystemSelect.getImagePath());
        themeSelectedIconPath.append(GameInfo.getTheme() == 1 ? "t1_p" : "t2_p");
        themeSelectedIconPath.append(numOfStackedPlanes + "_");
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
        this.setIcon(new ImageIcon(themeSelectedIconPath.toString()));
    }

    public void moveTo(int generalIndex) {
        this.setBounds(xOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][0] - BoardCoordinate.GRID_SIZE / 2,
                yOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][1] - BoardCoordinate.GRID_SIZE / 2,
                BoardCoordinate.GRID_SIZE, BoardCoordinate.GRID_SIZE);
    }

    public void makeItReadyToBeSelected() {
        this.addMouseListener(new MouseListener() {
            @Override
            // 选择此飞机，禁止其他点击，准备飞到相应位置
            public void mousePressed(MouseEvent e) {
                for (Aeroplane p : chessboard.getPlanes()) {
                    if (p.getNumber() != number) p.getPlaneView().setEnabled(false);
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
        });
    }
}
