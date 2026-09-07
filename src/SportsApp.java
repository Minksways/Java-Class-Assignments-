import java.util.Scanner;
/**Michael (Minks) Cortes Muniz, COP-3330C, 6/14/2026
 * The objective of this program is to have two classes be used to create as a reference for objects
 * to be called for and have it printed in the output, then update at least one attribute.
 * It must contain Two classes, Constructors and overloaded constructor with zeros or null,
 * a get and set methods, print methods for each class.
 **/
public class SportsApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SPORT APP INTERACTIVE LOG ===" + System.lineSeparator());


        System.out.print("Enter initial Swim Lap Time: ");
        int initLap = scanner.nextInt();
        System.out.print("Enter initial Swim Stroke Count: ");
        int initStrokes = scanner.nextInt();
        scanner.nextLine(); // Clear buffer


        Swimming SwimmingExerciseRecent = new Swimming(initLap, initStrokes, 0);


        SwimmingExerciseRecent.SwimmingDataResults();


        System.out.print("Enter updated Swim Lap Time: ");
        int newLap = scanner.nextInt();
        System.out.print("Enter updated SWOLF Score math value manually: ");
        int newSwolf = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        SwimmingExerciseRecent.setLapTime(newLap);
        SwimmingExerciseRecent.setSWOLFScore(newSwolf);


        SwimmingExerciseRecent.SwimmingDataResults();

        System.out.println("------------------------------------" + System.lineSeparator());


        System.out.print("Enter Finger Exercise Description: ");
        String finger = scanner.nextLine();
        System.out.print("Enter Relative Strength Exercise Description: ");
        String relative = scanner.nextLine();
        System.out.print("Enter Benchmarks Exercise Description: ");
        String benchmarks = scanner.nextLine();


        Climbing ClimbingScoringNames = new Climbing(finger, relative, benchmarks);


        ClimbingScoringNames.ClimbingScoringTypes();


        System.out.print("Enter updated Benchmarks Exercise Description: ");
        String newBenchmarks = scanner.nextLine();

        ClimbingScoringNames.setBenchmarks(newBenchmarks);


        ClimbingScoringNames.ClimbingScoringTypes();


        ClimbingScoringNames.sendRouteLog();

        scanner.close();
    }
}
