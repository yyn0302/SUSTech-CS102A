import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float money;
        int bookNeedToBuy;
        
        money = sc.nextFloat();
        bookNeedToBuy = sc.nextInt();
        float totalMoney = 0f;

        for (int i = 0; i < bookNeedToBuy; i++) {
            totalMoney += sc.nextFloat();
        }
        sc.close();

        System.out.print(totalMoney <= money ? "Yes" : "No");
    }
}
