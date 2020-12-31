import java.util.Scanner;

public class OJ_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp += (i + 1) * (isbn.charAt(i) - '0');
        }
        temp %= 11;
        int a = isbn.charAt(9) == 'X' ? 10 : isbn.charAt(9) - '0';
        System.out.print(temp == a ? "Yes" : "No");
    }
}
