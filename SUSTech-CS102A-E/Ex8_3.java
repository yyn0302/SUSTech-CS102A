import java.util.Scanner;

public class Ex8_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String toInput;
        for (; ; ) {
            boolean flag = true;
            toInput = sc.next();
            char[] charOfInput = new char[toInput.length()];
            if (toInput.equals("quit")) break;
            else {
                toInput.getChars(0, toInput.length(), charOfInput, 0);
            } // get sep chars, find if all [i] match [length-i-1]
            for (int i = 0; i < toInput.length() / 2; i++) {
                if (charOfInput[i] != charOfInput[charOfInput.length - i - 1]) {
                    System.out.printf("%S is not a palindrome\n", toInput);
                    flag = false;
                    break;
                }
            }
            if (flag)
                System.out.printf("%s is a palindrome\n", toInput);
        }
    }
}
