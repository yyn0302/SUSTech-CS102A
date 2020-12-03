package cs102a.aeroplane.presets;

public class PlaneState {
    public static final int IN_HANGAR = 0;          // 机场
    public static final int WAITING = 1;            // 满足条件进入起飞位置
    public static final int FINISH = 2;             // 到达终点

    public static final int NORM_MOVE = 3;          // 正常移动到目标
    public static final int STACK_AND_MOVE = 4;     // 叠子移动

    public static final int FLY_TO_SAME_COLOR = 5;  // 跳到下一个同色格子
    public static final int JET_TO_OPPOSITE = 6;    // 越到对面格子

    public static final int CRACKED = 7;            // 单机被击落
    public static final int STACK_AND_CRACKED = 8;  // 叠子被击落
    public static final int CRACK_OTHER = 9;        // 将目标方位其他棋子击落
    public static final int CRACK_OTHER_STACK = 10;        // 将目标方位其他棋子击落
}
