import java.util.Scanner;

public class Ex4_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] bucket = new int[101];
        int temp = sc.nextInt();
        while (temp != 0) {
            ++bucket[temp];
            temp = sc.nextInt();
        }
        for (int i = 1; i <= 100; i++) {
            if (bucket[i] != 0) {
                System.out.printf("%d occurs %d times.\n", i, bucket[i]);
            }
        }
    }
}
