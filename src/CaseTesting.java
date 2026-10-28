import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/* Testing section for running automate failing and passing states
 * to simulate database states and verify both affirmative and negative pass requirements.
 * */
public class CaseTesting {

    private MainController controller;
    private List<Characters> sampleDatabase;
    /* baselineSize tracks the total records inside your authentic project file
    to make sure tests work correctly. */
    private int baselineSize;

    @BeforeEach
    public void setUp() {
        controller = new MainController();

        // Reads from your true
        sampleDatabase = controller.ReadAllFromFile("Characters");

        // Baseline size on disk to have a way to store the tests
        baselineSize = sampleDatabase.size();
    }

    // Read Operation
    @Test
    public void testLoadDatabase_NonExistentFile() {
        /* NEGATIVE TESTING: Verifies that if an operator feeds a completely missing or
        bad path file name string, the code does not crash and outputs an empty array list. */
        List<Characters> list = controller.ReadAllFromFile("MissingFileTest.txt");
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    // Create Operation
    @Test
    public void testAddCharacter_Success() {
        /* AFFIRMATIVE TESTING INPUT: Verifies that inserting a row successfully adds
        to memory array and increments score metric by  0.1 */
        boolean result = controller.addCharacter(sampleDatabase, "9.9", true,
                "King's Landing", false, "Temporary Test Guard");
        assertTrue(result);
        assertEquals(baselineSize + 1, sampleDatabase.size());
    }

    @Test
    public void testAddCharacter_Fail_MissingAttributes() {
        /* NEGATIVE TESTING INPUT: Verifies boundary filters working as intended. */
        boolean result = controller.addCharacter(sampleDatabase, "", true,
                "", false, null);
        assertFalse(result);
        assertEquals(baselineSize, sampleDatabase.size());
    }

    // Delete Operation
    @Test
    public void testDeleteCharacter_Success() {
        /* AFFIRMATIVE TESTING INPUT: Generates temporary object to calculating
        correct size upon complete removal. */
        List<Characters> tempStorage = new ArrayList<>(sampleDatabase);
        tempStorage.add(new Characters("9999", false,
                "Braavos", true, "Temporary Drop Target"));
        int currentSize = tempStorage.size();

        boolean result = controller.deleteCharacter("9999", tempStorage);
        assertTrue(result);
        assertEquals(currentSize - 1, tempStorage.size());
    }

    @Test
    public void testDeleteCharacter_Fail_NonExistentID() {
        /* NEGATIVE TESTING INPUT: Verifies that targeting an ID key that does
        not exist on disk is safely handled. */
        boolean result = controller.deleteCharacter("FakeNonExistentIDString_999", sampleDatabase);
        assertFalse(result);
        assertEquals(baselineSize, sampleDatabase.size());
    }

    // Update Operation
    @Test
    public void testUpdateCharacter_Success() {
        /* AFFIRMATIVE TESTING INPUT: Verifies that committing final review modifications routes
        the elements straight into an updated subversion elements such as 1.10 . */
        boolean result = controller.updateCharacter(sampleDatabase, "1.9", "false",
                "Castle Black", "true", "Temporary Update Test Node");
        assertTrue(result);
        assertEquals(baselineSize + 1, sampleDatabase.size());
    }

    // Increment Engine & Batch Parsing
    @Test
    public void testDuplicateValidation_FreeSlot() {
        /* AFFIRMATIVE TESTING INPUT: Confirms that feeding an entirely unoccupied entry like 99
        safely uses custom gap-filling rules if there is an available space it registers  */
        String ID = "9.1";
        String resolvedID = controller.IsDuplicate(ID, sampleDatabase);

            // VALIDATOR: Validates that the output matches a valid numeric sequence
            assertTrue(resolvedID.matches("^[0-9]+(\\.[0-9]+)?$"));

            // Double check that your engine didn't mutate or cut down your massive input string
            assertEquals(ID, resolvedID);
        }


    @Test
    public void testParseBatchLine_Success() {
        /* AFFIRMATIVE TESTING INPUT: Batch parser splits columns exactly at comma targets
        and prints a string entry. */
        boolean result = controller.parseBatchLine("99.7,true,Dorne,false,Martell Spearman", sampleDatabase);
        assertTrue(result);
    }

    @Test
    public void testParseBatchLine_Fail_MalformedRow() {
        /* NEGATIVE TESTING INPUT: Ensures structural line verify corrupted boundary data overloads.
        * Can be missing or have extra */
        boolean result = controller.parseBatchLine("99.6,true,Testing,false,Guard, ExtraCommaText", sampleDatabase);
        assertFalse(result);
    }
}
