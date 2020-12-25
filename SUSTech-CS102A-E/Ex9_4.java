import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Ex9_4 {
    
    static boolean isLowerCase(int temp) {
        return temp >= 97 && temp <= 122;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("in.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));

        String line;
        int temp;
        boolean isNeedChange;
        while ((line = in.readLine()) != null) {
            isNeedChange = true;
            for (int i = 0; i < line.length(); i++) {
                temp = line.charAt(i);
                if (isLowerCase(temp)) {
                    if (isNeedChange) {
                        writer.write(temp - 32);
                        isNeedChange = false;
                    } else {
                        writer.write(temp);
                    }
                } else {
                    if (temp >= 65 && temp <= 90) {
                        isNeedChange = false;
                    } else if (temp == '.') {
                        int back = i - 1;
                        while (line.charAt(back) != ' ') {
                            back--;
                        }
                        if (isLowerCase(line.charAt(back + 1))) {
                            isNeedChange = true;
                        }
                    }
                    writer.write(temp);
                }
            }
            writer.write('\n');
        }
        in.close();
        writer.close();
    }
}
