/*
 * @Description: 本文件用于储存棋盘上各点的信息
 *               - 相对与窗口左上角的坐标/最好研究一下窗口可缩放，如何调整这里的坐标以及背景图片等大小的压缩
 *               - 对应格子的颜色（提示跳跃条件）
 *               - 在此格子上机头方向（提示图片旋转角度）
 * @Version: 0.1
 * @LastEditor: Chris
 * @LastEditTime: 2020-11-25 01:58
 */

package backend.chessboard;


/**
 * @description: 顺时针开始计数，上方中心蓝色入口计为1，外围一圈总计52号
 * 对于外圈格子  - 标“进入中心转向”的四个 -> 前缀 E（Enter）
 * - 无特殊标示 -> 前缀 C（Clockwise）
 * - 直线飞跃到对面 -> 前缀 F（Fly）
 * 对于向中心的直道   - 前缀 BL/YE/RE/GR
 * - 从外向内计数，1至6（例  BL6 即为蓝色棋子的终点）
 * @param {*}
 * @return {*}
 */

//TODO @zzh：根据实际情况设置这里的坐标像素
public enum coordinate {
    C1(1, 1, boardColor.YELLOW, direction.UP),
    C2(1, 1, boardColor.YELLOW, direction.UP),
    C3(1, 1, boardColor.YELLOW, direction.UP),
    C4(1, 1, boardColor.YELLOW, direction.UP),
    C5(1, 1, boardColor.YELLOW, direction.UP),
    C6(1, 1, boardColor.YELLOW, direction.UP),
    C7(1, 1, boardColor.YELLOW, direction.UP),
    C8(1, 1, boardColor.YELLOW, direction.UP),
    C9(1, 1, boardColor.YELLOW, direction.UP),
    C10(1, 1, boardColor.YELLOW, direction.UP),
    C11(1, 1, boardColor.YELLOW, direction.UP),
    C12(1, 1, boardColor.YELLOW, direction.UP),
    C13(1, 1, boardColor.YELLOW, direction.UP),
    C14(1, 1, boardColor.YELLOW, direction.UP),
    C15(1, 1, boardColor.YELLOW, direction.UP),
    C16(1, 1, boardColor.YELLOW, direction.UP),
    C17(1, 1, boardColor.YELLOW, direction.UP),
    C18(1, 1, boardColor.YELLOW, direction.UP),
    C19(1, 1, boardColor.YELLOW, direction.UP),
    C20(1, 1, boardColor.YELLOW, direction.UP),
    C21(1, 1, boardColor.YELLOW, direction.UP),
    C22(1, 1, boardColor.YELLOW, direction.UP),
    C23(1, 1, boardColor.YELLOW, direction.UP),
    C24(1, 1, boardColor.YELLOW, direction.UP),
    C25(1, 1, boardColor.YELLOW, direction.UP),
    C26(1, 1, boardColor.YELLOW, direction.UP),
    C27(1, 1, boardColor.YELLOW, direction.UP),
    C28(1, 1, boardColor.YELLOW, direction.UP),
    C29(1, 1, boardColor.YELLOW, direction.UP),
    C30(1, 1, boardColor.YELLOW, direction.UP);

    int xPixel, yPixel;
    boardColor c;
    direction d;

    coordinate(int x, int y, boardColor c, direction d) {
        this.xPixel = x;
        this.yPixel = y;
        this.c = c;
        this.d = d;
    }
}

enum boardColor {YELLOW, BLUE, RED, GREEN}

enum direction {UP, DOWN, LEFT, RIGHT}