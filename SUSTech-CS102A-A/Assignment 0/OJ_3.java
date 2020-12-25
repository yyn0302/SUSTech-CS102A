import java.util.Scanner;

public class OJ_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        float prize = sc.nextFloat();
        int count = sc.nextInt();
        sc.close();
        System.out.printf("%d %d", 20200000 + id % 10000, prize * count % 1.0 < 0.5 ? (int)Math.floor(prize * count) : (int)Math.ceil(prize * count));
    }
}
