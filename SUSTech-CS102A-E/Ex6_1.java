import java.util.*;
import java.lang.*;
import java.security.SecureRandom;

public class Ex6_1 {
    public static void main(String[] args) {
        Random Random = new Random();
        // int a = 3;
        // System.out.println("Before: " + a);
        // a = triple(a);
        // System.out.println("After: " + a);
        // System.out.println(Arrays.toString(test(3,4,3,2)));
        // int x = 3;
        // double y = x * 2.0;
        // System.out.println(x);
        int rand = Random.nextInt(10);
        System.out.println(rand);
        SecureRandom scR = new SecureRandom();
    }

    // public static int triple(int x) {
    // x *= 3;
    // return x;
    // }

    public static int[] test(int... grades) {
        return grades;
    }
}
