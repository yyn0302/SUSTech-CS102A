package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

public class PlaneView extends JButton {

    private final int color;
    // TODO: 2020/12/14 放好棋盘GUI后把棋盘图片左上角重定位(0,0)
    private final float xOffSet, yOffSet;
    private final JPanel chessboard;

    public PlaneView(){
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



    }
    public static void moveTo(int generalIndex,) {
        this.set
    }
}
