package Ex10;

import java.util.Scanner;

public class Ex10_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Your budget: ");
        int budget = sc.nextInt();
        int flag = 0;
        for (phoneModel phone : phoneModel.values()) {
            if (phone.getPrice() < budget) {
                System.out.printf("%-20sPrice: %-10d\n", phone, phone.getPrice());
                flag++;
            }
        }
        if (flag == 0) System.out.println("You do not have sufficient money");
    }
}
