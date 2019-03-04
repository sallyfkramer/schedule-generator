package Schedule.Schedule.Generator.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {
    //data fields//
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=45)
    private String name;

    //TODO: add  connection to training//

//    @ManyToMany(mappedBy = "employees")
//    private List<Training> trainings;
//
//    //TODO: add connection to shifts//
//
//    @ManyToMany(mappedBy =  "employees")
//    private List<Shift> shifts;




    //getters and setters//


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
