import java.util.ArrayList;
import java.util.Scanner;

class Ex8_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            String input = sc.nextLine().trim();
            if (input.length() == 0) break;
            ArrayList<Character> chars = new ArrayList<Character>();
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isWhitespace(input.charAt(i)) && !chars.contains(input.charAt(i)))
                    chars.add(input.charAt(i));
            }
            for (char c : chars) buffer.append(c);
            System.out.println("After removing repeating chars: " + buffer.toString());
        }
        System.out.println("Empty string, exit...");
    }
}