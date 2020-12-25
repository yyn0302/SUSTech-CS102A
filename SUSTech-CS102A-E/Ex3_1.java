public class Ex3_1 {
    public static void main(String[] args) {
        String name = args[0];
        int age = Integer.parseInt(args[1]);
        float weight = Float.parseFloat(args[2]);
        char grade = args[3].charAt(0);
        System.out.printf("Your name is %s\nYou are %d years old\nYou weight %.1f KG\nYour grade is %c\n\n",
         name, age, weight, grade);
    }
}
