package Schedule.Schedule.Generator.models;

public enum Priority {

    MANDATORY ("Mandatory", "Museum cannot safely open without these minimum staffing needs covered."),
    URGENT ("Urgent", "Posts that need to be filled for normal museum operation."),
    STRONG ("Strong Preference","Posts that might go empty on a snow day but are almost always covered."),
    PREFERRED ("Preferred","Posts that might go empty if there are several call-ins."),
    OPTIONAL ("Optional", "Posts that are filled for best practices but can be left empty if needed."),
    LOW ("Most Optional", "First few posts that get left empty when needed."),
    EXTRA ("Extra", "Posts that can be filled in case of over staffing.");


    private final String name;
    private final String description;

    Priority(String name, String description) {
        this.name = name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}