//This class is used for the measurements of certain types of climbing feats for individuals.
public class Climbing {
    String fingerStrength;
    String relativeStrength;
    String benchmarks;


    public String getFingerStrength() {
        return fingerStrength;
    }

    public void setFingerStrength(String fingerStrength) {
        this.fingerStrength = fingerStrength;
    }

    public String getBenchmarks() {
        return benchmarks;
    }

    public void setBenchmarks(String benchmarks) {
        this.benchmarks = benchmarks;
    }

    public String getRelativeStrength() {
        return relativeStrength;
    }

    public void setRelativeStrength(String relativeStrength) {
        this.relativeStrength = relativeStrength;
    }


    public Climbing(String fingerStrength, String relativeStrength, String Benchmarks) {
        this.fingerStrength = fingerStrength;
        this.relativeStrength = relativeStrength;
        this.benchmarks = Benchmarks;
    }

    public Climbing() {
        this(null, null, null);
    }


    public void sendRouteLog() {
        System.out.println("Climbing performance metrics successfully processed.");
    }


    public void ClimbingScoringTypes() {
        System.out.println("These are different ways of just measurements for small aspects of " +
                "climbing but not quite for\nactual competitive reasons, these being:\n" + fingerStrength +
                " used for things like hang test, or peak force measurments;\n" + relativeStrength + " is used to " +
                "get a better idea of one's ability to sustain themselves,\nand then we have " + benchmarks +
                " which is an understanding of one's endurance and\ncapacity to hold for a determined amount of time."
                + System.lineSeparator() + System.lineSeparator());
    }
}
