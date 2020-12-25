import java.util.Arrays;
import java.util.Scanner;

public class A2Q3 {
    public static long[] getMaxMin(long[] freq) {
        Arrays.sort(freq);
        long[] MaxMin = new long[2];
        MaxMin[0] = freq[freq.length - 1];
        MaxMin[1] = freq[0];
        return MaxMin;
    }

    static long pow(int a, int b) {
        long ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a;
            ans %= 998244353;
        }
        return ans;

    }

    public static long getGCD(long a, long b) {
        long k;
        do {
            k = a % b;
            a = b;
            b = k;
        } while (k != 0);
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        long[] freq = new long[n];
        String s;
        for (int i = 0; i < n; i++) {
            long ans = 0;
            if (sc.hasNextLine()) {
                s = sc.nextLine();
                for (int j = 0; j < s.length(); j++) {
                    ans += (((s.charAt(j) % 998244353) * (pow(s.length(), j)) % 998244353));
                }
                freq[i] = ans % 998244353;
            } else freq[i] = -1;
        }
        long[] mm = getMaxMin(freq);
        long gcd = getGCD(mm[0], mm[1]);
        System.out.printf("%d %d", (int) (mm[0] / gcd), (int) (mm[1] / gcd));
    }
}
