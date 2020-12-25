import java.io.*;

public class Ex9_3 {
    public static void main(String[] args) {
        String in = "in.txt", out = "out.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(in));
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            int tempASCII;
            while ((tempASCII = br.read()) != -1) {
                if (tempASCII >= 97 && tempASCII <= 122) tempASCII -= 32;
                bw.write((char)tempASCII);
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
