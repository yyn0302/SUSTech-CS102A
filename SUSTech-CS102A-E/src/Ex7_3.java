class User {
    private String name, password;
    private double money;

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public double getMoney() {
        return money;
    }
    public void setName(String arg) {
        this.name = arg;
    }
    public void setPassword(String arg) {
        this.password = arg;
    }
    public void setMoney(double arg) {
        this.money = arg;
    }
    public void introduce() {
        System.out.printf("name: %s\nbalance: $%.2f\n\n", name, money);
        return;
    }
    public void expense(double value) {
        this.money -= value;
    }
    public void income(double value) {
        this.money += value;
    }
}

public class Ex7_3 {
    public static void main(String[] args) {
        User usr = new User();
        usr.setName("Lucy");
        usr.setPassword("123456");
        usr.setMoney(1000);
        usr.introduce();
        usr.expense(2000);
        usr.expense(500);
        usr.income(1000);
        usr.introduce();
    }
}
