package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 60)
    private String name;

    @ManyToOne
    private Shift shift;

    @OneToOne
    private Roster roster;

    public Schedule(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }
}
