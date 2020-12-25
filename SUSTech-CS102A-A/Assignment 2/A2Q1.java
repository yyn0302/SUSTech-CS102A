import java.util.Scanner;

public class A2Q1 {
    static final double deltaX = 0.0001;
    float[] polynomial;

    public double f(double x) {
        double ans = 0d;
        for (int i = 0; i < polynomial.length - 1; i++) {
            ans += polynomial[i] * Math.pow(x, polynomial.length - i - 1);
        }
        return ans + polynomial[polynomial.length - 1];
    }

    public float integral(int a, int b) {
        double ans = 0d;
        int item = 0;
        while (a + item * deltaX < b) {
            ans += f(a + item * deltaX);
            item++;
        }
        return (float) (ans * deltaX);
    }

    public static void main(String[] args) {
        A2Q1 aq =new A2Q1();
        Scanner sc = new Scanner(System.in);
        int lowerBound = sc.nextInt(), upperBound = sc.nextInt();
        int n = sc.nextInt();
        aq.polynomial = new float[n + 1];
        for (int i = 0; i < n + 1; i++) {
            aq.polynomial[i] = sc.nextFloat();
        }
        sc.close();
        System.out.printf("%.1f\n", aq.integral(lowerBound, upperBound));
    }
}
