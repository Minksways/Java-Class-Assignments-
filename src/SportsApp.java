//Michael (Minks) Cortes Muniz, COP-3330C, 6/14/2026 1:24PM
//The objective of this program is to have two classes be used to create as a reference for objects to be called for
// and have it printed in the output, then update at least one attribute. It must contain Two classes, Constructors and
// overloaded constructor with zeros or null, a get and set methods, print methods for each class.
//
public class SportsApp {

    public static void main(String[] args) {
    Swimming SwimmingExerciseRecent = new Swimming(23,
            28, 0);

    SwimmingExerciseRecent.SwimmingDataResults(); //First output
    SwimmingExerciseRecent.setLapTime(13); //Updated the setter to then get the new output
    SwimmingExerciseRecent.setSWOLFScore(41); //Updated the setter to then get the new output
    SwimmingExerciseRecent.SwimmingDataResults();//Updated output
    System.out.println("------------------------------------" + System.lineSeparator());
//I tried making the instantiation use the constructors to dynamically update the SWOLFScore but could not.
//Will have to definitely do more time for this next time to get it right. I have redundant code here as well.

    Climbing ClimbingScoringNames = new Climbing("Finger Strength", "Relative Strength",
                "BENCHMARKS" );

        ClimbingScoringNames.ClimbingScoringTypes(); //First output
        ClimbingScoringNames.setBenchmarks("Benchmarks"); //Updated the setter to then get the new output
        ClimbingScoringNames.ClimbingScoringTypes(); //Updated output
    }
}
