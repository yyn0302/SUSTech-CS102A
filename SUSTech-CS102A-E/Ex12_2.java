abstract class absClass {
    public abstract String toS();
    public abstract int count();
    int a;
}

class conClass extends absClass {
    public String toS() {
        return "a";
    }

    public int count(){
        return 1;
    }
}

public class Ex12_2 {
    public static void main(String[] args) {
        conClass cc = new conClass();
        System.out.println(cc.toS());
    }
}
