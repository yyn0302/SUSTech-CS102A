import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tired = 0;
        for (int i = 0; i < 5; i++) {
            tired += sc.nextInt() >= 4 ? 1 : 0;
        }
        sc.close();
        System.out.print(tired);
    }
}
