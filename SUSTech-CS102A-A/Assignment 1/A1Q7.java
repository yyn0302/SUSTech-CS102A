import java.util.Scanner;

public class A1Q7 {
    public static boolean isLeapYear(int yyyy) {
        boolean isLeapYear;
        if (yyyy % 4 != 0) {
            isLeapYear  = false;
        }
        else if (yyyy % 100 != 0) {
            isLeapYear  = true;
        } 
        else if (yyyy % 400 != 0) {
            isLeapYear  = false;
        }
        else {
            isLeapYear  = true;
        }
        return isLeapYear;
    }

    public static int getDeltaDay(int yyyy, int mm, int dd) {
        int deltaDay = 0;

        // for each delta year, find if they are leap years
        int leapYear = 0;
        int nonLeapYear = 0;
        if (yyyy != 1) {
            for (int i = 1; i < yyyy; i++) {  // do not count yyyy it self, for it doesn't contain a complete year
                if (isLeapYear(i))  leapYear += 1;
                else    nonLeapYear += 1;
            }
        }
        // for the same year's delta month, find their delta day
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

        deltaDay = leapYear * 366 + nonLeapYear * 365 + deltaDayByMonth + dd;  // delta day in the same month is dd itself
        return deltaDay;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int daysInWeek = sc.nextInt(), yyyy = sc.nextInt(), mm = sc.nextInt(), dd = sc.nextInt();
        sc.close();
        System.out.print(getDeltaDay(yyyy, mm, dd) % daysInWeek == 0 ? daysInWeek : getDeltaDay(yyyy, mm, dd) % daysInWeek);
    }
}
