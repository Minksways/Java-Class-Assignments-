//This class is used for the measurements of certain types of climbing feats for individuals.
public class Climbing {
    String fingerStrength;
    String relativeStrength;
    String benchmarks;

//These are the set and get methods, I did use generate... option for these to take less time typing them.
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
//This is the construct and the overloaded construct (I did not use the overloaded construct right I believe)
//containing the all strings to be used by the instantiation for Climbing.
    public Climbing(String fingerStrength, String relativeStrength, String Benchmarks) {
        this.fingerStrength = fingerStrength;
        this.relativeStrength = relativeStrength;
        this.benchmarks = Benchmarks;
    }
    public Climbing() {
            this(null, null, null);
    }
//This is the print method used in the instantiation.
    public void ClimbingScoringTypes() {
        System.out.println("These are different ways of just measurements for small aspects of " +
                "climbing but not quite for\nactual competative reasons, these being:\n" + fingerStrength +
                " used for things like hang test, or peak force measurments;\n" + relativeStrength + " is used to " +
                "get a better idea of one's ability to sustain themselves,\nand then we have " + benchmarks +
                " which is an understanding of one's endurance and\ncapacity to hold for a determined amount of time."
        + System.lineSeparator() + System.lineSeparator());
    }
}
