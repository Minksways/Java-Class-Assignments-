/* Michael (Minks) Cortes Muniz
This is a testing phase of making sure the main logic of the writing code works, this code is meant
to be a way for adding new information into a text file for now (Eventually into a Database).
It reads two text files Characters and Characters_BatchTester
Methods of adding in information, reading, deleting and updating entries are all within this code, and
lets go over all sections as best as possible.
* */

// Only change from here was Int to String for CharacterID due to issues with incrementing.
public class Characters {
    private String CharacterID;
    private boolean CurrentState;
    private String Location;
    private boolean ImportanceState;
    private String CharacterDescription;


    // Constructor with everything inside.
    public Characters(String CharacterID, boolean CurrentState, String Location,
               boolean ImportanceState, String CharacterDescription) {
        this.CharacterID = CharacterID;
        this.CurrentState = CurrentState;
        this.Location = Location;
        this.ImportanceState = ImportanceState;
        this.CharacterDescription = CharacterDescription;
    }

    // getter methods for the main controller of the project.
    public String getCharacterID()
    { return CharacterID; }

    public boolean getCurrentState()
    { return CurrentState; }

    public String getLocation()
    { return Location; }

    public boolean getImportanceState()
    { return ImportanceState; }

    public String getCharacterDescription()
    { return CharacterDescription; }
}
