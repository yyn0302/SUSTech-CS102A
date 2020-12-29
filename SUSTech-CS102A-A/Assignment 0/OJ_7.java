import java.util.Scanner;

public class OJ_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String borrowTime = sc.next();
        String returnTime = sc.next();

        int[] borrowDetail = {Integer.parseInt(borrowTime.substring(0, 4)),
                Integer.parseInt(borrowTime.substring(4, 6)),
                Integer.parseInt(borrowTime.substring(6))};

        int[] returnDetail = {Integer.parseInt(returnTime.substring(0, 4)),
                Integer.parseInt(returnTime.substring(4, 6)),
                Integer.parseInt(returnTime.substring(6))};

        int keptTime = getDeltaDay(returnDetail[0], returnDetail[1], returnDetail[2]) -
                getDeltaDay(borrowDetail[0], borrowDetail[1], borrowDetail[2]);

        if (keptTime <= 30) {
            System.out.print("The return is successful!");
            return;
        } else {
            float fee = 0;
            if (keptTime <= 60) fee += (keptTime - 30) * 0.5;
            else fee += 15;
            if (keptTime > 60) fee += (keptTime - 60) * 0.7;
            if (fee > 250f || keptTime > 180) fee = 250;
            System.out.printf("You are late, please pay %.1f yuan!", fee);

        }
        sc.close();
    }

    public static boolean isLeapYear(int yyyy) {
        boolean isLeapYear;
        if (yyyy % 4 != 0) {
            isLeapYear = false;
        } else if (yyyy % 100 != 0) {
            isLeapYear = true;
        } else isLeapYear = yyyy % 400 == 0;
        return isLeapYear;
    }

    public static int getDeltaDay(int yyyy, int mm, int dd) {
        int deltaDay = 0;

        int leapYear = 0;
        int nonLeapYear = 0;
        if (yyyy != 1) {
            for (int i = 1; i < yyyy; i++) {
                if (isLeapYear(i)) leapYear += 1;
                else nonLeapYear += 1;
            }
        }

        int deltaDayByMonth = 0;
        switch (mm) {
            case 12:
                deltaDayByMonth += 30;
            case 11:
                deltaDayByMonth += 31;
            case 10:
                deltaDayByMonth += 30;
            case 9:
                deltaDayByMonth += 31;
            case 8:
                deltaDayByMonth += 31;
            case 7:
                deltaDayByMonth += 30;
            case 6:
                deltaDayByMonth += 31;
            case 5:
                deltaDayByMonth += 30;
            case 4:
                deltaDayByMonth += 31;
            case 3:
                deltaDayByMonth += isLeapYear(yyyy) ? 29 : 28;
            case 2:
                deltaDayByMonth += 31;
            case 1:
                break;
        }

        deltaDay = leapYear * 366 + nonLeapYear * 365 + deltaDayByMonth + dd;
        return deltaDay;
    }
}
