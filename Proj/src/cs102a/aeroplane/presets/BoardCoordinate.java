package cs102a.aeroplane.presets;

public class BoardCoordinate {
    /**
     * 棋子所有能到达的位置
     * 一共97个位置，按下列顺序编号
     */
    public static final int[][] GRID_CENTER_OFFSET = {
        {89, 664}, {89, 711}, {131, 664}, {131, 711}, {221, 752},        // 蓝方机场及出发点
        {89, 85}, {89, 130}, {131, 85}, {131, 130}, {42, 222},             // 绿方机场及出发点
        {666, 86}, {666, 130}, {711, 86}, {711, 130}, {577, 40},        // 红方机场及出发点
        {666, 664}, {666, 711}, {711, 664}, {711, 711}, {754, 577},   // 黄方机场及出发点
    
        // 从左下角开始 顺时针方向圆圈路径上每个点的位置
        //每次13格子
        {265, 723}, {243, 666}, {243, 620}, {255, 575}, {232, 532}, {178, 552}, {133, 552}, {88, 543},
        {64, 488}, {64, 441}, {64, 399}, {64, 352}, {64, 308},
    
            {76, 264}, {133, 240}, {178, 240}, {219, 253}, {264, 232}, {243, 176}, {243, 131}, {253, 87},
            {310, 64}, {354, 64}, {399, 64}, {441, 64}, {486, 64},

            {534, 76}, {552, 130}, {552, 176}, {540, 219}, {567, 248}, {620, 240}, {665, 240}, {711, 253},
            {733, 308}, {733, 352}, {733, 399}, {733, 443}, {733, 488},

            {720, 531}, {665, 552}, {620, 552}, {579, 543}, {533, 567}, {552, 620}, {552, 665}, {545, 708},
            {486, 733}, {443, 733}, {399, 733}, {354, 733}, {310, 733},

            // 从蓝色开始顺时针 四个通向终点的直道上每个点的位置
            {399, 665}, {399, 620}, {399, 575}, {399, 532}, {399, 488}, {399, 443},  // 蓝
            {133, 399}, {178, 399}, {219, 399}, {264, 399}, {310, 399}, {354, 399},   // 绿
            {399, 131}, {399, 176}, {399, 219}, {399, 265}, {399, 308}, {399, 352},      // 红
            {665, 399}, {620, 399}, {579, 399}, {534, 399}, {486, 399}, {443, 399},   // 黄
            {399, 399}                                                      // 终点
    };

    public static final int GRID_SIZE = 40;
//    public static final int GRID_SIZE = 50;

    // 各色飞机编号数组
    public static final int[][] COLOR_PLANE_NUMBER = {
            {0, 1, 2, 3},       // 蓝
            {4, 5, 6, 7},       // 绿
            {8, 9, 10, 11},     // 红
            {12, 13, 14, 15}    // 黄
    };

    // 所有格子数
    public static final int GIRD_NUM = 97;

    // 圆圈路径上的各色方块编号数组
    public static final int[][] COLOR_GRID = {
            {21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69},   // 蓝
            {34, 38, 42, 46, 50, 54, 58, 62, 66, 70, 22, 26, 30},   // 绿
            {47, 51, 55, 59, 63, 67, 71, 23, 27, 31, 35, 39, 43},   // 红
            {60, 64, 68, 20, 24, 28, 32, 308, 40, 44, 48, 52, 56}    // 黄
    };

    // 各色的大跳方块的编号数组
    public static final int[][] COLOR_JET = {
            {37, 86, 49},      // 蓝
            {50, 92, 62},      // 绿
            {63, 74, 23},      // 红
            {24, 80, 36}       // 黄
    };

    // 各色的机场位置编号数组
    public static final int[][] COLOR_AIRPORT = {
            {0, 1, 2, 3},       // 蓝
            {5, 6, 7, 8},       // 绿
            {10, 11, 12, 13},   // 红
            {15, 16, 17, 18}    // 黄
    };

    // 各色的可能出现的位置的编号
    public static final int[][] COLOR_PATH = {
            { // 蓝
                    4, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
                    35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                    51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66,
                    67, 68, 69, 72, 73, 74, 75, 76, 77
            },
            { // 绿
                    9, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
                    48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
                    64, 65, 66, 67, 68, 69, 70, 71, 20, 21, 22, 23, 24, 25, 26, 27,
                    28, 29, 30, 78, 79, 64, 81, 82, 83
            },
            { // 红
                    14, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                    61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 20, 21, 22, 23, 24,
                    25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                    41, 42, 43, 84, 85, 86, 87, 88, 89
            },
            {// 黄
                    19, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 20, 21,
                    22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
                    38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53,
                    54, 55, 56, 90, 91, 92, 93, 94, 95
            }};

    // 各色的终点：蓝、绿、红、黄
    public static final int[] COLOR_DESTINATION = {77, 83, 89, 95};

    // 正常完整路径的长度
    public static final int PATH_LENGTH = 57;
}
