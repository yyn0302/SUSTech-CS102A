class Super {
    @Override
    public String toString() {
        return "Super";
    }

    public String superString() {
        return "superString";
    }
}

class Sub extends Super {
    @Override
    public String toString() {
        return "Sub";
    }
}


public class Ex12_1 {
    public static void main(String[] args) {
        Super su = new Super();
        Super ss = new Sub();
        Sub sb = new Sub();

        System.out.println(su);
        System.out.println(ss);
        System.out.println(sb);

        System.out.println(su.superString());
        System.out.println(ss.superString());
        System.out.println(sb.superString());
    }
}
