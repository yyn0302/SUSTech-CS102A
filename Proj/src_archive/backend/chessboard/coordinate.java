/*
 * @Description: 本文件用于储存棋盘上各点的信息
 *               - 相对与窗口左上角的坐标
 *               - 对应格子的颜色（提示跳跃条件）
 *               - 在此格子上机头方向（提示图片旋转角度）
 * @Version: 0.1
 * @LastEditor: Chris
 * @LastEditTime: 2020-11-25 01:58
 */

package backend.chessboard;

import java.awt.*;

/**
 * 顺时针开始计数，上方中心蓝色入口计为1，外围一圈总计52号
 * <p>
 * 对于外圈格子
 * - 标“进入中心转向”的四个 -> 前缀 E（Enter）
 * - 无特殊标示 -> 前缀 C（Clockwise）
 * - 直线飞跃到对面 -> 前缀 F（Fly）
 * <p>
 * 对于向中心的直道
 * - 前缀 BL/YE/RE/GR
 * - 从外向内计数，1至6（例  BL6 即为蓝色棋子的终点）
 */

public enum coordinate {
    // FIXME: 2020/11/25 @zzh：根据实际情况设置这里的坐标像素
    E1(1, 1, validColor.BLUE, direction.RIGHT),
    C2(1, 1, validColor.BLUE, direction.RIGHT);

    int xPixel, yPixel;
    validColor c;
    direction d;

    coordinate(int x, int y, validColor c, direction d) {
        this.xPixel = x;
        this.yPixel = y;
        this.c = c;
        this.d = d;
    }
}

enum validColor {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    LIGHT_RED(Color.RED.brighter()),
    LIGHT_GREEN(Color.GREEN.brighter()),
    LIGHT_BLUE(Color.BLUE.brighter()),
    LIGHT_YELLOW(Color.YELLOW.brighter());

    Color c;

    validColor(Color c) {
        this.c = c;
    }
}

enum direction {UP, DOWN, LEFT, RIGHT}