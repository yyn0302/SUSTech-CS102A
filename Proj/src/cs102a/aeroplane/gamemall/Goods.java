package cs102a.aeroplane.gamemall;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.model.ChessBoard;

public abstract class Goods {

    private static int price = 10;
    private static int[] storeCnt = {0, 0, 0, 0};


    public static void purchase(int purchaser) {
        if (price * Wallet.getDiscountAsPercent(purchaser) <= Wallet.getBalance(purchaser)) {
            Wallet.spendBalance(purchaser, price * Wallet.getDiscountAsPercent(purchaser));
            storeCnt[purchaser] += 1;
        }
    }


    public static int getPrice() {
        return price;
    }


    public static int[] getStoreCnt() {
        return storeCnt;
    }


    protected static void setPrice(int price) {
        if (GameInfo.isSuperUser()) Goods.price = price;
    }


    protected static void setStoreCnt(int storeCnt, int nowPlayer) {
        if (GameInfo.isSuperUser()) Goods.storeCnt[nowPlayer] = storeCnt;
    }


    public abstract void use(ChessBoard chessBoard);
}
