/* These are all of our imports, including from file reading/writing, to user inputs,
arrays, arraysLists, and exception error handling
* */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/* We have our beginning section for reading the downloads folder of a users computer containing the
 * Characters.txt text file and running function to allow the program to keep looping/running
 * as long as it is true.*/
public class MainController {

    // Reads Characters.txt
    private final String TextFileName = "Characters";

    private final Scanner scanner;

    // isRunning is used to keep a true state for the program running and displaying.
    private boolean isRunning;
    /* IDExists keeps track if a current entry exists in the file
    to then increment it with custom function */
    private boolean IDExists = false;

    public MainController() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }
    // Main Menu display for all choices and submenu buttons
    private void DisplayMenu() {
        System.out.println("\n=========================================================");
        System.out.println("===== CRUD Operations Testing in IDE Console or CLI =====");
        System.out.println("=========================================================");
        System.out.println("\n1. Create Character Entries");
        System.out.println("2. Read Character Entries");
        System.out.println("3. Update/Delete Character Entries");
        System.out.println("4. Exit Program");
        System.out.println("\n=========================================================");
    }

    // User Choice Inputs Handler to have an area to type and go to next line.
    private int UserChoice() {
        System.out.print("Type here: ");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Choices for Main Menu
    private void MenuChoiceOutcomes(int choice) {
        switch (choice) {
            case 1:
                SubMenuCreate();
                break;
            case 2:
                SubMenuRead();
                break;
            case 3:
                SubMenuUpdateDelete();
                break;
            case 4:
                // Exits the program
                System.out.println("\nExiting Program,,, ");
                this.isRunning = false;
                break;
            default:
                System.out.println("Invalid choice, try again (1-4)");
        }
    }


    // Sub Menu display for adding/creating an entry
    private void SubMenuCreate() {
        boolean DisplaySubMenu = true;

        while (DisplaySubMenu) {
            System.out.println("\n==============================");
            System.out.println("===== Batching or Manual =====");
            System.out.println("==============================");
            System.out.println("1. Manual Create Character Entry");
            System.out.println("2. Batch Upload Character Entry");
            System.out.println("3. Go Back to Main Menu");
            System.out.println("4. Exit Program");
            System.out.println("\n==============================");

            // This submenu choices
            int SubMenuChoiceOutcomes = UserChoice();

            switch (SubMenuChoiceOutcomes) {
                case 1:
                    // Manual entry
                    CharacterEntry();
                    break;
                case 2:
                    // Batch uploading with Characters_BatchTester
                    CharacterEntryBatch();
                    break;
                case 3:
                    // Exits the submenus
                    System.out.println("Returning to Main Menu");
                    DisplaySubMenu = false;
                    break;
                case 4:
                    System.out.println("\nExiting Program,,, ");
                    DisplaySubMenu = false;
                    this.isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again (1-4)");
            }
        }
    }


    // Sub Menu Display for reading entries
    private void SubMenuRead() {
        boolean DisplayReadMenu = true;

        while (DisplayReadMenu) {
            System.out.println("\n=====================================");
            System.out.println("===== ReadAll Or ReadID Options =====");
            System.out.println("=====================================");
            System.out.println("1. Display Entire Character List");
            System.out.println("2. Display by Character ID");
            System.out.println("3. Go Back to Main Menu");
            System.out.println("4. Exit Program");
            System.out.println("\n===================================");

            int SubMenuChoiceOutcomes = UserChoice();

            switch (SubMenuChoiceOutcomes) {
                case 1:
                    //Displays all current entries in Characters
                    DisplayAllCharacters();
                    break;
                case 2:
                    // Displays an entry via ID in Characters
                    DisplayCharacterByID();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu");
                    DisplayReadMenu = false;
                    break;
                case 4:
                    System.out.println("\nExiting Program,,, ");
                    DisplayReadMenu = false;
                    this.isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again (1-4)");
            }
        }
    }


    // Sub Menu display for both updating and deleting
    private void SubMenuUpdateDelete() {
        boolean DisplayUpdateDeleteMenu = true;

        while (DisplayUpdateDeleteMenu) {
            System.out.println("\n===================================");
            System.out.println("===== Update / Delete Options =====");
            System.out.println("===================================");
            System.out.println("1. Update a Character via ID");
            System.out.println("2. Delete a Character via ID");
            System.out.println("3. Go Back to Main Menu");
            System.out.println("4. Exit Program");
            System.out.println("\n=================================");

            int SubMenuChoiceOutcomes = UserChoice();

            switch (SubMenuChoiceOutcomes) {
                case 1:
                    // Updates an entry via ID and lets you update each section in the row
                    UpdateEntryID();
                    break;
                case 2:
                    // Removes an entry via ID
                    RemoveEntryID();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu");
                    DisplayUpdateDeleteMenu = false;
                    break;
                case 4:
                    System.out.println("\nExiting Program,,, ");
                    DisplayUpdateDeleteMenu = false;
                    this.isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again (1-4)");
            }
        }
    }

    private void CharacterEntry() {
        System.out.println("\n--- Manual Create Character Entry ---");
        List<Characters> allCharacters = ReadAllFromFile();
        String CharacterIDInput;
        boolean CurrentState;
        String LocationInput;
        boolean ImportanceState;
        String DescriptionInput;

        System.out.println("(Type 'cancel' or 'cm' at any prompt to abort the operation completely)");

        // Makes sure Null ID does not pass during manual entry
        while (true) {
            System.out.print("Enter Character ID (1 or 1.0): ");
            CharacterIDInput = scanner.nextLine().trim();


            if (CharacterIDInput.equalsIgnoreCase("cancel")
                    || CharacterIDInput.equalsIgnoreCase("cm")) {
                System.out.println("Manual character entry operation completely canceled.");
                return;
            }
            if (CharacterIDInput.isEmpty()) {
                System.out.println("[ERROR] ID cannot be empty. Please try again.");
                continue;
            }
            // Allows for avoiding negative numbers and words, only valid Integers even as a String
            if (!CharacterIDInput.matches("^[0-9]+(\\.[0-9]+)?$")) {
                System.out.println("[ERROR] Invalid ID, no words or negative numbers. Please try again.");
                continue;
            }
            break;
        }

        // Processed ID gets displayed in a message
        IDExists = false;
        String verifiedID = IsDuplicate(CharacterIDInput, allCharacters);
        if (!verifiedID.equals(CharacterIDInput)) {
            System.out.println("ID '" + CharacterIDInput +
                    "' already exists. incrementing to next available increment: " + verifiedID);
        }

        //Makes sure only true or false get passed and displayed
        while (true) {
            System.out.print("Enter Current State (True/False or T/F): ");
            String input = scanner.nextLine().trim();


            if (input.equalsIgnoreCase("cancel")
                    || input.equalsIgnoreCase("cm")) {
                System.out.println("[INFO] Manual character entry operation completely canceled.");
                return;
            }

            if (!input.equalsIgnoreCase("true")
                    && !input.equalsIgnoreCase("false")
                    && !input.equalsIgnoreCase("t")
                    && !input.equalsIgnoreCase("f")) {
                System.out.println("[ERROR] Invalid State. Input must be exactly True, False, T, or F.");
                continue;
            }
            CurrentState = input.equalsIgnoreCase("true")
                    || input.equalsIgnoreCase("t");
            break;
        }

        // Allows for String to be added of any kind, this is closer to flavor text
        while (true) {
            System.out.print("Enter Location (String): ");
            LocationInput = scanner.nextLine().trim();


            if (LocationInput.equalsIgnoreCase("cancel")
                    || LocationInput.equalsIgnoreCase("cm")) {
                System.out.println("[INFO] Manual character entry operation completely canceled.");
                return;
            }
            if (LocationInput.isEmpty()) {
                System.out.println("[ERROR] Empty Location. Please try again.");
                continue;
            }
            break;
        }

        // True or False displayer
        while (true) {
            System.out.print("Enter ImportanceState (True/False or T/F): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("cancel")
                    || input.equalsIgnoreCase("cm")) {
                System.out.println("[INFO] Manual character entry operation completely canceled.");
                return;
            }
            if (!input.equalsIgnoreCase("true")
                    && !input.equalsIgnoreCase("false")
                    && !input.equalsIgnoreCase("t")
                    && !input.equalsIgnoreCase("f")) {
                System.out.println("[ERROR] Invalid State. Input must be exactly True, False, T, or F.");
                continue;
            }
            ImportanceState = input.equalsIgnoreCase("true") || input.equalsIgnoreCase("t");
            break;
        }

        /* Displayer for flavor text of this character, name and other important details
         * such if they were important, for what reason and if they are alive in what condition and such.*/
        while (true) {
            System.out.print("Enter Character Description (String): ");
            DescriptionInput = scanner.nextLine().trim();

            if (DescriptionInput.equalsIgnoreCase("cancel")
                    || DescriptionInput.equalsIgnoreCase("cm")) {
                System.out.println("Manual character entry operation canceled.");
                return;
            }
            if (DescriptionInput.isEmpty()) {
                System.out.println("[ERROR] Empty Character Description. Please try again.");
                continue;
            }
            break;
        }

        // Creates the new entry or Incremented entry to then be added into the text file
        boolean success = addCharacter(allCharacters, verifiedID, CurrentState,
                LocationInput, ImportanceState, DescriptionInput);
        if (success) {
            System.out.print("[Success] Created Character Entry into '" + TextFileName + "' file\n");
        }
    }
    // batch uploading using the Characters_BatchTester file
    private void CharacterEntryBatch() {
        System.out.println("\n--- Batch Upload Character Entry ---");
        System.out.print("Enter path to Text file: ");
        String filePathInput = scanner.nextLine().trim();

        // Strip out any quotation marks
        filePathInput = filePathInput.replace("\"", "");
        String finalPath = filePathInput;

        List<Characters> allCharacters = ReadAllFromFile();
        int importedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(finalPath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                boolean lineParsed = parseBatchLine(line, allCharacters);
                if (lineParsed) {
                    importedCount++;
                } else {
                    System.out.println("[ERROR] Line " + lineNumber +
                            ": Contains invalid formatting or missing metrics. Skipping.");
                }
            }

            // Success in batch upload, or if none went through will print zero valid
            if (importedCount > 0) {
                WriteAllToFile(allCharacters);
                System.out.println("[SUCCESS] Successfully processed and ordered " +
                        importedCount + " records.");
            } else {
                System.out.println("[ERROR] Zero valid characters parsed from your file.");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Cannot read or locate target file path: " + finalPath);
        }
    }
    // Entry ID Updater by elements in row
    private void UpdateEntryID() {
        System.out.println("\n--- Update Existing Character ---");
        System.out.println("Enter base Character ID to update (1 or 1.1) ");
        System.out.print("Type here: ");
        String targetID = scanner.nextLine().trim();


        List<Characters> allCharacters = ReadAllFromFile();
        IDExists = false;

        String nextVersionID = IsDuplicate(targetID, allCharacters);

        // Loops back if an entry entered does not exist
        if (!IDExists) {
            System.out.println("No character entry matching ID '" + targetID + "' was found.");
            return;
        }

        // Successful entry found, allows for updating now. Aborts if wrong info is typed
        System.out.println("\n[Record Found]. Creating incremental update branch version ID | " + nextVersionID);
        System.out.println("\n--- Enter New Configurations details below ---");

        System.out.print("Enter Current State (True or False) ");
        System.out.print("\nType here: ");
        String newState = scanner.nextLine().trim().toLowerCase();
        if (!newState.equals("true") && !newState.equals("false")) {
            System.out.println("[ERROR] Invalid State. Update aborted.");
            return;
        }

        System.out.print("\nEnter Location (String) ");
        System.out.print("\nType here: ");
        String newLoc = scanner.nextLine().trim();
        if (newLoc.isEmpty()) {
            System.out.println("[ERROR] Empty Location. Update aborted.");
            return;
        }

        System.out.print("\nEnter ImportanceState (True or False) ");
        System.out.print("\nType here: ");
        String newImp = scanner.nextLine().trim().toLowerCase();
        if (!newImp.equals("true") && !newImp.equals("false")) {
            System.out.println("[ERROR] Invalid State. Update aborted.");
            return;
        }

        System.out.print("\nEnter Character Description (String) ");
        System.out.print("\nType here: ");
        String newDesc = scanner.nextLine().trim();
        if (newDesc.isEmpty()) {
            System.out.println("[ERROR] Empty Character Description. Update aborted.");
            return;
        }

        boolean success = updateCharacter(allCharacters, nextVersionID, newState,
                newLoc, newImp, newDesc);
        if (success) {
            System.out.println("[SUCCESS] Created updated sub-version character entry '"
                    + nextVersionID + "'.");
        }
    }

    // Remove via ID display
    private void RemoveEntryID() {
        System.out.println("\n--- Delete Character Entry ---");
        System.out.print("Enter Character ID to remove [or type 'cancel'/'cm' to abort]: ");
        System.out.print("Type here: ");
        String targetID = scanner.nextLine().trim();

        if (targetID.equalsIgnoreCase("cancel")
                || targetID.equalsIgnoreCase("cm")
                || targetID.isEmpty()) {
            System.out.println("Deletion operation aborted.");
            return;
        }

        List<Characters> allCharacters = ReadAllFromFile();

        boolean removed = deleteCharacter(targetID, allCharacters);
        if (removed) {
            System.out.println("[SUCCESS] Character ID '" + targetID + "' removed from text file storage.");
        } else {
            System.out.println("No character entry matching ID '" + targetID + "' was found.");
        }
    }

    private void DisplayAllCharacters() {
        System.out.println("\n--- Displaying Entire Character Entries in file ---");
        List<Characters> allCharacters = ReadAllFromFile();

        int count = 0;
        for (Characters ch : allCharacters) {
            count++;
            System.out.println("\n-----------------------------------------");
            System.out.println("Character Entry #" + count);
            System.out.println("-----------------------------------------");
            System.out.println("Character ID: " + ch.getCharacterID());
            System.out.println("Current State: " + ch.getCurrentState());
            System.out.println("Location: " + ch.getLocation());
            System.out.println("Importance State: " + ch.getImportanceState());
            System.out.println("Description: " + ch.getCharacterDescription() + "\n");
        }

        if (count == 0) {
            System.out.println(" The text file is currently empty.");
        }
    }

    private void DisplayCharacterByID() {
        System.out.println("\n--- Search Character By ID ---");
        System.out.print("Enter Character ID to search: ");
        System.out.print("Type here: ");
        String targetID = scanner.nextLine().trim();

        List<Characters> allCharacters = ReadAllFromFile();
        boolean recordFound = false;

        for (Characters ch : allCharacters) {
            if (ch.getCharacterID().equals(targetID)) {
                System.out.println("\n--- [ID MATCH FOUND] ---");
                System.out.println("Character ID: " + ch.getCharacterID());
                System.out.println("Current State: " + ch.getCurrentState());
                System.out.println("Location: " + ch.getLocation());
                System.out.println("Importance State: " + ch.getImportanceState());
                System.out.println("Description: " + ch.getCharacterDescription());
                recordFound = true;
            }
        }

        if (!recordFound) {
            System.out.println("No character entry matching ID '" + targetID + "' was found.");
        }
    }


    public boolean addCharacter(List<Characters> currentList, String id,
                                boolean state, String loc, boolean imp, String desc) {
        if (id == null || id.isEmpty() || loc == null
                || loc.isEmpty() || desc == null || desc.isEmpty()) {
            return false;
        }
        boolean success = currentList.add(new Characters(id, state, loc, imp, desc));
        if (success && currentList == ReadAllFromFile()) {
            WriteAllToFile(currentList);
        }
        return success;

    }

    public boolean deleteCharacter(String targetID, List<Characters> currentList) {
        if (targetID == null || targetID.isEmpty()) return false;
        boolean wasRemoved = currentList.removeIf(ch -> ch.getCharacterID().trim().equalsIgnoreCase(targetID.trim()));
        if (wasRemoved) {
            WriteAllToFile(currentList);
        }
        return wasRemoved;
    }

    public boolean updateCharacter(List<Characters> currentList, String nextVersionID, String state, String loc, String imp, String desc) {
        boolean success = currentList.add(new Characters(nextVersionID, Boolean.parseBoolean(state), loc, Boolean.parseBoolean(imp), desc));
        if (success && currentList == ReadAllFromFile()) {
            WriteAllToFile(currentList);
        }
        return success;
    }

    /* The custom method for incrementing ID inputs that exists and increments to the next
    available ID (1 + .1)
    */

    public String IsDuplicate(String targetID, List<Characters> existingList) {
        boolean exactMatchFound = false;
        int HighestSubVersionInt = 0;

        String BaseID = targetID.contains(".") ? targetID.split("\\.")[0] : targetID;

        // Check if the EXACT requested ID (like 1.6) is already taken
        for (Characters ch : existingList) {
            if (ch.getCharacterID().trim().equalsIgnoreCase(targetID.trim())) {
                exactMatchFound = true;
                break;
            }
        }


        if (!exactMatchFound) {
            IDExists = false;
            return targetID;
        }

        IDExists = true;
        for (Characters ch : existingList) {
            String currentID = ch.getCharacterID().trim();
            if (currentID.startsWith(BaseID + ".")) {
                try {
                    String subStr = currentID.split("\\.")[1];
                    int subVersionInt = Integer.parseInt(subStr);
                    if (subVersionInt > HighestSubVersionInt) {
                        HighestSubVersionInt = subVersionInt;
                    }
                } catch (Exception ignored) {}
            }
        }

        int nextSubVersion = HighestSubVersionInt + 1;
        return BaseID + "." + nextSubVersion;
    }


    public boolean parseBatchLine(String rawLine, List<Characters> currentList) {
        if (rawLine == null || rawLine.trim().isEmpty()) return false;
        String[] data = rawLine.split(",", -1);
        if (data.length != 5) return false;

        for (String element : data) {
            if (element.trim().isEmpty()) return false;
        }

        try {
            String CharacterID = data[0].trim();

            String CurrentState = data[1].trim().toLowerCase();
            if (!CurrentState.equals("true")
                    && !CurrentState.equals("false")
                    && !CurrentState.equals("t")
                    && !CurrentState.equals("f")) return false;
            boolean currentState = CurrentState.equals("true") || CurrentState.equals("t");

            String Location = data[2].trim();


            String ImportanceState = data[3].trim().toLowerCase();
            if (!ImportanceState.equals("true")
                    && !ImportanceState.equals("false")
                    && !ImportanceState.equals("t")
                    && !ImportanceState.equals("f")) return false;
            boolean importanceState = ImportanceState.equals("true") || ImportanceState.equals("t");

            String Description = data[4].trim();

            String verifiedID = IsDuplicate(CharacterID, currentList);
            return currentList.add(new Characters(verifiedID, currentState, Location, importanceState, Description));
        } catch (Exception e) {
            return false;
        }
    }
    private List<Characters> ReadAllFromFile() {
        return ReadAllFromFile(TextFileName);
    }


    public List<Characters> ReadAllFromFile(String customFilePath) {
        List<Characters> list = new ArrayList<>();
        java.io.File file = new java.io.File(customFilePath);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(customFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",", -1);
                if (data.length >= 5) {
                    list.add(new Characters(
                            data[0].trim(),
                            Boolean.parseBoolean(data[1].trim()),
                            data[2].trim(),
                            Boolean.parseBoolean(data[3].trim()),
                            data[4].trim()
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] File reading failed: " + e.getMessage());
        }
        return list;
    }

    private void WriteAllToFile(List<Characters> list) {
        list.sort((c1, c2) -> {
            try {
                // Split IDs by the dot character
                String[] parts1 = c1.getCharacterID().split("\\.");
                String[] parts2 = c2.getCharacterID().split("\\.");

                int base1 = Integer.parseInt(parts1[0]);
                int base2 = Integer.parseInt(parts2[0]);

                if (base1 != base2) {
                    return Integer.compare(base1, base2);
                }

                // If IDs are identical, compares to see which number it will increment to
                int sub1 = parts1.length > 1 ? Integer.parseInt(parts1[1]) : 0;
                int sub2 = parts2.length > 1 ? Integer.parseInt(parts2[1]) : 0;
                return Integer.compare(sub1, sub2);
            } catch (NumberFormatException e) {
                return c1.getCharacterID().compareTo(c2.getCharacterID());
            }
        });

        // How it is added into the text tile
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TextFileName, false))) {
            for (Characters ch : list) {
                String csvRow = String.format("%s, %b, %s, %b, %s",
                        ch.getCharacterID(),
                        ch.getCurrentState(),
                        ch.getLocation(),
                        ch.getImportanceState(),
                        ch.getCharacterDescription()
                );
                bw.write(csvRow);
                bw.newLine();
            }
            // Error for if failure to add was found
        } catch (IOException e) {
            System.out.println("[ERROR] File writing failed: " + e.getMessage());
        }
    }

    // Starts the program loop when called
    public void start() {
        while (isRunning) {
            DisplayMenu();
            int choice = UserChoice();
            MenuChoiceOutcomes(choice);
        }
    }
}
