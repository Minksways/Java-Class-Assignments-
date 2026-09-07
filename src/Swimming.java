//This is the Swimming Class used specifically to get the SWOLF Score for swimming.
public class Swimming {
    int lapTime;
    int strokeCount;
    int SWOLFScore;

//These are the set and get methods, I did use generate... option for these to take less time typing them.
    public int getLapTime() {
        return lapTime;
    }

    public void setLapTime(int lapTime) {
        this.lapTime = lapTime;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }
    public int getSWOLFScore() {
        return this.lapTime + this.strokeCount;
    }

    public void setSWOLFScore(int SWOLFScore) {
        this.SWOLFScore = SWOLFScore;
    }
//This is the construct and the overloaded construct (I did not use the overloaded construct right I believe)
//containing the all strings to be used by the instantiation for Swimming.
    public Swimming(int lapTime, int strokeCount, int SWOLFScore) {
        this.lapTime = lapTime;
        this.strokeCount = strokeCount;
        this.SWOLFScore = lapTime + strokeCount;

    }
    public Swimming() {
        this(0, 0, 0);
    }
//This is the print method used in the instantiation.
    public void SwimmingDataResults() {
        System.out.println("The Lap Time and amount of Strokes done were: " +
                lapTime + " Seconds and a stroke count of " + strokeCount + System.lineSeparator() +
                "This then gives a SWOLFScore of: " + SWOLFScore + System.lineSeparator());
    }
}
