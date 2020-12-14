package cs102a.aeroplane.util;

import java.security.SecureRandom;

public class Dice {
    public static int roll() {
        SecureRandom sr = new SecureRandom();
        return 1 + sr.nextInt(6);
    }
}
