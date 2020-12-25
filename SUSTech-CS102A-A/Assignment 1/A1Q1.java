import java.util.Scanner;

public class A1Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int courseNumber = sc.nextInt(), credit = 0;

        for (int i = 0; i < courseNumber; i++) {
            credit += sc.nextInt();
        }
        sc.close();
        System.out.print(credit);
    }
}
