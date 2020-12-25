public class Grade {
    private Course course;
    private Student student;
    private float grade;
    private float gpa;

    public static float calGpa(float grade) {
        if (grade >= 97) return 4.00f;
        else if (grade >= 93) return 3.94f;
        else if (grade >= 90) return 3.85f;
        else if (grade >= 87) return 3.73f;
        else if (grade >= 83) return 3.55f;
        else if (grade >= 80) return 3.32f;
        else if (grade >= 77) return 3.09f;
        else if (grade >= 73) return 2.78f;
        else if (grade >= 70) return 2.42f;
        else if (grade >= 67) return 2.08f;
        else if (grade >= 63) return 1.63f;
        else if (grade >= 60) return 1.15f;
        else return 0.00f;
    }

    public Course getCourse() {
        return course;
    }

    public float getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGrade(float grade) {
        if (grade >= 0) this.grade = grade;
        gpa = calGpa(grade);
    }

    @Override
    public String toString() {
        return String.format("sid: %d, cid: %d, grade: %.1f, gpa: %.2f", student.getSid(), course.getCid(), grade, gpa);
    }

    public Grade(Course course, Student student, float grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        gpa = calGpa(grade);
    }
}
