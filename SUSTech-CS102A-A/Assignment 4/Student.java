import java.util.LinkedList;
import java.util.List;

public abstract class Student {
    private int number;
    private int college;
    private List<Course> courses = new LinkedList<>();

    public abstract boolean checkGraduate();

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCollege(int college) {
        this.college = college;
    }

    public int getNumber() {
        return this.number;
    }

    public int getCollege() {
        return this.college;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public int indexOfCourse(String cid) {
        for (int i = 0; i < this.courses.size(); i++) {
            if (this.courses.get(i).getCourseNumber().equals(cid)) return i;
        }
        return -1;
    }

    public void addSelectedCourse(Course c) {
        this.courses.add(c);
    }

    @Override
    public String toString() {
        return String.format("%d-%d", this.number, this.college);
    }

    public boolean contains(String cid) {
        if (courses.isEmpty()) return false;
        for (Course c : courses) {
            if (c.getCourseNumber().equals(cid)) return true;
        }
        return false;
    }
}
