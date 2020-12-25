import java.lang.*;
import java.util.*;

// import java.util.*;
// import java.lang.*;

class demo {
    public int a = 0;
    enum Demo {a, b};
    Demo aa = Demo.a;
}

public class Ex7_1 {
	public static void main(String[] args) {
    // demo dm = new demo();
    // System.out.println(dm.a);
    // String ass = "asss";
    // System.out.println("ass".equals(ass)?"y":"n");
    GradeBook gb = new GradeBook("CS102");
        System.out.println(gb.getter());
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            items.add("wjx");
        }
    }

    
}

class GradeBook {
    private String courseName; // course name of this Gradebook 
    // constructor initialize
    public GradeBook( String name ) {
        courseName = name;
    } // end constructor
    public String getter() {
        return courseName;
        
    }
}