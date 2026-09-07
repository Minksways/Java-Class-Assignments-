//This is the Swimming Class used specifically to get the SWOLF Score for swimming.
public class Swimming {
    int lapTime;
    int strokeCount;
    int SWOLFScore;


    public int getLapTime() { return lapTime; }

    public void setLapTime(int lapTime) { this.lapTime = lapTime; }

    public int getStrokeCount() { return strokeCount; }

    public void setStrokeCount(int strokeCount) { this.strokeCount = strokeCount; }


    public int getSWOLFScore() { return this.lapTime + this.strokeCount; }

    public void setSWOLFScore(int SWOLFScore) {
        this.SWOLFScore = SWOLFScore;
    }


    public Swimming(int lapTime, int strokeCount, int SWOLFScore) {
        this.lapTime = lapTime;
        this.strokeCount = strokeCount;
        this.SWOLFScore = lapTime + strokeCount;
    }

    public Swimming() { this(0, 0, 0); }


    public void SwimmingDataResults() {
        System.out.println("The Lap Time and amount of Strokes done were: " +
                lapTime + " Seconds and a stroke count of " + strokeCount + System.lineSeparator() +
                "This then gives a SWOLFScore of: " + SWOLFScore + System.lineSeparator());
    }
}
