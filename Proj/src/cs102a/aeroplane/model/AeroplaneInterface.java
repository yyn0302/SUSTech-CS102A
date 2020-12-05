package cs102a.aeroplane.model;

public interface AeroplaneInterface {
    /**
     * @param = null
     * @function 调用util中掷骰子方法
     * 进行可行移动判断，存入本地ans[]
     * 起飞？
     * 四则运算中满足条件的结果
     * 注意一定情况下允许摇第二次
     */
    public void rollAndConfirmStep();

    /**
     * 根据摇出来的结果问用户要走多少步
     * 或者作弊模式直接输入要走的步数
     */

    /**
     * @param = diceAns
     * @function 判断移动中发生的情况
     * 是否叠子
     * 撞子
     * 进入终点？！
     * 错过终点，返回
     */
    public void setPath(int steps);

    /**
     * 判断参数index是不是同色的格子(除去最后一个)
     * 若是则返回下一个同色格子的index，否则返回-1
     *
     * @param index 当前格子index
     * @return 下个同色格子index
     * @link called by setPath()
     */
    public int isSameColorGrid(int index);

    /**
     * 判断参数index是不是大跳的格子
     *
     * @param index 当前格子index
     * @return 若是则返回跳到格子的index，否则返回-1
     */
    public int isJetGrid(int index);

    /**
     * 在setPath后真实移动棋子
     * 更新棋子view坐标
     */
    public void move();

    // 此飞机是否在机场
    public boolean isInAirport();

    // 通过index获取在自己路径上的下标
    public int getStepFromIndex(int index);

    public float getXFromIndex(int index);
    // 通过index来获取在屏幕上的x坐标
//    public float getXFromIndex(int index){
//        return xOffSet + gridLength * Commdef.POSITIONS[index][0];
//    }

    public float getYFromIndex(int index);

    public void getReadyToFly();
        // 做一个不断重复的缩放动画，告诉玩家可以移动的棋子

    // 发生撞子时调用（对自己的参数进行调整）
    public void crackByPlane();

    // 重置，在游戏重新开始时调用
    public void resetGame();

    // 飞机到达终点完成任务时调用
    public void finishTask();


    }
