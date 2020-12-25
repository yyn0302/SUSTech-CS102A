import java.io.*;

public class Ex9_2 {
    public static void main(String[] args) {
        File dir = new File("/Users/hezean/Documents");
        String[] files = dir.list();
        for (String file : files) {
            System.out.println(file);
        }    
    }
}
