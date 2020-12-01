package aeroplanechess.util;

import java.security.SecureRandom;

public final class RandomUtil {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static int nextDice() {
        return 1 + RANDOM.nextInt(6);
    }
}
