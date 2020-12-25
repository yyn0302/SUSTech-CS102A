import java.util.Scanner;

public class Ex2_3 {


    /**
     * this part is a possible solution, which will give
     * the week, day, hour, minute, second information
     */

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);

    //     int time;
    //     int weeks, days, hours, minutes, seconds;

    //     System.out.printf("Enter the time by seconds: ");
    //     time = sc.nextInt();
    //     // get total seconds

    //     weeks = time / (7*24*60*60);
    //     if(weeks != 0){
    //         System.out.printf("%d Week", weeks);
    //         if(weeks != 1){
    //             System.out.print("s\t");
    //         }
    //         else{
    //             System.out.print("\t");
    //         }
    //     }
    //     // get num of weeks

    //     time -= (7*24*60*60) * weeks;
    //     days = time / (24*60*60);
    //     if(days != 0){
    //         System.out.printf("%d Day", days);
    //         if(days != 1){
    //             System.out.print("s\t");
    //         }
    //         else{
    //             System.out.print("\t");
    //         }
    //     }
    //     // get num of days

    //     time -= (24*60*60) * days;
    //     hours = time / (60*60);
    //     if(hours != 0){
    //         System.out.printf("%d Hour", hours);
    //         if(hours != 1){
    //             System.out.print("s\t");
    //         }
    //         else{
    //             System.out.print("\t");
    //         }
    //     }
    //     // get num of hours

    //     time -= (60*60) * hours;
    //     minutes = time / 60;
    //     if(minutes != 0){
    //         System.out.printf("%d Minute", minutes);
    //         if(minutes != 1){
    //             System.out.print("s\t");
    //         }
    //         else{
    //             System.out.print("\t");
    //         }
    //     }
    //     // get num of minutes

    //     time -= 60 * minutes;
    //     seconds = time;
    //     if(minutes != 0){
    //         System.out.printf("%d Second", seconds);
    //         if(seconds != 1){
    //             System.out.print("s\t\n\n");
    //         }
    //         else{
    //             System.out.print("\t\n\n");
    //         }
    //     }
    //     // get num of seconds
    // }



    //TODO the program is carved up from here.
    /**
     * this part will just show hour, minute and second
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int time;
        // int weeks, days, hours, minutes, seconds;
        int hours, minutes, seconds;

        System.out.printf("Enter the time by seconds: ");
        time = sc.nextInt();
        sc.close();
        // get total seconds

        // weeks = time / (7*24*60*60);
        // if(weeks != 0){
        //     System.out.printf("%d Week", weeks);
        //     if(weeks != 1){
        //         System.out.print("s\t");
        //     }
        //     else{
        //         System.out.print("\t");
        //     }
        // }
        // // get num of weeks

        // time -= (7*24*60*60) * weeks;
        // days = time / (24*60*60);
        // if(days != 0){
        //     System.out.printf("%d Day", days);
        //     if(days != 1){
        //         System.out.print("s\t");
        //     }
        //     else{
        //         System.out.print("\t");
        //     }
        // }
        // // get num of days

        // time -= (24*60*60) * days;
        hours = time / (60*60);
        if(hours != 0){
            System.out.printf("%d Hour", hours);
            if(hours != 1){
                System.out.print("s\t");
            }
            else{
                System.out.print("\t");
            }
        }
        // get num of hours

        time -= (60*60) * hours;
        minutes = time / 60;
        if(minutes != 0){
            System.out.printf("%d Minute", minutes);
            if(minutes != 1){
                System.out.print("s\t");
            }
            else{
                System.out.print("\t");
            }
        }
        // get num of minutes

        time -= 60 * minutes;
        seconds = time;
        if(minutes != 0){
            System.out.printf("%d Second", seconds);
            if(seconds != 1){
                System.out.print("s\t\n\n");
            }
            else{
                System.out.print("\t\n\n");
            }
        }
        // get num of seconds
    }
}