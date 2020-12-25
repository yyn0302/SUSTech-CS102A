import java.util.LinkedList;
import java.util.List;

public class ArtsStudent extends Student {
    public ArtsStudent(String[] info) {
        super.setNumber(Integer.parseInt(info[0]));
        super.setCollege(Integer.parseInt(info[1]));
    }

    @Override
    public boolean checkGraduate() {
        int artsCredit = 0, generalCredit = 0, scienceCredit = 0;
        for (Course c : super.getCourses()) {
            switch (c.getCourseType()) {
                case ARTS:
                    artsCredit += c.getCredit();
                    break;
                case GENERAL:
                    generalCredit += c.getCredit();
                    break;
                case SCIENCE:
                    scienceCredit += c.getCredit();
                    break;
            }
        }
        return (artsCredit >= 8 && generalCredit >= 4 && scienceCredit >= 4);
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d", super.toString(), "ARTS", super.getCourses().size());
    }
}
