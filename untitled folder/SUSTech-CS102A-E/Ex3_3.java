//guessing number
//@author = Chris
//@date = 2020.09.21
//package src;

import java.util.Random;
import java.util.Scanner;


public class Ex3_3 {

    public static int getGuess() {
        int guess;
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Please enter a number in [0,100]: ");
        guess = sc.nextInt();
        while(guess < 0 || guess >100){
            System.out.println("error, reenter: ");
            guess = sc.nextInt();
        }
        sc.close();
        return guess;
    }


    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        // /*Ex3_3.*/getGuess getGuess = new getGuess();
        
        int mag_num = rand.nextInt(100);
        int guess = getGuess();
        for(;;){
            if(guess == mag_num){
                System.out.println("End");
                break;
            }
            else if(guess < mag_num){
                System.out.println("Too small");
                guess = getGuess();
            }
            else if(guess > mag_num){
                System.out.println("Too large");
                guess = getGuess();
            }
        }
    }
}
