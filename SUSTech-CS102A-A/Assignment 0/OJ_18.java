import java.util.Scanner;

public class OJ_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String temp = scanner.nextLine();
        String encrypted = scanner.nextLine();
        scanner.close();

        StringBuilder sb = new StringBuilder();

        char[] lowerCode = new char[26];
        char[] upperCode = new char[26];
        for (int i = 0; i < 26; i++) {
            lowerCode[i] = temp.charAt(i);
            upperCode[i] = (char) (lowerCode[i] - ('a' - 'A'));
        }

        for (int i = 0; i < encrypted.length(); i++) {
            if (encrypted.charAt(i) <= 90 && encrypted.charAt(i) >= 65)
                // 65-90
                sb.append(upperCode[encrypted.charAt(i) - 'A']);
            else if (encrypted.charAt(i) <= 122 && encrypted.charAt(i) >= 97)
                // 97-122
                sb.append(lowerCode[encrypted.charAt(i) - 'a']);
            else
                sb.append(encrypted.charAt(i));
        }
        System.out.print(sb.toString());
    }
}