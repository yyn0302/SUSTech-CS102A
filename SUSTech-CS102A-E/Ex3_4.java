// package src;

import java.util.Scanner;
import java.lang.Math;
// import java.math.MathContext;


public class Ex3_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int state;
        int proc = 1;
        double Pi = (double)0;
//        double temp;

        System.out.print("Enter the precision (n): ");
        state = sc.nextInt();
        sc.close();

        //one possible version using `for`
//        for(int i = 1; i <= state; i++){
//            Pi += (Math.pow((-1), (i + 1))) * (double)(4.00f / (2.00f * i -1.00f));
////            Pi += ((-1) ^ (i + 1)) * (double)(4.00f / (2.00f * i -1.00f));
////            temp = ((-1) ^ (i + 1)) * (double)(4 / (2 * i -1));
////            System.out.println(((-1) ^ i) * (double)(4 / (2 * i -1)));
//            System.out.println(Pi);
//        }
//        System.out.print(Pi);


        //one possible version using `while`
         while(proc <= state){
             Pi += Math.pow((-1), (proc + 1)) * (double)(4.00f / (2.00f * proc -1.00f));
             ++ proc;
             System.out.println(Pi);
         }         
    }
}

class Ex3_4_F2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double precision, pi_1, pi_2;
        pi_1 = 0;
        pi_2 = 3;

        System.out.print("Enter the precision: ");
        precision = sc.nextFloat();
        sc.close();

        while(Math.abs(pi_1 - pi_2) > precision) {

        }

    }
}