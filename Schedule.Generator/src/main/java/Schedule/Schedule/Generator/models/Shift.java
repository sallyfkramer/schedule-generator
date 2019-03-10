package Schedule.Schedule.Generator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Shift {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max = 25)
    private String name;

    @NotNull
    @Size(min=3, max=50)
    private String description;

    public Shift(String name, String description){
        this.name= name;
        this.description= description;
    }

    public Shift(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//
//    @ManyToOne
//    private List<Employee> employeeList;
}
