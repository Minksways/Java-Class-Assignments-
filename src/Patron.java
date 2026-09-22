/* This is the Patron Class to be the base for all profiles added in, using the same logic of a
relational database table to contain all the information and make sure no null information remains*/

public class Patron {
    private String ID; // Unique ID that will use 7 digits for each profile
    private String Name;// Name String accepter
    private String Address;// Address String accepter
    private double Balance;// Number accepter for fees due for profiles

    // Constructor to set up all parameters that will be used by application
    public Patron(String ID, String Name, String Address, double Balance) {
        SetID(ID);
        setName(Name);
        SetAddress(Address);
        SetBalance(Balance);
    }

    public void SetID(String ID) {
        // Setter and Getter for ID to only accept a 7-digit number limit and no null
        if (ID == null || ID.trim().isEmpty()) {
            throw new IllegalArgumentException("Patron ID cannot be null or blank.");
        }
        if (ID.length() != 7 || !ID.matches("\\d+")) {
            throw new IllegalArgumentException("Patron ID must be exactly 7 digits long. | " +
                    "Correct range: 0000000 - 9999999");
        }
        this.ID = ID;
    }public String getID() { return this.ID; }

    // Setter and Getter for Names of profiles to not be null and only be String
    public void setName(String Name) {
        if (Name == null || Name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patron Name cannot be null or blank.");
        }
        this.Name = Name;
    } public String getName() { return this.Name; }

    //Setter and Getter for Address of profiles to not be null and only be String
    public void SetAddress(String Address) {
        if (Address == null || Address.trim().isEmpty()) {
            throw new IllegalArgumentException("Patron Address cannot be null or blank.");
        }
        this.Address = Address;
    } public String getAddress() { return this.Address; }

    //Setter and Getter for numbers relating to a overdue amount between 0 - 250 that won't allow null
    public void SetBalance(double overdue) {
        if (overdue < 0.0 || overdue > 250.0) {
            throw new IllegalArgumentException("Fine balance is out of limits. " +
                    "Must be between $0.00 and $250.00. Provided: $" + overdue);
        }
        this.Balance = overdue;
    } public double getBalance() { return this.Balance;}

    /* Overrides the default Java handler for String Files to be the specific 4 required format
    per profile of patrons including the parameters*/
    @Override
    public String toString() {
        return String.format("%s - %s - %s - %.2f", ID, Name, Address, Balance);
    }
}
