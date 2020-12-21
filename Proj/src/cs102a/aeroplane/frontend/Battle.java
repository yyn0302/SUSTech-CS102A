package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.util.Dice;


/**
 * 后端只要调用 Battle.isWinner()
 */
public class Battle {
    public static boolean isWinner() {
        int[] result = Dice.rollDices();
        int self = result[0];
        int oppo = result[1];
        new TimeDialog().showDialog(Settings.window, String.format("你摇出 %d  %s  对方摇出 %d", self, (self < oppo ? " < " : " > "), oppo), 1);

        return self > oppo;
    }


}
