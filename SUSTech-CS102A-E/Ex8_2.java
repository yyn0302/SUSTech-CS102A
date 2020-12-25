import java.util.ArrayList;
import java.util.Random;

class circle {
    Random rd = new Random();
    private double r, x, y;

    public circle() {
        this.r = 2 * rd.nextDouble() + 1;
        this.x = 3 * rd.nextDouble() + 2;
        this.y = 3 * rd.nextDouble() + 2;
    }

    public double distanceToOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getS() {
        return r * r * Math.PI;
    }

    public void showInfo(int index) {
        System.out.printf("Circle #%d: radius = %.2f, x = %.2f, y = %.2f\n", index, r, x, y);
    }
}

public class Ex8_2 {
    public static void main(String[] args) {
        Random rd = new Random();
        int n = rd.nextInt(5) + 5;
        ArrayList<circle> circles = new ArrayList<>();
        double[] area = new double[n];
        double[] dist = new double[area.length];

        for (int i = 0; i < n; i++) {
            circle c = new circle();
            circles.add(c);
            c.showInfo(i + 1);
            area[i] = circles.get(i).getS();
            dist[i] = circles.get(i).distanceToOrigin();
        }

        double temp = area[0];
        int flag = 1;
        for (int i = 1; i < n; i++) {
            if (area[i] < temp) temp = area[i];
        }
        for (int i = 1; i < n; i++) {
            if (area[i] == temp) flag = i + 1;
            break;
        }
        System.out.printf("Circle #%d is the smallest circle, area = %.2f\n", flag, temp);
//        flag = 1;
//        temp = dist[0];
//        for (int i = 1; i < n; i++) {
//            if (dist[i] > temp) temp = dist[i];
//        }
//        for (int i = 1; i < n; i++) {
//            if (dist[i] == temp) flag = i + 1;
//            break;
//        }
        // may have some bugs

        System.out.printf("Circle #%d is the farthest circle, distance to origin = %.2f\n", flag, temp);
    }
}
