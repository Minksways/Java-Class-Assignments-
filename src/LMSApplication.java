/* Michael (Minks) Cortes Muniz
* Java Assigment for Software Development CEN 3024
* LMS Application that can run on computer and updates a .txt file that contains all records of people
* money to the library from 0 to 250 dollars
* Will be using while loops, if statements, user inputs, and using the computers hard drive to save
* .txt file*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Main Run Application for users to commence usage
public class LMSApplication {
    private static final List<Patron> PatronList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static String lastUsedFileName = "patron.txt";

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("--- Library Tracker ---");

        // Starts the while Tracker for adding and seeing information inside the Patron.txt
        while (running) {
            printMenu();
            // Shows us only 1 - 6 is accepted
            System.out.print("Enter 1 - 6 to choose option: ");
            String inputs = scanner.nextLine().trim();


            switch (inputs) {
                // Is the User input in Charge of bulk loading the 4 parameter set up from .txt files
                case "1":
                    addTextFilePatron();
                    break;
                // Will commence manual entry of each 4 parameters inside of output or CLI
                case "2":
                    addManuallyPatron();
                    break;
                /* Remove individual Patrons who have pay or aren't needed in the system anymore via
                * the 7-digit ID*/
                case "3":
                    removeIDPatron();
                    break;
                // Display a single Patron via the ID 7-digit ID
                case "4":
                    displayIDPatron();
                    break;
                // Displays ALL the current profiles of the Patrons
                case "5":
                    displayPatrons();
                    break;
                // Stops the program and saves the .txt file into the hard drive and project
                case "6":
                    System.out.println("\nSaving updated .txt file to computer drive...");
                    saveTextFilePatron();
                    System.out.println("Shutting down terminal application.");
                    running = false;
                    break;

                // Will print if an input other than 1 - 6 is found
                default:
                    System.out.println("Invalid input choice. " +
                            "Please enter a option number from 1 to 6.");
            }
        }
    }

    // Interface for telling the user what each number will do and also displays a menu like location
    private static void printMenu() {
        System.out.println(" --------------------------------");
        System.out.println("1. File.txt or Path loader");
        System.out.println("2. Manual Entry into CLI");
        System.out.println("3. Remove patron via 7 digit ID");
        System.out.println("4. Look up Patron via 7 digit ID");
        System.out.println("5. Show all Patrons in CLI");
        System.out.println("6. Exit CLI");
        System.out.println(" --------------------------------");
    }

    //  Method for if there is a duplicate ID attempted to be added
    private static boolean isIDDuplicate(String ID) {
        for (Patron p : PatronList) {
            if (p.getID().equalsIgnoreCase(ID)) {
                return true;
            }
        }
        return false;
    }

    // Method for adding in .txt file via bulk load
    private static void addTextFilePatron() {
        // Must be a path on computer with the .txt file name
        System.out.print("Enter .txt file path " +
                "( ex: \"C:\\Users\\username\\Downloads\\patron.txt\" ): ");

        String fileName = scanner.nextLine().trim();

        lastUsedFileName = fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String rowLine;
            int uploadedCount = 0;

            while ((rowLine = reader.readLine()) != null) {
                if (rowLine.trim().isEmpty()) continue;

                // Parameters for the dash delimiter between each ID, Strings, and Numbers
                String processedLine = rowLine.replace("--", "-~");
                String[] tokens = processedLine.split("-");

                if (tokens.length == 4) {
                    String extractedID = tokens[0].trim();
                    String extractedName = tokens[1].trim();
                    String extractedAddress = tokens[2].trim();
                    String balanceStr = tokens[3].trim().replace("~", "-");

                    // Will reject the $ Symbol for numbers and print to User
                    if (balanceStr.contains("$")) {
                        System.out.println("Row skipped: Raw text contains a currency symbol ($). " +
                                "| Correct Format example: 45.50");
                        continue;
                    }

                    // Will reject a duplicate 7-digit ID and print to User
                    if (isIDDuplicate(extractedID)) {
                        System.out.println("Row skipped: Duplicate ID. Patron ID already in use: " +
                                extractedID);
                        continue;
                    }

                    try {
                        double parsedBalance = Double.parseDouble(balanceStr);

                        // If the number is below zero will reject as a negative number
                        if (parsedBalance < 0.0) {
                            System.out.println("Row skipped: Balance cannot be a negative amount. " +
                                    "Provided: " + parsedBalance);
                            continue;
                        }

                        // If above 250 will reject as a number too big
                        if (parsedBalance > 250.0) {
                            System.out.println("Row skipped: Balance exceeds maximum cap limit of " +
                                    "$250.00. Provided: " + parsedBalance);
                            continue;
                        }

                        // Adds all raw text and gives a number upload in Output
                        PatronList.add(new Patron(extractedID, extractedName, extractedAddress,
                                parsedBalance));
                        uploadedCount++;

                    // Will reject any type of strings in the number section as an invalid input
                    } catch (NumberFormatException e) {
                        System.out.println("Row skipped: Balance field is not a valid number input.");
                    // Will reject an ID too big or too small
                    } catch (IllegalArgumentException e) {
                        System.out.println("Row skipped due to ID error: " +
                                e.getMessage());
                    }
                }
            }
            // File added via bulk load successfully message
            System.out.println("\n[Success] " + uploadedCount +
                    " patron file have been added.");
        // File path could not be read due to incorrectly type
        } catch (IOException e) {
            System.out.println("\nFile system read block exception: " +
                    "Target path could not be located or opened.");
        }
    }
    // Will save the uploaded file to the computer
    private static void saveTextFilePatron() {
        try (java.io.BufferedWriter writer =
                     new java.io.BufferedWriter(new java.io.FileWriter(lastUsedFileName))) {
            for (Patron p : PatronList) {
                // Formats each object perfectly back into the original flat-file dash structure
                writer.write(p.toString());
                writer.newLine();
            }
            // Successful upload message
            System.out.println("[Data Synced] All active CLI records safely written to: " +
                    lastUsedFileName);
        } catch (IOException e) {
            // Could not be added back to original file due to error, missing file or unknown file
            System.out.println("[Save Failure] Could not write updated ledger data back " +
                    "to local drive.");
        }
    }

    // Manual entry interface of section by section for one profile entry
    private static void addManuallyPatron() {
        System.out.println("\n--- Manual CLI Patron Entry ---");

        String inputID;
        // Will start it
        while (true) {
            // Will need a valid 7-digit ID
            System.out.print("Enter Unique 7-Digit ID: ");
            inputID = scanner.nextLine().trim();

            if (inputID.isEmpty()) {
                // cannot be a null input
                System.out.println("[ERROR] ID cannot be null. Please try again.");
            } else if (inputID.length() != 7 || !inputID.matches("\\d+")) {
                // Must be 7 digits or will give an error and print this message
                System.out.println("[ERROR] ID must be exactly 7 numeric characters long. " +
                        "| Range: 0000000 - 9999999");
            } else if (isIDDuplicate(inputID)) {
                // Duplicate error message
                System.out.println("[ERROR] Duplicate ID. This unique identifier is " +
                        "already assigned to a profile.");
            } else {
                break;
            }
        }

        // Entering the String for profile Name
        System.out.print("Enter Patron Full Name: ");
        String inputName = scanner.nextLine().trim();

        // Entering the String for profile Address
        System.out.print("Enter Patron Address: ");
        String inputAddress = scanner.nextLine().trim();

        // While loop for confirming the amount for balance section is 0 to 250
        double checkedBalance;
        while (true) {
            // Message print to enter the specified amount
            System.out.print("Enter Balance amount due 0.00 - 250.00: ");
            String balanceInput = scanner.nextLine().trim();

            // Will print an error if a $ was used with the balance amount
            if (balanceInput.contains("$")) {
                System.out.println("[ERROR] Please do not include the raw dollar symbol ($). " +
                        "Enter numbers only");
                continue;
            }

            try {
                checkedBalance = Double.parseDouble(balanceInput);

                // The given input value was not in-bounds and give an error message
                if (checkedBalance < 0.0 || checkedBalance > 250.0) {
                    System.out.println("[ERROR] Value out-of-bounds. " +
                            "Balance must be between $0.00 and $250.00.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                /* To prevent anything other than integer or double in the last section
                and will print error */
                System.out.println("[ERROR] Input text could not be parsed into a numeric amount.");
            }
        }

        // Successful manual profile upload message
        try {
            PatronList.add(new Patron(inputID, inputName, inputAddress, checkedBalance));
            System.out.println("\n[Success] New profile record created and added to file ledger.");
        } catch (IllegalArgumentException e) {

            System.out.println("[Registration Denied] " + e.getMessage());
        }
    }

    // Removing a profile via the 7-digit ID start
    private static void removeIDPatron() {
        System.out.print("\nEnter the 7-digit ID of the patron to remove them: ");
        String targetID = scanner.nextLine().trim();

        // will confirm for valid ID and then remove
        boolean elementRemoved = false;
        for (int i = 0; i < PatronList.size(); i++) {
            if (PatronList.get(i).getID().equals(targetID)) {
                PatronList.remove(i);
                elementRemoved = true;
                break;
            }
        }

        // Will output message for removing the profile after they have paid or other reason
        if (elementRemoved) {
            System.out.println("[Success] Patron dropped from file ledger. " +
                    "Overdue fees cleared or other reason to remove.");
        } else {
            // ID given in be user is not in the file
            System.out.println("[Deletion Fail] No registered patron exists with ID: " + targetID);
        }
    }

    // Will display a specif ID profile active in the file
    private static void displayIDPatron() {
        System.out.print("\nEnter 7-digit ID to search: ");
        String searchID = scanner.nextLine().trim();

        // Will confirm for valid ID
        boolean targetFound = false;
        for (Patron p : PatronList) {
            if (p.getID().equals(searchID)) {
                System.out.println("\n[Record Found]: " + p);
                targetFound = true;
                break;
            }
        }

        // If it does not exist it will let user know
        if (!targetFound) {
            System.out.println("[Lookup Fail] No registered patron with that ID exists.");
        }
    }

    // Displays ALL current Profiles
    private static void displayPatrons() {
        System.out.println("\n--- Current Registered Patrons List ---");
        if (PatronList.isEmpty()) {
            // If there are NO profile will print this instead
            System.out.println("The active CLI ledger is currently empty.");
            return;
        }

        // Parameters for printing the profiles and dividing it with dashes
        System.out.printf("%-10s | %-20s | %-50s | %-10s\n", "ID", "Name", "Address", "Balance");
        System.out.println("---------------------------------------" +
                "------------------------------------------------");
        for (Patron p : PatronList) {
            System.out.printf("%-10s | %-20s | %-50s | $%-10.2f\n",
                    p.getID(),
                    p.getName(),
                    p.getAddress(),
                    p.getBalance());
        }
        System.out.println("---------------------------------------" +
                "------------------------------------------------");
        // Will show current count of active Patrons with a record in file
        System.out.println("Total Active CLI Records: " + PatronList.size());
    }
}
