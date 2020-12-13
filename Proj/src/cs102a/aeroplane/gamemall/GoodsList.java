package cs102a.aeroplane.gamemall;

import cs102a.aeroplane.model.Aeroplane;
import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.presets.PlaneState;

public class GoodsList {

    // 对应炸弹
    public static final Goods bomb = new Goods(99, "炸弹", "单次使用，让场上其他方飞机回机场") {
        @Override
        public void use(ChessBoard chessBoard) {
            for (Aeroplane p : chessBoard.getPlanes()) {
                if (p.getColor() != chessBoard.getNowPlayer()) {
                    if (p.getState() == PlaneState.WAITING || p.getState() == PlaneState.ON_BOARD) {
                        p.backToHangarDueToCrash();
                    }
                }
            }
            setStoreCnt(getStoreCnt()[chessBoard.getNowPlayer()] - 1, chessBoard.getNowPlayer());
        }
    };

    // 对应波音
    public static final Goods takeOffAnyway = new Goods(49, "芜湖起飞", "自己方所有当前在机场的飞机都获得一次无条件起飞的资格") {
        @Override
        public void use(ChessBoard chessBoard) {
            for (Aeroplane p : chessBoard.getPlanes()) {
                if (p.getColor() == chessBoard.getNowPlayer()) {
                    if (p.getState() == PlaneState.IN_HANGAR) {
                        p.setState(PlaneState.WAITING);
                    }
                }
            }
            setStoreCnt(getStoreCnt()[chessBoard.getNowPlayer()] - 1, chessBoard.getNowPlayer());
        }
    };

    // 对应VIP
    public static final Goods makeMeWin = new Goods(999, "VIP", "无论自己多菜，游戏结束后排行榜上一定是第一") {
        @Override
        public void use(ChessBoard chessBoard) {
            chessBoard.setWinner1Index(chessBoard.getNowPlayer());
            setStoreCnt(getStoreCnt()[chessBoard.getNowPlayer()] - 1, chessBoard.getNowPlayer());
        }
    };
}
