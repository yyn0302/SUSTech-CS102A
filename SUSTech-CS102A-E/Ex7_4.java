import java.util.*;
import java.lang.*;

class Food {
    private String name, type;
    private int size;
    private double price;

    public void showInformation() {
        System.out.printf("%s %s: (%d Inches) %.2f$\n", type, name, size, price);
    }
//@getter
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
//@setter
    public void setName(String arg) {
        this.name = arg;
    }
    public void setType(String arg) {
        this.type = arg;
    }
    public void setSize(int arg) {
        this.size = arg;
    }
    public void setPrice(float arg) {
        this.price = arg;
    }
}

public class Ex7_4 {
    public static void main(String[] args) {
        Food pizza1 = new Food();
        Food pizza2 = new Food();
        Food FriedRice = new Food();
        Food Noodles = new Food();
        ArrayList<Food> Foods = new ArrayList<>();
        Foods.add(pizza1);
        Foods.add(pizza2);
        Foods.add(FriedRice);
        Foods.add(Noodles);
    }
}
