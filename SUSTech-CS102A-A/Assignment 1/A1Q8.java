import java.util.Scanner;
import java.util.Arrays;

public class A1Q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int luckyNumber = sc.nextInt();
        String SID = sc.next();
        sc.close();

        int[] decomposedSID = new int[SID.length()];
        int numOfLuckyNumber = 0;
        for (int i = 0; i < SID.length(); i++) {
            decomposedSID[i] = SID.charAt(i) - 48;
            numOfLuckyNumber += decomposedSID[i] == luckyNumber ? 1 : 0;
        }

        Arrays.sort(decomposedSID);
        for (int i = 0; i < numOfLuckyNumber; i++) {
            System.out.print(luckyNumber);
        }
        for (int i = decomposedSID.length - 1; i >= 0; i--) {
            System.out.print(decomposedSID[i] != luckyNumber ? decomposedSID[i] : "");
        }
    }
}