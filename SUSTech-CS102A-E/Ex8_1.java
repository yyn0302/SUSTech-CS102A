import java.util.Arrays;

public class Ex8_1 {
    public static void main(String[] args) {
        System.out.println('\u2665');
        char[] charArr = {'h','e','l','l','o'};
        String hello = new String(charArr, 0, 4);
        System.out.println(hello);

        String demo1 = "Hex";
        String demo2 = "Hex";
//        demo1 = "Bin";
        System.out.println(demo1);
        System.out.println(demo2);
//        String.getChars();
        String s1 = "hello world";
        System.out.println(s1.length());
//        @throws index out of bound exception
        System.out.println("Hex".equals(demo2) ? "y":"n");

//        String s1 = "Hello World";
        String s2 =  "Hello World";
        if(s2.equalsIgnoreCase(s1)) System.out.println("s1 = s2");
        System.out.println(s2.compareTo(s1));
        System.out.println(s1.regionMatches(1,s2,1,5));
        System.out.println(s1.regionMatches(true,1,s2,1,8));
        System.out.println(s1.startsWith("ell", 1));

        System.out.println(s1.concat(s2));
//        test tt = new test();

        Character c1 = 'A';
        Character c2 = new Character('A');
        if (c1 == c2)
            System.out.println("cc1 and cc2 are the same");
        if (c1.equals(c2))
            System.out.println("cc1 and cc2 are the same");
    }

    abstract class test {
        public abstract void demo();
    }
    class extendTEST extends test {
        @Override
        public void demo() {
            System.out.println("this function demo is override from abstract class test");
        }
    }
}

