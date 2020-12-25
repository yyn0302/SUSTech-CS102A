import java.util.*;
import java.lang.*;

class Circle {
    private double radius, x, y;
//    public Circle(double radius, double x, double y) {
//        this.radius = radius;
//        this.x = x;
//        this.y = y;
//    }

    public double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }

    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    public void getPosition() {
        System.out.printf("Position of the circle is (%.1f,%.1f)\n", x, y);
    }

}

public class Ex7_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float radius = sc.nextFloat(), x = sc.nextFloat(), y = sc.nextFloat();
//        Circle circle = new Circle(radius, x, y);
        ArrayList<Circle> circles = new ArrayList<>();
        Circle c1 = new Circle();
        System.out.println(c1.getArea());
        c1.getPosition();
        circles.add(c1);
        circles.get(0).getPosition();
    }
}
