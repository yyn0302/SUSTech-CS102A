package cs102a.aeroplane.gamemall;

public class Wallet {

    private static float[] discountAsPercent = {1.00f, 1.00f, 1.00f, 1.00f};
    private static float[] balance = {0, 0, 0, 0};


    public static float getDiscountAsPercent(int user) {
        return discountAsPercent[user];
    }


    public static void setDiscountAsPercent(int user, float discountAsPercent) {
        Wallet.discountAsPercent[user] = discountAsPercent;
    }


    public static float getBalance(int user) {
        return balance[user];
    }


    public static void addBalance(int user, float addition) throws AssertionError {
        assert addition > 0 : "氪金，充的钱当然要是正数啊";
        Wallet.balance[user] += addition;
    }


    public static void spendBalance(int user, float moneyUsed) {
        Wallet.balance[user] -= moneyUsed;
    }
}
