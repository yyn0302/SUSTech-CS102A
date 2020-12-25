import java.util.ArrayList;

public class GradeSystem {
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<Grade> gradeList;

    public GradeSystem() {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        gradeList = new ArrayList<>();
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Grade> getGradeList() {
        return gradeList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public boolean checkStudent(int sid) {
        for (Student stu : studentList) {
            if (stu.getSid() == sid) return true;
        }
        return false;
    }

    public boolean checkCourse(int cid) {
        for (Course cur : courseList) {
            if (cur.getCid() == cid) return true;
        }
        return false;
    }

    public int checkGrade(Grade grade2check) {
        for (int i = 0; i < gradeList.size(); i++) {
            Grade grd = gradeList.get(i);
            if (grd.getStudent().getSid() == grade2check.getStudent().getSid()
                    && grd.getCourse().getCid() == grade2check.getCourse().getCid())
                return i;
        }
        return -1;
    }

    public boolean addStudent(Student student) {
        if (checkStudent(student.getSid())) return false;
        else {
            studentList.add(student);
            return true;
        }
    }

    public boolean addCourse(Course course) {
        if (checkCourse(course.getCid())) return false;
        else {
            courseList.add(course);
            return true;
        }
    }

    public boolean addGrade(Grade grade) {
        int index = checkGrade(grade);
        if (index >= 0 && gradeList.get(index).getGrade() < 60 && grade.getGrade() >= 60) {
            gradeList.get(index).setGrade(grade.getGrade());
            return true;
        } else if (index == -1 && checkStudent(grade.getStudent().getSid()) && checkCourse(grade.getCourse().getCid())) {
            gradeList.add(grade);
            return true;
        } else return false;
    }

    public float gpa(int sid) {
        float GPA = 0f;
        int course = 0;
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getStudent().getSid() == sid) {
                GPA += gradeList.get(i).getGpa();
                course++;
            }
        }
        if (course != 0) return GPA / course;
        else return 0.00f;
    }

    public float average(int cid) {
        float totalScore = 0f;
        int studentCnt = 0;
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getCourse().getCid() == cid) {
                totalScore += gradeList.get(i).getGrade();
                studentCnt++;
            }
        }
        if (studentCnt != 0) return totalScore / studentCnt;
        else return 0.00f;
    }

    public ArrayList<Grade> listStuGrade(int sid) {
        ArrayList<Grade> listStuGrade = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            for (int j = 0; j < gradeList.size(); j++) {
                if (gradeList.get(j).getStudent().getSid() == sid
                        && gradeList.get(j).getCourse().getCid() == courseList.get(i).getCid()) {
                    listStuGrade.add(gradeList.get(j));
                }
            }
        }
        return listStuGrade;
    }

    public ArrayList<Grade> listCouGrade(int cid) {
        ArrayList<Grade> listCouGrade = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < gradeList.size(); j++) {
                if (gradeList.get(j).getCourse().getCid() == cid &&
                        gradeList.get(j).getStudent().getSid() == studentList.get(i).getSid()) {
                    listCouGrade.add(gradeList.get(j));
                }
            }
        }
        return listCouGrade;
    }
}
