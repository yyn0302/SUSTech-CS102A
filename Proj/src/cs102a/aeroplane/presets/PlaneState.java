package cs102a.aeroplane.presets;

public enum PlaneState {
    IN_HANGAR(0),          // 机场
    WAITING(1),            // 满足条件进入起飞位置
    FINISH(2),             // 到达终点

    NORM_MOVE(3),          // 正常移动到目标
    STACK_AND_MOVE(4),     // 叠子移动

    FLY_TO_SAME_COLOR(5),  // 跳到下一个同色格子
    JET_TO_OPPOSITE(6),    // 越到对面格子

    CRACKED(7),            // 单机被击落
    STACK_AND_CRACKED(8),  // 叠子被击落
    CRACK_OTHER(9);        // 将目标方位其他棋子击落

    private int state;

    PlaneState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
