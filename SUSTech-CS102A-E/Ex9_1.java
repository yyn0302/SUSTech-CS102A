// Writing data to a sequential text file with class Formatter.

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class Ex9_1 {
    public static void main(String[] args) throws Exception {
        Formatter output = new Formatter("clients.txt"); // open the file
        Scanner input = new Scanner(System.in); // reads user input
        File cl = new File("clients.txt");
        int accountNumber; // stores account number
        String firstName; // stores first name
        String lastName; // stores last name
        double balance; // stores account balance

        System.out.printf("%s\n%s\n%s\n%s\n\n",
                "To terminate input, type the end-of-file indicator ",
                "when you are prompted to enter input.",
                "On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
                "On Windows type <ctrl> z then press Enter");
        System.out.printf("%s\n%s",
                "Enter account number (> 0), first name, last name and balance.",
                "? ");

        while (input.hasNext()) {
            accountNumber = input.nextInt(); // read account number
            firstName = input.next(); // read first name
            lastName = input.next(); // read last name
            balance = input.nextDouble(); // read balance

            if (accountNumber > 0) {
                // write new record
                output.format("%d %s %s %.2f\n", accountNumber,
                        firstName, lastName, balance);
            } else {
                System.out.println("Account number must be greater than 0.");
            }
            System.out.printf("%s %s\n%s", "Enter account number (>0),",
                    "first name, last name and balance.", "? ");
        }
        output.close();
        input.close();

        Scanner sc = new Scanner(cl);
        System.out.println("\n\n\n\n\n");
        while(sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}