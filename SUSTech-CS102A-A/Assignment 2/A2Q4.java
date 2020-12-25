import java.util.Scanner;

public class A2Q4 {
    static int calc(double num1, int num2, char ch) {
        switch (ch) {
            case '+':
                return (int) Math.floor(num1 + num2);
            case '-':
                return (int) Math.floor(num1 - num2);
            case '*':
                return (int) Math.floor(num1 * num2);
            case '/':
                return (int) Math.floor(num1 / num2);
            default:
                return -1;
        }
    }

    static int getMinIndex(int a, int b, int c, int d) {
        int temp = Integer.MAX_VALUE;
        if (a >= 0) temp = a;
        if (b >= 0 && b < temp) temp = b;
        if (c >= 0 && c < temp) temp = c;
        if (d >= 0 && d < temp) temp = d;
        return temp;
    }

    static int getFlag(String input, int fromIndex) {
        int temp1 = input.indexOf('+', fromIndex);
        int temp2 = input.indexOf('-', fromIndex);
        int temp3 = input.indexOf('*', fromIndex);
        int temp4 = input.indexOf('/', fromIndex);
        return getMinIndex(temp1, temp2, temp3, temp4);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        int flag1 = getFlag(input, 0);
        int flag2 = getFlag(input, flag1 + 1);
        if (flag2 > input.length() - 1) flag2 = input.length() - 1;
        double tempAns = 0;
        if (flag1 == 0) {
            tempAns = (-1) * Integer.parseInt(input.substring(flag1 + 1, flag2));
            flag1 = flag2;
            flag2 = getFlag(input, flag1 + 1);
            if (flag2 > input.length() - 1) flag2 = input.length() - 1;
        } else {
            tempAns = Integer.parseInt(input.substring(0, flag1));
        }
        while (true) {
            if (flag1 < 0 || flag1 >= input.length()) {
                break;
            } else if (flag2 == input.length() - 1) {
                tempAns = calc(tempAns, Integer.parseInt(input.substring(flag1 + 1)), input.charAt(flag1));
                break;
            }
            tempAns = calc(tempAns, Integer.parseInt(input.substring(flag1 + 1, flag2)), input.charAt(flag1));
            flag1 = flag2;
            flag2 = getFlag(input, flag1 + 1);
            if (flag2 > input.length() - 1) flag2 = input.length() - 1;
        }
        System.out.print((int) Math.floor(tempAns));
    }
}
