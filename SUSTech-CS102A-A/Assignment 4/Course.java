public class Course {
    private String courseNumber;
    private CourseType courseType;
    private int credit;

    public Course(String info) {
        this.courseNumber = info.charAt(0) + "";
        switch (Integer.parseInt(info.charAt(2) + "")) {
            case 0:
                this.courseType = CourseType.ARTS;
                break;
            case 1:
                this.courseType = CourseType.SCIENCE;
                break;
            case 2:
                this.courseType = CourseType.GENERAL;
                break;
        }
        this.credit = Integer.parseInt(info.substring(4));
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseType=" + courseType +
                ", credit=" + credit + '}';
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public int getCredit() {
        return credit;
    }
}
