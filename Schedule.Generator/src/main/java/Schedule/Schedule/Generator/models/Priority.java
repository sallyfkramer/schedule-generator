package Schedule.Schedule.Generator.models;

public enum Priority {

    MANDATORY ("Mandatory", "Museum cannot safely open without these minimum staffing needs covered.", 1),
    URGENT ("Urgent", "Posts that need to be filled for normal museum operation.", 2),
    STRONG ("Strong Preference","Posts that might go empty on a snow day but are almost always covered.",3),
    PREFERRED ("Preferred","Posts that might go empty if there are several call-ins.",4),
    OPTIONAL ("Optional", "Posts that are filled for best practices but can be left empty if needed.",5),
    LOW ("Most Optional", "First few posts that get left empty when needed.",6),
    EXTRA ("Extra", "Posts that can be filled in case of over staffing.",7);


    private final String name;
    private final String description;
    private final int number;

    Priority(String name, String description, int number) {
        this.name = name;
        this.description=description;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }
}