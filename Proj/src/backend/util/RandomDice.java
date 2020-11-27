package backend.util;

import java.util.Random;

public final class RandomDice {
    private static final Random rand = new Random();

    public static int nextInt(int begin, int end) {
        return begin + rand.nextInt(end - begin + 1);
    }
}
