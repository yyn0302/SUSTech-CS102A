import java.util.LinkedList;
import java.util.List;

public class ScienceStudent extends Student {
    private double generalWeight;
    private double artsWeight;
    private final double scienceWeight = 1.00f;
    public ScienceStudent(String[] info) {
        super.setNumber(Integer.parseInt(info[0]));
        super.setCollege(Integer.parseInt(info[1]));
        this.generalWeight = Double.parseDouble(info[2]);
        this.artsWeight = Double.parseDouble(info[3]);
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
        if ((artsCredit * this.artsWeight + generalCredit * this.generalWeight + scienceCredit * this.scienceWeight) >= 10.00f
                && super.getCourses().size() >= 4) return true;
        else return false;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d", super.toString(), "SCIENCE", super.getCourses().size());
    }
}
