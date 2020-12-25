public class Student {
    private int sid;
    private String name;
    private static int studentCnt = 1;

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getStudentCnt() {
        return studentCnt;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, sid: %d", name, sid);
    }

    public Student(String name) {
        this.name = name;
        this.sid = studentCnt;
        studentCnt++;
    }
}
