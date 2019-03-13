package Schedule.Schedule.Generator.models;

public enum Rarity {

    HIGH ("High"),
    MEDIUM ("Medium"),
    LOW ("Low");


    private final String name;

    Rarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}