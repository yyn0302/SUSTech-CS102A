import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConcreteUniversity implements University {
    private List<Student> students = new LinkedList<>();
    private List<Course> courses = new LinkedList<>();
    private List<Relation> relations = new LinkedList<>();

    private class Relation {
        private String courseNum;
        private List<String> prerequisite = new LinkedList<>();
        private int neededPrerequisites = 0;

        public Relation(String relation) {
            this.courseNum = relation;
            prerequisite = null;
        }

        public Relation(List<String> relations) {
            this.courseNum = relations.get(relations.size() - 1);
            for (int i = 0; i < relations.size() - 2; i++) {
                this.prerequisite.add(relations.get(i));
            }
            neededPrerequisites = Integer.parseInt(relations.get(relations.size() - 2));
        }

        public List<String> getPrerequisite() {
            return prerequisite;
        }

        public int getNeededPrerequisites() {
            return neededPrerequisites;
        }

        public String getCourseNum() {
            return courseNum;
        }
    }

    private int indexOfStudent(int sid) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getNumber() == sid) return i;
        }
        return -1;
    }

    private int indexOfCourse(String cid) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseNumber().equals(cid)) return i;
        }
        return -1;
    }

    @Override
    public void addOneCourse(String courseInfo) {
        courses.add(new Course(courseInfo));
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public void addOneStudent(String studentInfo) {
        String[] args = studentInfo.split(" ");
        if (args.length == 2) students.add(new ArtsStudent(args));
        else students.add(new ScienceStudent(args));
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public String showArtsANDScienceCount() {
        int artStuCnt = 0, sciStuCnt = 0;
        for (Student student : students) {
            if (student instanceof ScienceStudent) sciStuCnt++;
            else artStuCnt++;
        }
        return String.format("ARTS-%d-SCIENCE-%d", artStuCnt, sciStuCnt);
    }

    @Override
    public void addCourseRelations(List<String> relations) {
        for (String re : relations) {
            List<String> r = Arrays.asList(re.split(" "));
            if (r.size() == 1) this.relations.add(new Relation(r.get(0)));
            else this.relations.add(new Relation(r));
        }
    }

    @Override
    public boolean selectCourse(int studentNumber, String courseNumber) {
        int indexOfStu = indexOfStudent(studentNumber);
        int indexOfCur = indexOfCourse(courseNumber);
        if (students.get(indexOfStu).indexOfCourse(courseNumber) != -1) return false;
        if (indexOfCur == -1 || indexOfStu == -1) {
            return false;
        }
        List<List<String>> preCourses = new LinkedList<>();
        List<Integer> preCoursesNeedNum = new LinkedList<>();

        for (Relation r : relations) {
            if (r.getCourseNum().equals(courseNumber)) {
                preCourses.add(r.getPrerequisite());
                preCoursesNeedNum.add(r.getNeededPrerequisites());
            }
        }
        boolean flag = true;
        for (int i = 0; i < preCourses.size(); i++) {
            List<String> list = preCourses.get(i);
            int temp = 0;
            boolean isEmpty = false;
            try {
                for (String cid : list) {
                    if (students.get(indexOfStu).contains(cid)) temp++;
                }
            } catch (NullPointerException e) {
                isEmpty = true;
            }
            if (!isEmpty && temp < preCoursesNeedNum.get(i)) {
                flag = false;
                break;
            }
        }
        if (flag) students.get(indexOfStu).addSelectedCourse(courses.get(indexOfCur));
        return flag;
    }

    @Override
    public List<Course> getCoursesOfOneStudentOrderByCourseNumber(int studentNumber) {
        List<Course> curs = students.get(indexOfStudent(studentNumber)).getCourses();
        insSort(curs);
        return curs;
    }

    private void insSort(List<Course> list) {
        Course temp;
        for (int i = 1; i < list.size(); i++) {
            temp = list.get(i);
            while (i > 0 && list.get(i - 1).getCourseNumber().charAt(0) > temp.getCourseNumber().charAt(0)) {
                list.set(i, list.get(i - 1));
                i--;
            }
            list.set(i, temp);
        }
    }

    @Override
    public boolean checkGraduateForOneStudent(int studentNumber) {
        try {
            return students.get(indexOfStudent(studentNumber)).checkGraduate();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void selectCourseByCollege(int college, String courseNumber) {
        for (Student s : students) {
            if (s.getCollege() == college) {
                if (!s.contains(courseNumber)) {
                    selectCourse(s.getNumber(), courseNumber);
                }
            }
        }
    }
}
